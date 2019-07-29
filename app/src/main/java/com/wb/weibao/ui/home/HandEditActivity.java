package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityHandEditBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.ChangEvent;
import com.wb.weibao.utils.AppManager;
import com.wb.weibao.widget.zxing.android.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

public class HandEditActivity extends BaseActivity<BasePresenter, ActivityHandEditBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_hand_edit;
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
        mTitleBarLayout.setTitle("手动输入");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = mBinding.etContent.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    showToast("输入内容不能为空!");
                    return;
                }
                Api.getApi().getQrcodeProccess(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",trim)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(HandEditActivity.this, false) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                com.lidroid.xutils.util.LogUtils.d("BaseBean=="+baseBean.toString());
                                showToast("交班成功");
                                AppManager.finishActivity2(CaptureActivity.class);
                                EventBus.getDefault().post(new ChangEvent());
                                finish();

                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });
//                setResult(RESULT_OK, new Intent().putExtra("data", trim));
//                finish();

            }
        });
    }
}
