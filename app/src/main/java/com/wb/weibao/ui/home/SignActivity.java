package com.wb.weibao.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivitySignBinding;

import java.util.ArrayList;
import java.util.List;

public class SignActivity extends BaseActivity<BasePresenter,ActivitySignBinding> {

    private List<String> mDataList=new ArrayList<>();
    private CommonAdapter<String> mAdapter;
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
    protected void initData() {
        super.initData();
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mAdapter=new CommonAdapter<String>(aty,R.layout.item_sign_layout,mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        mBinding.rcBody.setNestedScrollingEnabled(false);
        mBinding.srlBody.setEnableLoadmore(false);
        mBinding.srlBody.setEnableRefresh(false);
    }
}
