package com.wb.weibao.ui.earlywarning;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wb.weibao.R;
import com.google.android.gms.plus.PlusOneButton;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.databinding.FragemntEarlyWarningBinding;
import com.wb.weibao.databinding.FragmentFireBinding;
import com.wb.weibao.ui.home.HomeFragment;
import com.wb.weibao.ui.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class FireFragment  extends BaseFragment<BaseFragmentPresenter, FragmentFireBinding> {

    private FragmentManager mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();
    private TBCFragment tbcFragment;

   private DCLFragment dclFragment;
   private YCLFragment yclFragment;
    private int mIndex = 0;//当前模块下标
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fire;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        super.initData();
        initFragment();
        mBinding.tabTitleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left_title:
                        if (currentFragmentPosition != 0) {
                            mIndex = 0;

                            changeFragment(0);
                        }
                        break;
                    case R.id.rb_center_title:
                        if (currentFragmentPosition != 1) {
                            mIndex = 1;

                            changeFragment(1);
                        }
                        break;

                    case R.id.rb_right_title:
                        if (currentFragmentPosition != 2) {
                            mIndex = 2;
                            changeFragment(2);
                        }
                        break;
                }
            }
        });
    }

    private void initFragment() {
        tbcFragment= new TBCFragment();
        mFragments.add(tbcFragment);

        dclFragment= new DCLFragment();
        mFragments.add(dclFragment);

        yclFragment= new YCLFragment();
        mFragments.add(yclFragment);

        mFm = getChildFragmentManager();
        mTransaction = mFm.beginTransaction();
        mTransaction.add(R.id.lly_body, tbcFragment);
        mTransaction.show(mFragments.get(0));
        mTransaction.commitAllowingStateLoss();
    }

    private int currentFragmentPosition = 0;

    public void changeFragment(final int position) {
        mFm = getChildFragmentManager();
        mTransaction = mFm.beginTransaction();
        if (position != currentFragmentPosition) {
            mTransaction.hide(mFragments.get(currentFragmentPosition));
            if (!mFragments.get(position).isAdded()) {
                mTransaction.add(R.id.lly_body, mFragments.get(position));
            }
            mTransaction.show(mFragments.get(position));
            mTransaction.commitAllowingStateLoss();
        }
        currentFragmentPosition = position;
    }


}
