package com.wb.weibao.ui.home;

import android.support.v4.app.Fragment;

import com.wb.weibao.R;
import com.wb.weibao.adapters.CommonPagerAdapter;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityMySecurityBinding;

import java.util.ArrayList;
import java.util.List;

public class MySecurityActivity extends BaseActivity<BasePresenter, ActivityMySecurityBinding> {
    private List<String> title = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private CommonPagerAdapter mMyPagerAdapter;

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
        mTitleBarLayout.setTitle("我的维保");
    }

    @Override
    protected void initData() {
        super.initData();
        title.add("全部");
        title.add("待审批");
        title.add("待维保");
        title.add("已完成");
        fragments.add(new SecurityFragment());
        fragments.add(new SecurityFragment());
        fragments.add(new SecurityFragment());
        fragments.add(new SecurityFragment());

        initTablayout();
    }

    private void initTablayout() {
        mMyPagerAdapter = new CommonPagerAdapter(getSupportFragmentManager(), title, fragments);
        mBinding.vpBody.setAdapter(mMyPagerAdapter);
        mBinding.vpBody.setOffscreenPageLimit(4);
        mBinding.tbHead.setupWithViewPager(mBinding.vpBody);
    }
}
