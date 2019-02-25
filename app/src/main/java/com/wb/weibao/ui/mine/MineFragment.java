package com.wb.weibao.ui.mine;

import android.content.Intent;
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
        mTitleBarLayout.setTitle("预警日志");
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
    }
}
