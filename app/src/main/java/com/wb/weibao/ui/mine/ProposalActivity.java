package com.wb.weibao.ui.mine;

import android.app.Activity;
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
import com.wb.weibao.databinding.ActivityProposalBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.ChangEvent;
import com.wb.weibao.ui.home.HandEditActivity;
import com.wb.weibao.utils.AppManager;
import com.wb.weibao.widget.zxing.android.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

public class ProposalActivity extends BaseActivity<BasePresenter,ActivityProposalBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_proposal;
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
        mTitleBarLayout.setTitle("提建议");
    }

    @Override
    protected void initData() {
        super.initData();

        WorksSizeCheckUtil.textChangeListener listener = new WorksSizeCheckUtil.textChangeListener(mBinding.affirm);
        listener.addAllEditText(mBinding.etContent);
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mBinding.etContent.getText().toString())) {
                    showToast("建议不能为空");
                    return;
                }
                Api.getApi().getAdviceadd(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",mBinding.etContent.getText().toString())
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(ProposalActivity.this, false) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                com.lidroid.xutils.util.LogUtils.d("BaseBean=="+baseBean.toString());
                                showToast("感谢您提出的宝贵意见!");

                                finish();

                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });
            }
        });
    }


}
