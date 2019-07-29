package com.wb.weibao.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivitySmartElectricBinding;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.ui.earlywarning.DCLFragment;
import com.wb.weibao.ui.earlywarning.FireFragment;
import com.wb.weibao.ui.earlywarning.TBCFragment;
import com.wb.weibao.ui.earlywarning.YCLFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SmartElectricActivity extends BaseActivity<BasePresenter, ActivitySmartElectricBinding> {

    private FragmentManager     mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment>      mFragments = new ArrayList<>();
    private TBCFragment         tbcFragment;

    private DCLFragment dclFragment;
    private YCLFragment yclFragment;
    private int         mIndex = 0;//当前模块下标
    private int         mType  = 4;
    private int         mNum   = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_electric;
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
        mTitleBarLayout.setTitle("智慧用电");
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
                           /* if (tbcFragment != null) {
                                tbcFragment.loadData();
                            }*/
                            count();
                        }
                        break;
                    case R.id.rb_center_title:

                        if (currentFragmentPosition != 1) {
                            mIndex = 1;

                            changeFragment(1);
                           /* if (dclFragment != null) {
                                dclFragment.loadData();
                            }*/
                            count();
                        }
                        break;

                    case R.id.rb_right_title:
                        if (currentFragmentPosition != 2) {
                            mIndex = 2;
                            changeFragment(2);
                           /* if (yclFragment != null) {
                                yclFragment.loadData();
                            }*/
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
        mBinding.llyBody.removeAllViews();
        tbcFragment = new TBCFragment();
        tbcFragment.setType(mType);
        mFragments.add(tbcFragment);

        dclFragment = new DCLFragment();
        dclFragment.setType(mType);
        mFragments.add(dclFragment);

        yclFragment = new YCLFragment();
        yclFragment.setType(mType);
        mFragments.add(yclFragment);

        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();

        if (mNum == 1) {
            currentFragmentPosition = 2;
        }
        if (!mFragments.get(currentFragmentPosition).isAdded()) {
            mTransaction.add(R.id.lly_body, mFragments.get(currentFragmentPosition));
        }
        mTransaction.show(mFragments.get(currentFragmentPosition));
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

    public void count() {
        Api.getApi().getRecordcount(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", MyApplication.getInstance().getUserData().getPrincipal().getInstCode() + "", MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordCount>(this, false) {
                    @Override
                    public void onSuccess(RecordCount baseBean) {
                        LogUtils.e("baseBean" + baseBean.toString());
                        RecordCount.DataBean data    = baseBean.getData();
                        int                  num_que = 0;//待确认
                        int                  num_chu = 0;//待处理

                        switch (mType) {
                            case 1://远程监控火警
                                num_que = data.getRemoteMonitoringTbcNum();
                                num_chu = data.getRemoteMonitoringTbpNum();
                                break;
                            case 2://九小场所火警
                                num_que = data.getNineSmallPlacesTbcNum();
                                num_chu = data.getNineSmallPlacesTbpNum();
                                break;
                            case 3://故障111
                                num_que = data.getAlarmNum();
                                break;
                            case 4://用电异常
                                num_que = data.getElectricityFaultTbcNum();
                                num_chu = data.getElectricityFaultTbpNum();
                                break;
                            case 5://用水异常111
                                num_que = data.getWaterFaultTbcNum();
                                num_chu = data.getWaterFaultTbpNum();
                                break;
                            case 6://拆除 防拆待处理
                                num_que = data.getTamperNum();
                                break;
                            case 7://其他

                                break;
                        }

                        if (num_que == 0) {
                            mBinding.rbLeftTitle.setText("待确认");
                        } else if (num_que < 100) {
                            mBinding.rbLeftTitle.setText("待确认(" + num_que + ")");
                        } else {
                            mBinding.rbLeftTitle.setText("待确认(99+)");
                        }

                        if (num_chu == 0) {
                            mBinding.rbCenterTitle.setText("待处理");
                        } else if (num_chu < 100) {
                            mBinding.rbCenterTitle.setText("待处理(" + num_chu + ")");
                        } else {
                            mBinding.rbCenterTitle.setText("待处理(99+)");
                        }


                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
