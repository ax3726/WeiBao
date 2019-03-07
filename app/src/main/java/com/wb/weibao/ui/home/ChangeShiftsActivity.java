package com.wb.weibao.ui.home;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityChangeShiftsBinding;
import com.wb.weibao.databinding.ItemChangeShiftsLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.ChangEvent;
import com.wb.weibao.model.home.Handoverbean;
import com.wb.weibao.model.home.SignListModel;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.LocationHelper;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;
import com.wb.weibao.view.MyAlertDialog;
import com.wb.weibao.widget.zxing.android.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChangeShiftsActivity extends BaseActivity<BasePresenter, ActivityChangeShiftsBinding> {
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final int REQUEST_CODE_SCAN = 2001;//二维码扫码参数
    private List<Handoverbean.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<Handoverbean.DataBean.ListBean> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_shifts;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("交接班");
    }

    private int isLocation = 0;

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvJiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLocation == 0) {
                    new MyAlertDialog(ChangeShiftsActivity.this).builder().setTitle("提示").setMsg("您已超出考勤范围，无法交接班，请在考勤范内交接班").setPositiveButton("我知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).setNegativeButton("重新定位", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocationHelper.getInstance().startLocation(aty);
                        }
                    }).show();
                } else {
                    if (mSignType != 1) {
                        new MyAlertDialog(ChangeShiftsActivity.this).builder().setTitle("提示").setMsg("您还未签到，请先签到，再进行交接班").setPositiveButton("我知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    } else {

                        startActivity(QrcodeActivity.class);
                        finish();
                    }
                }
            }
        });

        mBinding.tvJie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLocation == 0) {
                    new MyAlertDialog(ChangeShiftsActivity.this).builder().setTitle("提示").setMsg("您已超出考勤范围，无法交接班，请在考勤范内交接班").setPositiveButton("我知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).setNegativeButton("重新定位", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LocationHelper.getInstance().startLocation(aty);
                        }
                    }).show();
                } else {
                    if (mSignType != 1) {
                        new MyAlertDialog(ChangeShiftsActivity.this).builder().setTitle("提示").setMsg("您还未签到，请先签到，再进行交接班").setPositiveButton("我知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    } else {

                        Intent intent = new Intent(aty,
                                CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_SCAN);

                    }
                }
            }
        });


    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        checkSign();
        mAdapter = new CommonAdapter<Handoverbean.DataBean.ListBean>(aty, R.layout.item_change_shifts_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, Handoverbean.DataBean.ListBean listBean, int position) {
                ItemChangeShiftsLayoutBinding binding = holder.getBinding(ItemChangeShiftsLayoutBinding.class);
                binding.viewLine.setVisibility(position == mDataList.size() - 1 ? View.GONE : View.VISIBLE);
                if (listBean.getCrossUserId().equals("" + MyApplication.getInstance().getUserData().getId())) {
                    binding.tvTitle.setText("交班于消防保安" + listBean.getConnectUserName());
                } else if (listBean.getConnectUserId().equals("" + MyApplication.getInstance().getUserData().getId())) {
                    binding.tvTitle.setText("接班于消防保安" + listBean.getConnectUserName());
                }
                long handoverTime = listBean.getHandoverTime();
                binding.tvTime.setText(transferLongToDate("yyyy-MM-dd HH:mm:ss", handoverTime));

            }

        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        getDataList();


        LocationHelper.getInstance().setILocationListener(new LocationHelper.ILocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
                    if (checkLocation()) {
                        isLocation = 1;
//                        mBinding.tvResult.setText("您已进入考勤范围…");
                    } else {
                        isLocation = 0;

//                        mBinding.tvResult.setText("您不在考勤范围…");
                    }
                }
            }
        });
        LocationHelper.getInstance().startLocation(aty);


        SpfUtils spfUtils = SpfUtils.getInstance(aty);
        mProjectLatitude = TextUtils.isEmpty(spfUtils.getSpfString(SpfKey.LatiTude)) ? 0 : Double.valueOf(spfUtils.getSpfString(SpfKey.LatiTude));
        mProjectLongitude = TextUtils.isEmpty(spfUtils.getSpfString(SpfKey.LongiTude)) ? 0 : Double.valueOf(spfUtils.getSpfString(SpfKey.LongiTude));


//        LocationHelper.getInstance().startLocation(aty);  重新定位
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(ChangEvent event) {
        getDataList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Api.getApi().getQrcodeProccess(MyApplication.getInstance().getUserData().getId() + "",content)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                com.lidroid.xutils.util.LogUtils.d("BaseBean=="+baseBean.toString());
                              showToast("交班成功");
                                getDataList();
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });


            }
        }
    }

    /**
     * 交接班记录
     */
    private void getDataList() {
        Api.getApi().getHandoverList(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<Handoverbean>(this, true) {
                    @Override
                    public void onSuccess(Handoverbean baseBean) {

                        Handoverbean.DataBean data = baseBean.getData();
                        List<Handoverbean.DataBean.ListBean> list = data.getList();
                        mDataList.addAll(list);

                    }

                    @Override
                    public void onFail(String errMsg) {
                    }
                });

    }


    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }


    private double mLatitude = 0;
    private double mLongitude = 0;
    private double mProjectLatitude = 0;
    private double mProjectLongitude = 0;

    private boolean checkLocation() {
        if (mLatitude == 0 || mLongitude == 0) {
            return false;
        }
        double m = DemoUtils.DistanceOfTwoPoints(mProjectLatitude, mProjectLongitude, mLatitude, mLongitude);
        if (m <= 200) {
            return true;
        } else {
            return false;
        }
    }


    private int mSignType = -1;// 1签到   0签退

    /**
     * 检查状态
     */
    private void checkSign() {
        Api.getApi().checkSign(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        mSignType = "1".equals(baseBean.getData().toString()) ? 1 : 0;

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


}
