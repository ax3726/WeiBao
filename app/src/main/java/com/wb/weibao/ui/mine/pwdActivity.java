package com.wb.weibao.ui.mine;

import android.text.TextUtils;
import android.view.View;

import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.model.BaseBean;
import com.lm.lib_common.utils.MD5Utils;
import com.lm.lib_common.utils.WorksSizeCheckUtil;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityPwdBinding;
import com.wb.weibao.ui.Login.LoginActivity;

public class pwdActivity extends BaseActivity<BasePresenter,ActivityPwdBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pwd;
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
        mTitleBarLayout.setTitle("修改密码");
        WorksSizeCheckUtil.textChangeListener listener = new WorksSizeCheckUtil.textChangeListener(mBinding.affirm);
        listener.addAllEditText(mBinding.inputPwd,mBinding.inputNewpwd,mBinding.inputNewpwd2);
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatePwd();
            }
        });
    }


    private void updatePwd() {
        String pwd = mBinding.inputPwd.getText().toString().trim();
        String newpwd = mBinding.inputNewpwd.getText().toString().trim();
        String newpwd2 = mBinding.inputNewpwd2.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            showToast("原始密码不能为空!");
            return;
        }
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


        Api.getApi().getupdatePwd(""+MyApplication.getInstance().getUserData().getId(),MD5Utils.encryptMD5(newpwd),MD5Utils.encryptMD5(pwd))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                       showToast("修改密码成功!");
                        startActivity(LoginActivity.class);
                        aty.finish();
                        MyApplication.getInstance().exit();
                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.affirm.setClickable(true);
                    }
                });
               mBinding.affirm.setClickable(false);
    }



}
