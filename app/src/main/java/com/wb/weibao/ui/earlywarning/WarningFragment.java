package com.wb.weibao.ui.earlywarning;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntWarningBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.earlywarning.ErrorListModel;
import com.wb.weibao.ui.Login.ForgetPwdActivity;

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
        mBinding.tabTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mBinding.pager.setCurrentItem(0);
                mBinding.tabTvLine.setVisibility(View.VISIBLE);
                mBinding.tabTvLine1.setVisibility(View.INVISIBLE);
            }
        });
        mBinding.tabTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.pager.setCurrentItem(1);
                mBinding.tabTvLine.setVisibility(View.INVISIBLE);
                mBinding.tabTvLine1.setVisibility(View.VISIBLE);
            }
        });

    }

    private void initFragment() {
        fireFragment= new FireFragment();
        alarmFragment = new AlarmFragment();
        mFragments.add(fireFragment);
        mFragments.add(alarmFragment);
    }

    public void toLoadData() {

        if (fireFragment != null) {
            fireFragment.toLoadData();
        }
        if (alarmFragment != null) {
            alarmFragment.toLoadData();
        }

     /*   if (mRecordFragment != null) {
            mRecordFragment.loadData();
        }*/
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
