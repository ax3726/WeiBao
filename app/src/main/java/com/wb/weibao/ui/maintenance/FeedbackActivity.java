package com.wb.weibao.ui.maintenance;

import android.text.TextUtils;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.databinding.ActivityFeedbackBinding;
import com.wb.weibao.model.event.AddOderEvent;

import org.greenrobot.eventbus.EventBus;

public class FeedbackActivity extends BaseActivity<BasePresenter,ActivityFeedbackBinding> {


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
    }


    private void submit() {
        String name = mBinding.etName.getText().toString();
        String phone = mBinding.etPhone.getText().toString();
        String content = mBinding.etContent.getText().toString();

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
        mBinding.tvSubmit.setClickable(false);
        Api.getApi().getorderUpdateFankui(getIntent().getStringExtra("userId").toString(),"8",getIntent().getStringExtra("id").toString(),name,phone,content).compose(callbackOnIOToMainThread())
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
}
