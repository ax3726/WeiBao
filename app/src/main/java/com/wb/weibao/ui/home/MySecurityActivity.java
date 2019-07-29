package com.wb.weibao.ui.home;

import android.support.v4.app.Fragment;

import com.wb.weibao.R;
import com.wb.weibao.adapters.CommonPagerAdapter;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityMySecurityBinding;
import com.wb.weibao.model.home.MaintenanceListModel;

import java.util.ArrayList;
import java.util.List;

public class MySecurityActivity extends BaseActivity<BasePresenter, ActivityMySecurityBinding> {
    private List<String> title = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private CommonPagerAdapter mMyPagerAdapter;

    private int mType=-1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_security;
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

        mType = getIntent().getIntExtra("type",-1);
        if (mType == 1) {
            mTitleBarLayout.setTitle("维保订单");
        } else {
            mTitleBarLayout.setTitle("我的维保");
        }

    }

    private SecurityFragment mSecurityFragment1 = null;
    private SecurityFragment mSecurityFragment2 = null;
    private SecurityFragment mSecurityFragment3 = null;
    private SecurityFragment mSecurityFragment4 = null;

    @Override
    protected void initData() {
        super.initData();
        title.add("全部");
        title.add("待审批");
        title.add("待维保");
        title.add("已完成");
        mSecurityFragment1 = new SecurityFragment().setType(mType);
        mSecurityFragment2 = new SecurityFragment().setType(mType);
        mSecurityFragment3 = new SecurityFragment().setType(mType);
        mSecurityFragment4 = new SecurityFragment().setType(mType);

        fragments.add(mSecurityFragment1);
        fragments.add(mSecurityFragment2);
        fragments.add(mSecurityFragment3);
        fragments.add(mSecurityFragment4);

        initTablayout();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataList();
    }

    private void initTablayout() {
        mMyPagerAdapter = new CommonPagerAdapter(getSupportFragmentManager(), title, fragments);
        mBinding.vpBody.setAdapter(mMyPagerAdapter);
        mBinding.vpBody.setOffscreenPageLimit(4);
        mBinding.tbHead.setupWithViewPager(mBinding.vpBody);
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

                                List<MaintenanceListModel.DataBean.ListBean> list2 = new ArrayList<>();//待审批
                                List<MaintenanceListModel.DataBean.ListBean> list3 = new ArrayList<>();//待维保
                                List<MaintenanceListModel.DataBean.ListBean> list4 = new ArrayList<>();//已完成
                                for (MaintenanceListModel.DataBean.ListBean bean : list) {
                                    if ("1".equals(bean.getStatus())) {//待审批
                                        list2.add(bean);
                                    } else if ("3".equals(bean.getStatus())) {//待维保
                                        list3.add(bean);
                                    } else if ("4".equals(bean.getStatus())) {//已完成
                                        list4.add(bean);
                                    }

                                }
                                mSecurityFragment1.setData(list);
                                mSecurityFragment2.setData(list2);
                                mSecurityFragment3.setData(list3);
                                mSecurityFragment4.setData(list4);


                            }


                        }


                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

}
