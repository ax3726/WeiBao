package com.wb.weibao.ui.home;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityInitiateStateBinding;

public class InitiateStateActivity extends BaseActivity<BasePresenter,ActivityInitiateStateBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiate_state;
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
        mTitleBarLayout.setTitle("提交成功");
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
