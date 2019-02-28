package com.wb.weibao.ui.home;

import android.support.v7.widget.LinearLayoutManager;

import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.databinding.FragmentSecurityBinding;

import java.util.ArrayList;
import java.util.List;

public class SecurityFragment extends BaseFragment<BaseFragmentPresenter, FragmentSecurityBinding> {

    private List<String> mDataList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_security;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mAdapter = new CommonAdapter<String>(aty, R.layout.item_security, mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
    }


}
