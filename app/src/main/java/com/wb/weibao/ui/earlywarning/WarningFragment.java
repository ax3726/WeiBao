package com.wb.weibao.ui.earlywarning;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.databinding.FragemntWarningBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class WarningFragment extends BaseFragment<BaseFragmentPresenter, FragemntWarningBinding> implements ViewPager.OnPageChangeListener {


    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_warning;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setLeftShow(false);
        mTitleBarLayout.setTitle("预警日志");
        mTitleBarLayout.setTextSize(20);
    }
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private FireFragment fireFragment;
    private AlarmFragment alarmFragment;

    @Override
    protected void initData() {
        super.initData();
        initFragment();
        mBinding.pager.setOnPageChangeListener(this);
        mBinding.pager.setAdapter(new EventsPageAdpater(getChildFragmentManager()));

    }

    private void initFragment() {
        fireFragment= new FireFragment();
        alarmFragment = new AlarmFragment();
        mFragments.add(fireFragment);
        mFragments.add(alarmFragment);
    }

    class EventsPageAdpater extends FragmentPagerAdapter {

        public EventsPageAdpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
           mBinding.tabTvLine.setVisibility(View.VISIBLE);
            mBinding.tabTvLine1.setVisibility(View.INVISIBLE);
        } else {
            mBinding.tabTvLine.setVisibility(View.INVISIBLE);
            mBinding.tabTvLine1.setVisibility(View.VISIBLE);
        }
    }
}
