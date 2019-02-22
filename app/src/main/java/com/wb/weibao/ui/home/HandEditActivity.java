package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityHandEditBinding;

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
                setResult(RESULT_OK, new Intent().putExtra("data", trim));
                finish();

            }
        });
    }
}
