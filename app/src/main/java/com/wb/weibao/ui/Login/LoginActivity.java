package com.wb.weibao.ui.Login;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.utils.MD5Utils;
import com.lm.lib_common.utils.WorksSizeCheckUtil;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityLoginBinding;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.ui.main.MainActivity;

public class LoginActivity extends BaseActivity<BasePresenter,ActivityLoginBinding> {


    private boolean flag=true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        WorksSizeCheckUtil.textChangeListener listener = new WorksSizeCheckUtil.textChangeListener(mBinding.affirm);
        listener.addAllEditText(mBinding.inputPhone,mBinding.inputPassword);
        mBinding.pwdeye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    mBinding.pwdeye.setBackgroundResource(R.mipmap.ic_eye2);
                    mBinding.inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mBinding.inputPassword.setSelection(mBinding.inputPassword.getText().toString().trim().length());
                    return;
                }
                flag = true;
                mBinding.pwdeye.setBackgroundResource(R.mipmap.ic_eye1);
                mBinding.inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.inputPassword.setSelection(mBinding.inputPassword.getText().toString().trim().length());
            }
        });
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }


    private void checkLogin() {
        String phone = mBinding.inputPhone.getText().toString().trim();
        String password = mBinding.inputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showToast("用户名不能为空!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空!");
            return;
        }
        Api.getApi().getUserLogin(phone, MD5Utils.encryptMD5(password))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<LoginModel>(LoginActivity.this, true) {
                    @Override
                    public void onSuccess(LoginModel loginModel) {

                        MyApplication.getInstance().setUserData(loginModel.getData());
                        Log.e("====",loginModel.toString());
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.affirm.setClickable(true);
                    }
                });
               mBinding.affirm.setClickable(false);
    }


}
