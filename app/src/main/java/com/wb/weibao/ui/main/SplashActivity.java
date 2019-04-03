package com.wb.weibao.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.lm.lib_common.utils.MD5Utils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivitySplashBinding;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.ui.Login.LoginActivity;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;

public class SplashActivity extends BaseActivity<BasePresenter, ActivitySplashBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private SpfUtils spfUtils;

    @Override
    protected void initData() {
        super.initData();
        spfUtils = SpfUtils.getInstance(aty);
        String name = spfUtils.getSpfString(SpfKey.LOGIN_NAME);
        String pwd = spfUtils.getSpfString(SpfKey.LOGIN_PASSWORD);
        if (!TextUtils.isEmpty(pwd)) {
            Login(name, pwd);
        } else {
            toLoginActivity();
        }

    }

    private void toLoginActivity()
    {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(aty, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
    private void Login(String name, String pwd) {

        Api.getApi().getUserLogin(name, MD5Utils.encryptMD5(pwd))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<LoginModel>(this, false) {
                    @Override
                    public void onSuccess(LoginModel loginModel) {
                        MyApplication.getInstance().setUserData(loginModel.getData());
                        Log.e("====", loginModel.toString());
                        spfUtils.setSpfString(SpfKey.IS_LOGIN, "true");

                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(aty, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }.start();


                    }

                    @Override
                    public void onFail(String errMsg) {
                        toLoginActivity();
                    }
                });

    }
}
