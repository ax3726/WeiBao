package com.wb.weibao.ui.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityForgetPwdBinding;

public class ForgetPwdActivity extends BaseActivity<BasePresenter,ActivityForgetPwdBinding> {


    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("忘记密码");
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
