package com.wb.weibao.ui.home;

import android.support.v7.widget.LinearLayoutManager;

import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityTrainingEducationBinding;

import java.util.ArrayList;
import java.util.List;

public class TrainingEducationActivity extends BaseActivity<BasePresenter, ActivityTrainingEducationBinding> {
    private List<String> mDataList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_training_education;
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
        mTitleBarLayout.setTitle("培训教育");
    }

    @Override
    protected void initData() {
        super.initData();
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mAdapter = new CommonAdapter<String>(aty, R.layout.item_train_education_layout, mDataList) {
//            @Override
//            protected void convert(ViewHolder holder, String s, int position) {
//
//            }
//        };
//        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
//        mBinding.rcBody.setAdapter(mAdapter);
    }
}
