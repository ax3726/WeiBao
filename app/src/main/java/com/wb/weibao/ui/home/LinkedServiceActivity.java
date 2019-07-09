package com.wb.weibao.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityLinkedServiceBinding;

public class LinkedServiceActivity extends BaseActivity<BasePresenter,ActivityLinkedServiceBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_linked_service;
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
        mTitleBarLayout.setTitle("关联服务");
    }
}
