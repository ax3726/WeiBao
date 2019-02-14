package com.wb.weibao.ui.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityForgetPwdBinding;
import com.wb.weibao.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity<BasePresenter,ActivityRegisterBinding> {


    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("免费申请账户");
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
