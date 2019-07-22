package com.wb.weibao.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.wb.weibao.BuildConfig;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.R;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntMineBinding;
import com.wb.weibao.ui.Login.LoginActivity;
import com.wb.weibao.utils.AppManager;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;



public class MineFragment extends BaseFragment<BaseFragmentPresenter, FragemntMineBinding> {


    private SpfUtils spfUtils;

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setLeftShow(false);
        mTitleBarLayout.setTitle("我的");
        mTitleBarLayout.setTextSize(20);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_mine;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        spfUtils = SpfUtils.getInstance(aty);
        mBinding.tvPhone.setText(MyApplication.getInstance().getUserData().getPrincipal().getName());
        if (MyApplication.getInstance().getUserData().getPrincipal().getType().equals("1")) {

            mBinding.tvWeizhi.setText(MyApplication.getInstance().getUserData().getPrincipal().getInstName());

        } else {

//            mBinding.tvWeizhi.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
            mBinding.tvWeizhi.setText(MyApplication.getInstance().getUserData().getPrincipal().getProjectName());
        }

        String  str= spfUtils.getSpfString(SpfKey.IS_PUSH_PLAY);
        if(TextUtils.isEmpty(str))
        {
            str="ok";
            spfUtils.setSpfString(SpfKey.IS_PUSH_PLAY,str);
        }
        mBinding.swBtn.setChecked("ok".equals(str));

        mBinding.swBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                spfUtils.setSpfString(SpfKey.IS_PUSH_PLAY, isChecked ? "ok":"nook");


            }
        });
        mBinding.pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aty, pwdActivity.class);
                startActivity(intent);
            }
        });
        mBinding.btnTui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.class);
                aty.finish();
                AppManager.finishAllActivity();
                MyApplication.getInstance().exit();
                spfUtils.setSpfString(SpfKey.IS_LOGIN, "");

            }
        });
        mBinding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("0571-56260119");
            }
        });
        mBinding.jianyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProposalActivity.class);
            }
        });

        mBinding.banben.setText("当前版本"+ BuildConfig.VERSION_NAME);
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
