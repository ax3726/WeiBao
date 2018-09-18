package com.wb.weibao.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BasePresenter;
import com.wb.weibao.R;
import com.wb.weibao.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
