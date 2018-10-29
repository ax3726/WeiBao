package com.wb.weibao.ui.maintenance;

import android.text.TextUtils;
import android.view.View;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityFeedbackBinding;
import com.wb.weibao.model.event.AddOderEvent;

import org.greenrobot.eventbus.EventBus;

public class FeedbackActivity extends BaseActivity<BasePresenter,ActivityFeedbackBinding> {


    private String name;
    private String phone;
    private String content;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
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
        mTitleBarLayout.setTitle("维保反馈");

    }

    @Override
    protected void initData() {
        super.initData();

        mBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                mBinding.tvSubmit.setClickable(false);
                Api.getApi().getorderUpdateFankui("" + MyApplication.getInstance().getUserData().userRoles.get(0).userId,"8",getIntent().getStringExtra("id").toString(),name,phone,content).compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(FeedbackActivity.this, false) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                EventBus.getDefault().post(new AddOderEvent());
                                finish();
                            }

                            @Override
                            public void onFail(String errMsg) {
                                mBinding.tvSubmit.setClickable(true);
                            }
                        });
            }
        });
        mBinding.affirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                mBinding.affirm1.setClickable(false);
                Api.getApi().getorderUpdateFankui("" + MyApplication.getInstance().getUserData().userRoles.get(0).userId,"7",getIntent().getStringExtra("id").toString(),name,phone,content).compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(FeedbackActivity.this, false) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                EventBus.getDefault().post(new AddOderEvent());
                                finish();
                            }

                            @Override
                            public void onFail(String errMsg) {
                                mBinding.affirm1.setClickable(true);
                            }
                        });
            }
        });
    }


    private void submit() {

        name = mBinding.etName.getText().toString();
        phone = mBinding.etPhone.getText().toString();
        content = mBinding.etContent.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入处理人姓名!");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入最终花费金额!");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            showToast("请输入处理结果!");
            return;
        }

    }


}
