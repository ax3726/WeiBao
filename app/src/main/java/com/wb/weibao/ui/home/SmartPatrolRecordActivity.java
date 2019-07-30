package com.wb.weibao.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.lidroid.xutils.util.LogUtils;
import android.util.TypedValue;
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
import com.wb.weibao.model.home.PatrolPointListBean;

import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.LocationHelper;
import com.wb.weibao.view.PopupWindow.ConfirmPopupwindow;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SmartPatrolRecordActivity extends BaseActivity<BasePresenter, ActivitySmartPatrolRecordBinding> {


    private List<PatrolPointListBean.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<PatrolPointListBean.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    private ConfirmPopupwindow    mConfirmPopupwindow = null;
    private String name="";

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("智慧巡查记录");
        mTitleBarLayout.setRightTxt("结束巡查");

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
        TextView txt_search = (TextView) mBinding.searchview.findViewById(R.id.search_src_text);
        //设置字体大小为14sp
        txt_search.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
        //设置字体颜色
        txt_search.setTextColor(getResources().getColor(R.color.color333333));
        //设置提示文字颜色
        txt_search.setHintTextColor(getResources().getColor(R.color.color999999));
        //去掉searchview下划线
        View view = mBinding.searchview.findViewById(R.id.search_plate);
        view.setBackgroundColor(Color.TRANSPARENT);

    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }

    @Override
    protected void initData() {
        super.initData();
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEndPatrol(getIntent().getStringExtra("patrolRecordId").toString());
            }
        });
        mAdapter = new CommonAdapter<PatrolPointListBean.DataBean.ListBean>(aty, R.layout.item_smart_patrol_record, mDataList) {
            @Override
            protected void convert(ViewHolder holder, PatrolPointListBean.DataBean.ListBean item, int position) {
                ItemSmartPatrolRecordBinding binding = holder.getBinding(ItemSmartPatrolRecordBinding.class);
                binding.tvName.setText(mDataList.get(position).getName());
                binding.tvContent.setText(mDataList.get(position).getAddress());
                binding.tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mConfirmPopupwindow = new ConfirmPopupwindow(aty,"1");
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
                                Api.getApi().getPatrolRecordAppAdd(getIntent().getStringExtra("patrolRecordId"),""+item.getId(),""+mLatitude,""+mLongitude,txt,str,"1")
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
                        mConfirmPopupwindow = new ConfirmPopupwindow(aty,"2");
                        mConfirmPopupwindow.setConfirmListener(new ConfirmPopupwindow.ConfirmListener() {
                            @Override
                            public void onOk(ArrayList<String> imageUUid, String txt) {//返回结果
                                String str = DemoUtils.ListToString(imageUUid, ";");
                                LocationHelper.getInstance().startLocation(aty);
                                Api.getApi().getPatrolRecordAppAdd(getIntent().getStringExtra("patrolRecordId"),""+item.getId(),""+mLatitude,""+mLongitude,txt,str,"2")
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
        mBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText))
                {
                    LogUtils.e("11==="+newText);
                    name=newText;
                    getDataList();
                }else
                {

                    name="";
                   getDataList();
                }
                return false;
            }
        });




        LocationHelper.getInstance().setILocationListener(new LocationHelper.ILocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (location != null) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();

                }
            }
        });
        LocationHelper.getInstance().startLocation(aty);



    }
    private double mLatitude = 0;
    private double mLongitude = 0;

    private void getDataList() {
        Api.getApi().getPatrolPointList(name, MyApplication.getInstance().getProjectId(),"" + mPage, "" + mPageSize)
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
        File file = new File(str);
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
        Api.getApi().getEndPatrol(id,"")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }
}
