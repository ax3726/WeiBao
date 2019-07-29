package com.wb.weibao.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityFireControlBinding;
import com.wb.weibao.model.home.FrieControlModel;

import java.util.List;

public class FireControlActivity extends BaseActivity<BasePresenter, ActivityFireControlBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fire_control;
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
        mTitleBarLayout.setTitle("消防微站");
    }

    @Override
    protected void initData() {
        super.initData();
        getDataList();
    }

    public void getDataList() {
        Api.getApi().getFireControl(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<FrieControlModel>(this, true) {
                    @Override
                    public void onSuccess(FrieControlModel baseBean) {

                        initView(baseBean);
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    private void initView(FrieControlModel baseBean) {
        if (baseBean.getData() == null) {
            return;
        }

        List<FrieControlModel.DataBean.ListBean> list = baseBean.getData().getList();
        if (list != null && list.size() > 0) {
            FrieControlModel.DataBean.ListBean listBean = list.get(0);
            mBinding.tvName.setText(TextUtils.isEmpty(listBean.getName()) ? "" : listBean.getName());
            mBinding.tvPhone.setText(TextUtils.isEmpty(listBean.getPhone()) ? "" : listBean.getPhone());
            mBinding.tvAddress.setText(TextUtils.isEmpty(listBean.getAreaDetail()) ? "" : listBean.getAreaDetail());
            mBinding.tvZhan.setText(TextUtils.isEmpty(listBean.getMaster()) ? "" : listBean.getMaster());
            mBinding.tvZhanPhone.setText(TextUtils.isEmpty(listBean.getMasterPhone()) ? "" : listBean.getMasterPhone());

        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(mBinding.tvPhone.getText().toString().trim());
            }
        });
        mBinding.imgZhanPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(mBinding.tvZhanPhone.getText().toString().trim());
            }
        });
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
