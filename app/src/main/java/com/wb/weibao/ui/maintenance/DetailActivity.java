package com.wb.weibao.ui.maintenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.lm.lib_common.utils.MD5Utils;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityDetailBinding;
import com.wb.weibao.ui.Login.LoginActivity;

public class DetailActivity extends BaseActivity<BasePresenter,ActivityDetailBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
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
        mTitleBarLayout.setTitle("新增维保订单");
    }
    private String mId = "";
    private String muserId = "";
    @Override
    protected void initData() {
        super.initData();
       mId=getIntent().getStringExtra("id");
       muserId=getIntent().getStringExtra("userId");
        Api.getApi().getorderDetail(muserId, mId)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }
}
