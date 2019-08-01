package com.wb.weibao.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.baidu.location.BDLocation;
import com.lidroid.xutils.util.LogUtils;

import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.lling.photopicker.PhotoPickerActivity;
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
import com.wb.weibao.databinding.ActivitySmartPatrolRecordBinding;
import com.wb.weibao.databinding.ItemSmartPatrolRecordBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.event.SmartPatrolEvent;
import com.wb.weibao.model.event.SmartlectorMonitoringEvent;
import com.wb.weibao.model.home.PatrolPointListBean;

import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.LocationHelper;
import com.wb.weibao.view.MyAlertDialog;
import com.wb.weibao.view.PopupWindow.ConfirmPopupwindow;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SmartPatrolRecordActivity extends BaseActivity<BasePresenter, ActivitySmartPatrolRecordBinding> {


    private List<PatrolPointListBean.DataBean.ListBean>          mDataList           = new ArrayList<>();
    private CommonAdapter<PatrolPointListBean.DataBean.ListBean> mAdapter;
    private int                                                  mPage               = 1;
    private int                                                  mPageSize           = 10;
    private ConfirmPopupwindow                                   mConfirmPopupwindow = null;
    private String                                               name                = "";

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setLeftShow(false);
        mTitleBarLayout.setTitle("智慧巡查");
        mTitleBarLayout.setRightTxt("结束巡查");
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyAlertDialog(aty).builder().setTitle("提示")
                        .setMsg("确定要结束并提交巡查记录吗？").setNegativeButton("再等等", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setPositiveButton("确定结束", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        getEndPatrol(getIntent().getStringExtra("patrolRecordId"));
                    }
                }).show();


            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_patrol_record;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mBinding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //如果actionId是搜索的id，则进行下一步的操作
                    String newText = mBinding.etSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(newText)) {
                        LogUtils.e("11===" + newText);
                        name = newText;
                        getDataList();
                    } else {

                        name = "";
                        getDataList();
                    }

                }
                return false;
            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<PatrolPointListBean.DataBean.ListBean>(aty, R.layout.item_smart_patrol_record, mDataList) {
            @Override
            protected void convert(ViewHolder holder, PatrolPointListBean.DataBean.ListBean item, int position) {
                ItemSmartPatrolRecordBinding binding = holder.getBinding(ItemSmartPatrolRecordBinding.class);
                binding.tvName.setText(mDataList.get(position).getName());
                binding.tvContent.setText(mDataList.get(position).getAddress());
                binding.tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mConfirmPopupwindow = new ConfirmPopupwindow(aty, "1");
                        mConfirmPopupwindow.setToastListener(new ConfirmPopupwindow.ToastListener() {
                            @Override
                            public void Toast(String txt) {

                                showToast(txt);
                            }
                        });
                        mConfirmPopupwindow.setConfirmListener(new ConfirmPopupwindow.ConfirmListener() {
                            @Override
                            public void onOk(ArrayList<String> imageUUid, String txt) {//返回结果
                                String str = DemoUtils.ListToString(imageUUid, ";");
                                LocationHelper.getInstance().startLocation(aty);
                                Api.getApi().getPatrolRecordAppAdd(getIntent().getStringExtra("patrolRecordId"), "" + item.getId(), "" + mLatitude, "" + mLongitude, txt, str, "1")
                                        .compose(callbackOnIOToMainThread())
                                        .subscribe(new BaseNetListener<BaseBean>(SmartPatrolRecordActivity.this, true) {
                                            @Override
                                            public void onSuccess(BaseBean baseBean) {
                                                getDataList();

                                            }

                                            @Override
                                            public void onFail(String errMsg) {

                                            }
                                        });
                            }
                        });
                        mConfirmPopupwindow.showPopupWindow();
                    }
                });

                binding.tvFastOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mConfirmPopupwindow = new ConfirmPopupwindow(aty, "2");
                        mConfirmPopupwindow.setConfirmListener(new ConfirmPopupwindow.ConfirmListener() {
                            @Override
                            public void onOk(ArrayList<String> imageUUid, String txt) {//返回结果
                                String str = DemoUtils.ListToString(imageUUid, ";");
                                LocationHelper.getInstance().startLocation(aty);
                                Api.getApi().getPatrolRecordAppAdd(getIntent().getStringExtra("patrolRecordId"), "" + item.getId(), "" + mLatitude, "" + mLongitude, txt, str, "2")
                                        .compose(callbackOnIOToMainThread())
                                        .subscribe(new BaseNetListener<BaseBean>(SmartPatrolRecordActivity.this, true) {
                                            @Override
                                            public void onSuccess(BaseBean baseBean) {
                                                showToast("急速巡查成功");
                                                getDataList();

                                            }

                                            @Override
                                            public void onFail(String errMsg) {

                                            }
                                        });
                            }
                        });
                        mConfirmPopupwindow.showPopupWindow();
                    }
                });
            }
        };


        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);


        mBinding.srlBody.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                getDataList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mBinding.srlBody.resetNoMoreData();
                mPage = 1;
                getDataList();
            }
        });
        getDataList();


        LocationHelper.getInstance().setILocationListener(new LocationHelper.ILocationListener() {
            @Override
            public void onLocationChanged(BDLocation location) {
                if (location != null) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();

                }
            }
        });
        LocationHelper.getInstance().startLocation(aty);


    }

    private double mLatitude  = 0;
    private double mLongitude = 0;

    private void getDataList() {
        Api.getApi().getPatrolPointList(name, MyApplication.getInstance().getProjectId(), "" + mPage, "" + mPageSize)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<PatrolPointListBean>(SmartPatrolRecordActivity.this, false) {
                    @Override
                    public void onSuccess(PatrolPointListBean baseBean) {
                        stopRefersh();
                        PatrolPointListBean.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<PatrolPointListBean.DataBean.ListBean> list = data.getList();
                            if (list != null && list.size() > 0) {
                                mDataList.addAll(list);
                                if (list.size() < mPageSize) {
                                    mBinding.srlBody.finishLoadmoreWithNoMoreData();
                                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PhotoPickerActivity.RESULT_OK && requestCode == ConfirmPopupwindow.RequestCode) {
            ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
            if (mConfirmPopupwindow != null) {
                if (result != null && result.size() > 0) {
                    loadImg(result.get(0));
                    mConfirmPopupwindow.setData(result.get(0));
                    mAdapter.notifyDataSetChanged();

                }

            }
        }
    }


    private void loadImg(String str) {
        File   file  = new File(str);
        byte[] bytes = DemoUtils.getimageByte(str);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =

                MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), bytes));


        Api.getApi().upLoad(body)
                .compose(callbackOnIOToMainThread()).subscribe(new BaseNetListener<BaseBean>(this, true) {
            @Override
            public void onSuccess(BaseBean baseBean) {
//                mImageUUid.add(baseBean.getData().toString());
                if (mConfirmPopupwindow != null) {
                    mConfirmPopupwindow.addData(baseBean.getData().toString());
                }
            }

            @Override
            public void onFail(String errMsg) {
            }
        });

    }

    private void getEndPatrol(String id) {
        Api.getApi().getEndPatrol(id, "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        EventBus.getDefault().post(new SmartPatrolEvent());
                        finish();
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            new MyAlertDialog(aty).builder().setTitle("提示")
                    .setMsg("确定要结束并提交巡查记录吗？").setNegativeButton("再等等", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).setPositiveButton("确定结束", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getEndPatrol(getIntent().getStringExtra("patrolRecordId"));
                }
            }).show();


            return true;
        }
        return super.onKeyDown(keyCode,event);

    }
}
