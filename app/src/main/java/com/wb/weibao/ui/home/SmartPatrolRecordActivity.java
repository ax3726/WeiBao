package com.wb.weibao.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.View;
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
import com.wb.weibao.databinding.ActivitySmartPatrolRecordBinding;
import com.wb.weibao.databinding.ItemSmartPatrolRecordBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.view.PopupWindow.ConfirmPopupwindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SmartPatrolRecordActivity extends BaseActivity<BasePresenter, ActivitySmartPatrolRecordBinding> {

    private List<String>          mDataList           = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private int                   mPage               = 1;
    private int                   mPageSize           = 10;
    private ConfirmPopupwindow    mConfirmPopupwindow = null;

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("智慧巡查记录");
        mTitleBarLayout.setRightShow(true);
        mTitleBarLayout.setRightTxt("结束巡查");
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        TextView txt_search = (TextView) mBinding.searchview.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        //设置字体大小为14sp
        txt_search.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
        //设置字体颜色
        txt_search.setTextColor(getResources().getColor(R.color.color333333));
        //设置提示文字颜色
        txt_search.setHintTextColor(getResources().getColor(R.color.color999999));
        //去掉searchview下划线
        View view = mBinding.searchview.findViewById(android.support.v7.appcompat.R.id.search_plate);
        view.setBackgroundColor(Color.TRANSPARENT);

    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }

    @Override
    protected void initData() {
        super.initData();
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");

        mAdapter = new CommonAdapter<String>(aty, R.layout.item_smart_patrol_record, mDataList) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                ItemSmartPatrolRecordBinding binding = holder.getBinding(ItemSmartPatrolRecordBinding.class);
                binding.tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mConfirmPopupwindow = new ConfirmPopupwindow(aty);
                        mConfirmPopupwindow.setConfirmListener(new ConfirmPopupwindow.ConfirmListener() {
                            @Override
                            public void onOk(ArrayList<String> imageUUid, String txt) {//返回结果

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
    }

    private void getDataList() {

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
//        if (!TextUtils.isEmpty(str)) {
        File   file  = new File(str);
        byte[] bytes = DemoUtils.getimageByte(str);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), bytes));


        Api.getApi().upLoad(body)
                .compose(callbackOnIOToMainThread()).subscribe(new BaseNetListener<BaseBean>(this, true) {
            @Override
            public void onSuccess(BaseBean baseBean) {
                if (mConfirmPopupwindow != null) {
                    mConfirmPopupwindow.addData(baseBean.getData().toString());
                }

            }

            @Override
            public void onFail(String errMsg) {
//                    mBinding.affirm.setEnabled(true);
            }
        });
//        } else {
//            addRecord();
//        }

    }
}
