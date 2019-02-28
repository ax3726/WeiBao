package com.wb.weibao.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityFireControlBinding;

public class FireControlActivity extends BaseActivity<BasePresenter, ActivityFireControlBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_fire_control;
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
        mTitleBarLayout.setTitle("消防微站");
    }


}
