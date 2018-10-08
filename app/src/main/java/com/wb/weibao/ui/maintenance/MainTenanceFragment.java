package com.wb.weibao.ui.maintenance;

import com.lm.lib_common.base.BaseFragment;
import com.lm.lib_common.base.BaseFragmentPresenter;
import com.wb.weibao.R;
import com.wb.weibao.databinding.FragemntEarlyWarningBinding;
import com.wb.weibao.databinding.FragemntMainTenanceBinding;

/**
 * Created by Administrator on 2018/10/8.
 */

public class MainTenanceFragment extends BaseFragment<BaseFragmentPresenter,FragemntMainTenanceBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_main_tenance;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }
}
