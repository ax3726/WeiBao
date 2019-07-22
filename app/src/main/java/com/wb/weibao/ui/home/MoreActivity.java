package com.wb.weibao.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityMoreBinding;
import com.wb.weibao.model.home.MaintenanceListModel;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends BaseActivity<BasePresenter,ActivityMoreBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_more;
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
        mTitleBarLayout.setTitle("更多功能");
    }

    @Override
    protected void initData() {
        super.initData();


        mBinding.tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InitiateWeibaoActivity.class);
            }
        });
        mBinding.tv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(MySecurityActivity.class);

            }
        });
        mBinding.tv03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignActivity.class);
            }
        });
        mBinding.tv04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChangeShiftsActivity.class);
            }
        });
        getDataList();
    }

    /**
     * 我的维保
     */
    public void getDataList() {
        Api.getApi().getMyWeiBao2(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<MaintenanceListModel>(this, true) {
                    @Override
                    public void onSuccess(MaintenanceListModel baseBean) {
                        if (baseBean.getData() != null) {
                            List<MaintenanceListModel.DataBean.ListBean> list = baseBean.getData().getList();
                            if (list != null && list.size() > 0) {
                               mBinding.imageRed.setVisibility(View.VISIBLE);
                            }else
                                {
                                    mBinding.imageRed.setVisibility(View.GONE);
                                }


                        }


                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

}
