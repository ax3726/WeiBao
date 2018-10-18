package com.wb.weibao.ui.mine;

import com.lm.lib_common.base.BaseFragment;
import com.lm.lib_common.base.BaseFragmentPresenter;
import com.wb.weibao.R;
import com.wb.weibao.databinding.FragemntMainTenanceBinding;
import com.wb.weibao.databinding.FragemntMineBinding;

/**
 * Created by Administrator on 2018/10/8.
 */

public class MineFragment extends BaseFragment<BaseFragmentPresenter, FragemntMineBinding> {


    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setLeftShow(false);
        mTitleBarLayout.setTitle("我的");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_mine;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }
}
