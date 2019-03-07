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

import com.lidroid.xutils.util.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wb.weibao.R;
import com.google.android.gms.plus.PlusOneButton;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntEarlyWarningBinding;
import com.wb.weibao.databinding.FragmentFireBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.ui.Login.ForgetPwdActivity;
import com.wb.weibao.ui.home.HomeFragment;
import com.wb.weibao.ui.mine.MineFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        count();
        EventBus.getDefault().register(this);
        mBinding.tabTitleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left_title:

                        if (currentFragmentPosition != 0) {

                            mIndex = 0;
                            changeFragment(0);
                            if (tbcFragment != null) {
                                tbcFragment.loadData();
                            }
                            count();
                        }
                        break;
                    case R.id.rb_center_title:

                        if (currentFragmentPosition != 1) {
                            mIndex = 1;

                            changeFragment(1);
                            if (dclFragment != null) {
                                dclFragment.loadData();
                            }
                            count();
                        }
                        break;

                    case R.id.rb_right_title:
                        if (currentFragmentPosition != 2) {
                            mIndex = 2;
                            changeFragment(2);
                            if (yclFragment != null) {
                                yclFragment.loadData();
                            }
                            count();
                        }

                        break;
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(RecordDetailEvent event) {
        count();

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

    public void toLoadData() {

        count();
        if (tbcFragment != null) {
            tbcFragment.loadData();

        }
        if (dclFragment != null) {
            dclFragment.loadData();

        }
        if (yclFragment != null) {
            yclFragment.loadData();

        }
     /*   if (mRecordFragment != null) {
            mRecordFragment.loadData();
        }*/
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

   public  void count()
   {
       Api.getApi().getRecordcount(MyApplication.getInstance().getUserData().getId() + "",MyApplication.getInstance().getUserData().getCompanyId(),MyApplication.getInstance().getProjectId())
               .compose(callbackOnIOToMainThread())
               .subscribe(new BaseNetListener<RecordCount>(FireFragment.this, false) {
                   @Override
                   public void onSuccess(RecordCount baseBean) {
                       LogUtils.e("baseBean" + baseBean.toString());
                        mBinding.rbLeftTitle.setText("待确认("+baseBean.getData().getFireWaitConfirmNum()+")");
                        mBinding.rbCenterTitle.setText("待处理("+baseBean.getData().getFireWaitProccessNum()+")");

                   }

                   @Override
                   public void onFail(String errMsg) {

                   }
               });
   }

}
