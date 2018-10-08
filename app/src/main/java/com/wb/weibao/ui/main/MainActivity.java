package com.wb.weibao.ui.main;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.lm.lib_common.utils.DoubleClickExitHelper;
import com.lm.lib_common.utils.MD5Utils;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.databinding.ActivityMainBinding;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.ui.Login.LoginActivity;
import com.wb.weibao.view.CustomPopWindow;
import com.wb.weibao.view.WheelStyle;
import com.wb.weibao.view.WheelView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> {


    private FragmentManager mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();
    private DoubleClickExitHelper mDoubleClickExit;//

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
        LoginModel loginModel= (LoginModel) getIntent().getSerializableExtra("loginModel");
          Api.getApi().getProject_list(loginModel.data.institutions.getCode(), ""+loginModel.data.userRoles.get(0).userId).compose(callbackOnIOToMainThread())
            .subscribe(new BaseNetListener<BaseBean>(MainActivity.this, false) {
        @Override
        public void onSuccess(BaseBean baseBean) {
            Log.e("====",baseBean.toString());

        }

        @Override
        public void onFail(String errMsg) {

        }
    });
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
    }

    private void initFragment() {


//        mFragments.add();
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();
//        mTransaction.add(R.id.lly_body, mHomeFragment);
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
     * 点击 PopupWindow 之外的地方不消失
     */
    private void touchOutsideDontDisMiss(){
//        View view = LayoutInflater.from(this).inflate(R.layout.pop_layout_close,null);
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("FK","onClick.....");
//                mPopWindow.dissmiss();
//            }
//        };
//        view.findViewById(R.id.close_pop).setOnClickListener(listener);
//        wheel = (WheelView) view.findViewById(R.id.wheel_week_wheel);
//
//        wheel.setWheelStyle(WheelStyle.STYLE_LIGHT_TIME);
//        mPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
//                .setView(view)
//                .enableBackgroundDark(true)//弹出popWindow时，背景是否变暗
//                .enableOutsideTouchableDissmiss(false)// 设置点击PopupWindow之外的地方，popWindow不关闭，如果不设置这个属性或者为true，则关闭
//                .create();
//
//        mPopWindow.showAsDropDown(mButton7,0,10);
    }


}
