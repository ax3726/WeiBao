package com.wb.weibao.ui.home;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityNoDataBinding;

public class NoDataActivity extends BaseActivity<BasePresenter, ActivityNoDataBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_no_data;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        int type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            mTitleBarLayout.setTitle("水压监控");
        } else if (type == 2) {
            mTitleBarLayout.setTitle("水位监控");
        } else if (type == 3) {
            mTitleBarLayout.setTitle("电气监控");
        }

    }
}
