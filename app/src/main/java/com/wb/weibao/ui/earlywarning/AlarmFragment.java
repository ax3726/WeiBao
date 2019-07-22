package com.wb.weibao.ui.earlywarning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragmentAlarmBinding;
import com.wb.weibao.databinding.FragmentFireBinding;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordDetailEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends BaseFragment<BaseFragmentPresenter, FragmentAlarmBinding> {

    private FragmentManager mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();
    private TBCFragment tbcFragment;

    private AlarmDQRFragment alarmDQRFragment;
    private AlarmYCLFragment alarmYCLFragment;
    private int mIndex = 0;//当前模块下标
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_alarm;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        super.initData();
        count();
        initFragment();
        EventBus.getDefault().register(this);
        mBinding.tabTitleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left_title:

                        if (currentFragmentPosition != 0) {

                            mIndex = 0;
                            changeFragment(0);
                            count();
                        }
                        break;

                    case R.id.rb_right_title:
                        if (currentFragmentPosition != 1) {
                            mIndex = 1;
                            changeFragment(1);

                        }
                        break;
                }
            }
        });
    }

    private void initFragment() {


        alarmDQRFragment= new AlarmDQRFragment();
        mFragments.add(alarmDQRFragment);

        alarmYCLFragment= new AlarmYCLFragment();
        mFragments.add(alarmYCLFragment);

        mFm = getChildFragmentManager();
        mTransaction = mFm.beginTransaction();
        mTransaction.add(R.id.lly_body, alarmDQRFragment);
        mTransaction.show(mFragments.get(0));
        mTransaction.commitAllowingStateLoss();
    }

    public void toLoadData() {
        count();
        if (alarmDQRFragment != null) {
            alarmDQRFragment.loadData();
        }
        if (alarmYCLFragment != null) {
            alarmYCLFragment.loadData();
        }

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
        Api.getApi().getRecordcount(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordCount>(AlarmFragment.this, false) {
                    @Override
                    public void onSuccess(RecordCount baseBean) {
                        LogUtils.e("baseBean" + baseBean.toString());
//                        mBinding.rbLeftTitle.setText("待确认("+baseBean.getData().getAlarmWaitProccessNum()+")");

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(RecordDetailEvent event) {
        count();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}

