package com.wb.weibao.ui.record;

import com.lm.lib_common.base.BaseFragment;
import com.lm.lib_common.base.BaseFragmentPresenter;
import com.wb.weibao.R;
import com.wb.weibao.databinding.FragemntMineBinding;
import com.wb.weibao.databinding.FragemntRecordBinding;

/**
 * Created by Administrator on 2018/10/8.
 */

public class RecordFragment extends BaseFragment<BaseFragmentPresenter,FragemntRecordBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_record;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }
}
