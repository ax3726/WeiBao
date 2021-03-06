package com.wb.weibao.ui.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.lm.lib_common.utils.WorksSizeCheckUtil;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityForgetPwdBinding;
import com.wb.weibao.databinding.ActivityRegisterBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.ui.home.AddDayWeiBaoActivity;
import com.wb.weibao.utils.DataUtils;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.view.AddSpaceTextWatcher;
import com.wb.weibao.view.MyAlertDialog;

import org.greenrobot.eventbus.EventBus;

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

    private AddSpaceTextWatcher asEditTexts;
    @Override
    protected void initData() {
        super.initData();
        WorksSizeCheckUtil.textChangeListener listener = new WorksSizeCheckUtil.textChangeListener(mBinding.affirm);
        listener.addAllEditText(mBinding.etName,mBinding.etPhone);
        asEditTexts = new AddSpaceTextWatcher(mBinding.etPhone, 13);
        asEditTexts.setSpaceType(AddSpaceTextWatcher.SpaceType.mobilePhoneNumberType);
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void update() {
        String name = mBinding.etName.getText().toString().trim();
        String phone = mBinding.etPhone.getText().toString().replace(" ", "");
        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名!");
            return;
        }
        if (TextUtils.isEmpty(phone) ) {
            showToast("请输入手机号码!");
            return;
        }
        if (phone.length()<11 || phone.length()>11) {
            showToast("手机号码格式不正确!");
            return;
        }


        Api.getApi().getintentionadd(name,phone)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
//                        showToast("资料已提交!");
//                        new Thread() {
//                            @Override
//                            public void run() {
//                                super.run();
//                                try {
//                                    sleep(3000);
//                                    finish();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }.start();


                        new MyAlertDialog(aty).builder().setTitle("客官，我们已收到您的申请")
                                .setMsg("工作人员会在1—2个工作日内联系您,请保持电话畅通。联系我们：0571-56260119").setPositiveButton("我知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();



                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.affirm.setClickable(true);
                    }
                });
        mBinding.affirm.setClickable(false);
    }

}
