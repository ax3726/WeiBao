package com.wb.weibao.ui.main;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.utils.DoubleClickExitHelper;
import com.wb.weibao.R;
import com.wb.weibao.databinding.ActivityMainBinding;
import com.wb.weibao.ui.earlywarning.EarlyWarningFragment;
import com.wb.weibao.ui.maintenance.MainTenanceFragment;
import com.wb.weibao.ui.mine.MineFragment;
import com.wb.weibao.ui.record.RecordFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> {


    private FragmentManager mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();
    private DoubleClickExitHelper mDoubleClickExit;//
    private EarlyWarningFragment mEarlyWarningFragment;
    private RecordFragment mRecordFragment;
    private MainTenanceFragment mMainTenanceFragment;
    private MineFragment mMineFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();

        mBinding.rgButtom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_forewarning:
                        if (currentFragmentPosition != 0) {
                            changeFragment(0);
                        }
                        break;
                    case R.id.rb_record:
                        if (currentFragmentPosition != 1) {
                            changeFragment(1);
                        }
                        break;
                    case R.id.rb_weibao:
                        if (currentFragmentPosition != 2) {
                            changeFragment(2);
                        }
                        break;
                    case R.id.rb_myuser:
                        if (currentFragmentPosition != 3) {
                            changeFragment(3);
                        }
                        break;
                }
            }
        });

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDoubleClickExit = new DoubleClickExitHelper(this);
        initFragment();
    }

    private void initFragment() {
        mEarlyWarningFragment = new EarlyWarningFragment();
        mRecordFragment = new RecordFragment();
        mMainTenanceFragment = new MainTenanceFragment();
        mMineFragment = new MineFragment();

        mFragments.add(mEarlyWarningFragment);
        mFragments.add(mRecordFragment);
        mFragments.add(mMainTenanceFragment);
        mFragments.add(mMineFragment);
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();
        mTransaction.add(R.id.lly_body, mEarlyWarningFragment);
        mTransaction.show(mFragments.get(0));
        mTransaction.commitAllowingStateLoss();
    }

    private int currentFragmentPosition = 0;

    public void changeFragment(final int position) {
        mFm = getSupportFragmentManager();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClickExit.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }




}
