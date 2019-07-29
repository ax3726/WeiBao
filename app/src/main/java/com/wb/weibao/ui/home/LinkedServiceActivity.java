package com.wb.weibao.ui.home;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityLinkedServiceBinding;

public class LinkedServiceActivity extends BaseActivity<BasePresenter,ActivityLinkedServiceBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_linked_service;
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
        mTitleBarLayout.setTitle("关联服务");
    }

    @Override
    protected void initData() {
        super.initData();
        mBinding.weburl.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mBinding.weburl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LinkedServiceWebActivity.class);
            }
        });
    }
}
