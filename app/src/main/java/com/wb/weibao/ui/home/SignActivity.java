package com.wb.weibao.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.lidroid.xutils.util.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivitySignBinding;
import com.wb.weibao.databinding.ItemSignLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.SignListModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.LocationHelper;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;
import com.wb.weibao.view.MyAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class SignActivity extends BaseActivity<BasePresenter, ActivitySignBinding> {

    private List<SignListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<SignListModel.DataBean.ListBean> mAdapter;
    private int mSignType = -1;// 1签到   0签退

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("值班签到");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSignType > -1) {
                    if (checkLocation()) {
                        if (mSignType == 1) {

                            new MyAlertDialog(aty).builder().setTitle("提示")
                                    .setMsg("确定要签退？").setPositiveButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).setNegativeButton("签退", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    addSignOut();
                                }
                            }).show();

                        } else {
                            addSignIn();
                        }
                    } else {

                    }
                } else {
                    showToast("考勤状态获取失败!");
                    checkSign();
                }


            }
        });
        mBinding.tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationHelper.getInstance().startLocation(aty);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<SignListModel.DataBean.ListBean>(aty, R.layout.item_sign_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, SignListModel.DataBean.ListBean item, int position) {
                ItemSignLayoutBinding binding = holder.getBinding(ItemSignLayoutBinding.class);

                binding.tvState.setSelected(!"1".equals(item.getStatus()));
                binding.tvState.setText("1".equals(item.getStatus()) ? "签到" : "签退");
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getSignTime(), "yyyy/MM/dd HH:mm:ss"));
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        mBinding.rcBody.setNestedScrollingEnabled(false);
        mBinding.srlBody.setEnableLoadmore(false);
        mBinding.srlBody.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                addSignList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                addSignList();
            }
        });


        addSignList();
        checkSign();
        LocationHelper.getInstance().setILocationListener(new LocationHelper.ILocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
//                    mBinding.gps.setText("mLatitude=="+mLatitude+"mLongitude==="+mLongitude+"相差距离==="+DemoUtils.DistanceOfTwoPoints(mProjectLatitude, mProjectLongitude, mLatitude, mLongitude));

                    if (checkLocation()) {
                        mBinding.tvResult.setSelected(true);
                        mBinding.tvResult.setText("您已进入考勤范围…");
                    } else {
                        mBinding.tvResult.setSelected(false);
                        mBinding.tvResult.setText("您不在考勤范围…");
                    }
                }
            }
        });
        LocationHelper.getInstance().startLocation(aty);
        SpfUtils spfUtils = SpfUtils.getInstance(aty);
        mProjectLatitude = TextUtils.isEmpty(spfUtils.getSpfString(SpfKey.LatiTude)) ? 0 : Double.valueOf(spfUtils.getSpfString(SpfKey.LatiTude));
        mProjectLongitude = TextUtils.isEmpty(spfUtils.getSpfString(SpfKey.LongiTude)) ? 0 : Double.valueOf(spfUtils.getSpfString(SpfKey.LongiTude));

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
//        LogUtils.d("mProjectLatitude="+mProjectLatitude+"mProjectLongitude="+mProjectLongitude+"mLatitude="+mLatitude+"mLongitude="+mLongitude);
//       showToast("==="+m);
        if (m <= 200) {
            return true;
        } else {
            return false;
        }
    }

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
                        mBinding.tvSign.setText(mSignType == 1 ? "值班签退" : "值班签到");
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 签到
     */
    private void addSignIn() {
        Api.getApi().addSignIn(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("签到成功!");
                        mSignType = 1;
                        mBinding.tvSign.setText("值班签退");
                        addSignList();
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 签退
     */
    private void addSignOut() {
        Api.getApi().addSignOut(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("签退成功!");
                        mSignType = 0;
                        mBinding.tvSign.setText("值班签到");
                        addSignList();
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 签到退   列表
     */
    private void addSignList() {
        Api.getApi().getSignList(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<SignListModel>(this, true) {
                    @Override
                    public void onSuccess(SignListModel baseBean) {
                        stopRefersh();
                        if (baseBean.getData() != null) {
                            List<SignListModel.DataBean.ListBean> list = baseBean.getData().getList();
                            mDataList.clear();
                            if (list != null && list.size() > 0) {
                                mDataList.addAll(list);
                            }
                            mAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFail(String errMsg) {
                        stopRefersh();
                    }
                });

    }

    private void stopRefersh() {
        mBinding.srlBody.finishRefresh();
        mBinding.srlBody.finishLoadmore();
    }

}
