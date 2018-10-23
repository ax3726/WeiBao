package com.wb.weibao.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.utils.DoubleClickExitHelper;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityMainBinding;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.ui.earlywarning.EarlyWarningFragment;
import com.wb.weibao.ui.maintenance.AddOrderActivity;
import com.wb.weibao.ui.maintenance.MainTenanceFragment;
import com.wb.weibao.ui.mine.MineFragment;
import com.wb.weibao.ui.record.RecordFragment;
import com.wb.weibao.view.PopupWindow.FitPopupUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> implements View.OnClickListener {

    private FragmentManager mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();
    private DoubleClickExitHelper mDoubleClickExit;//
    private EarlyWarningFragment mEarlyWarningFragment;
    private RecordFragment mRecordFragment;
    private MainTenanceFragment mMainTenanceFragment;
    private MineFragment mMineFragment;
    private List<ProjectListModel.DataBean.ListBean> mProjectList = new ArrayList<>();//项目列表
    private int mIndex = 0;//当前模块下标

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvName.setOnClickListener(this);
        mBinding.tvAddOrder.setOnClickListener(this);

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
                            mIndex = 0;
                            mBinding.rlyHead.setVisibility(View.VISIBLE);
                            mBinding.tvAddOrder.setVisibility(View.GONE);
                            changeFragment(0);
                        }
                        break;
                    case R.id.rb_record:
                        if (currentFragmentPosition != 1) {
                            mIndex = 1;
                            mBinding.rlyHead.setVisibility(View.VISIBLE);
                            mBinding.tvAddOrder.setVisibility(View.GONE);
                            changeFragment(1);

                        }
                        break;
                    case R.id.rb_weibao:
                        if (currentFragmentPosition != 2) {
                            mIndex = 2;
                            mBinding.rlyHead.setVisibility(View.VISIBLE);
                            mBinding.tvAddOrder.setVisibility(View.VISIBLE);
                            changeFragment(2);
                        }
                        break;
                    case R.id.rb_myuser:
                        if (currentFragmentPosition != 3) {
                            mBinding.rlyHead.setVisibility(View.GONE);
                            mIndex = 3;
                            changeFragment(3);
                        }
                        break;
                }
            }
        });
        getProjectList();
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

    /**
     * 获取项目列表
     */
    private void getProjectList() {
        Api.getApi().getProject_list(MyApplication.getInstance().getUserData().institutions.getCode(),
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            mProjectList.clear();
                            if (data.getList() != null && data.getList().size() > 0) {
                                mProjectList.addAll(data.getList());
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);
                                MyApplication.getInstance().setProjectId(listBean.getInstId());
                                mBinding.tvName.setText(listBean.getInstName());
                                toLoadData();

                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    private void toLoadData() {

        if (mEarlyWarningFragment != null) {
            mEarlyWarningFragment.loadData();
        }
        if (mRecordFragment != null) {
            mRecordFragment.loadData();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_order:
                startActivity(AddOrderActivity.class);
                break;
            case R.id.tv_name:
                List<String> wheelString = new ArrayList<>();
                for (int i = 0; i < mProjectList.size(); i++) {
                    wheelString.add(mProjectList.get(i).getInstName());
                }
                if (wheelString.size() > 0) {
                    FitPopupUtil fitPopupUtil = new FitPopupUtil(aty, wheelString);
                    fitPopupUtil.setOnClickListener(new FitPopupUtil.OnCommitClickListener() {
                        @Override
                        public void onClick(String reason) {
                            ProjectListModel.DataBean.ListBean listBean = mProjectList.get(Integer.parseInt(reason));
                            MyApplication.getInstance().setProjectId(listBean.getInstId());
                            mBinding.tvName.setText(listBean.getInstName());
                            toLoadData();

                        }
                    });
                    fitPopupUtil.showPopup(v);
                }
                break;
        }
    }


}
