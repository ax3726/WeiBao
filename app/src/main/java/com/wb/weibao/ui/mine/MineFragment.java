package com.wb.weibao.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.R;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntMineBinding;
import com.wb.weibao.ui.Login.LoginActivity;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;

/**
 * Created by Administrator on 2018/10/8.
 */

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
        mBinding.tvPhone.setText(MyApplication.getInstance().getUserData().getName());
        mBinding.tvWeizhi.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
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
