package com.wb.weibao.ui.Login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.lm.lib_common.utils.MD5Utils;
import com.lm.lib_common.utils.WorksSizeCheckUtil;
import com.wb.weibao.BuildConfig;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.databinding.ActivityForgetPwdBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.VersionBean;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.update.AppUpdateProgressDialog;
import com.wb.weibao.utils.update.DownloadReceiver;
import com.wb.weibao.utils.update.DownloadService;

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


    private int istrue=1;

    @Override
    protected void initData() {
        super.initData();
        WorksSizeCheckUtil.textChangeListener listener = new WorksSizeCheckUtil.textChangeListener(mBinding.affirm);
        listener.addAllEditText(mBinding.etName, mBinding.etPhone,mBinding.etPwd,mBinding.etNewpwd);

        countDownTimer = new CountDownTimer(60*1000+50, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               mBinding.tvPhoneCode.setText(millisUntilFinished/1000+"S");
                istrue=0;
            }

            @Override
            public void onFinish() {
                mBinding.tvPhoneCode.setText("重新获取");
                istrue=1;
            }
        };
        mBinding.tvPhoneCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBinding.etName.getText().length()==11) {
                    if(istrue==1) {
                        Api.getApi().getphoneCode(mBinding.etName.getText().toString())
                                .compose(callbackOnIOToMainThread())
                                .subscribe(new BaseNetListener<BaseBean>(ForgetPwdActivity.this, false) {
                                    @Override
                                    public void onSuccess(BaseBean baseBean) {
                                        LogUtils.e("baseBean" + baseBean.toString());
                                        countDownTimer.start();

                                    }

                                    @Override
                                    public void onFail(String errMsg) {
                                        istrue=1;
                                    }
                                });
                    }
                }else
                    {
                        showToast("输入的手机号码不正确");
                    }
            }
        });



       mBinding.affirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String newpwd = mBinding.etPwd.getText().toString().trim();
               String newpwd2 = mBinding.etNewpwd.getText().toString().trim();

               if (TextUtils.isEmpty(newpwd)) {
                   showToast("新密码不能为空!");
                   return;
               }
               if (TextUtils.isEmpty(newpwd2)) {
                   showToast("新密码不能为空!");
                   return;
               }

               if(!newpwd.equals(newpwd2))
               {
                   showToast("两次输入的新密码不一致!");
                   return;
               }
               Api.getApi().getupdatePassword(mBinding.etName.getText().toString(),mBinding.etPhone.getText().toString(), MD5Utils.encryptMD5(mBinding.etNewpwd.getText().toString()))
                       .compose(callbackOnIOToMainThread())
                       .subscribe(new BaseNetListener<BaseBean>(ForgetPwdActivity.this, false) {
                           @Override
                           public void onSuccess(BaseBean baseBean) {
                               LogUtils.e("baseBean" + baseBean.toString());


                           }

                           @Override
                           public void onFail(String errMsg) {

                           }
                       });
           }
       });



    }
    private CountDownTimer countDownTimer;

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

}
