package com.wb.weibao.ui.home;

import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.databinding.FragmentHomeLayoutBinding;

/**
 * Created by LiMing
 * Date 2019/2/15
 */
public class HomeFragment extends BaseFragment<BaseFragmentPresenter, FragmentHomeLayoutBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvSign.setOnClickListener(this);
        mBinding.tvDayRecord.setOnClickListener(this);
        mBinding.tvHandover.setOnClickListener(this);
        mBinding.tvAddWeibao.setOnClickListener(this);
        mBinding.tvMyWeibao.setOnClickListener(this);
        mBinding.tvWarningRecord.setOnClickListener(this);
        mBinding.tvLookGang.setOnClickListener(this);
        mBinding.tvWeibaoOrder.setOnClickListener(this);
        mBinding.tvFireControl.setOnClickListener(this);
        mBinding.tvPeixun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign://值班签到

                break;
            case R.id.tv_day_record://日常维保记录

                break;
            case R.id.tv_handover://交接班

                break;
            case R.id.tv_add_weibao://发起维保

                break;
            case R.id.tv_my_weibao://我的维保

                break;
            case R.id.tv_warning_record://警报统计

                break;
            case R.id.tv_look_gang://查岗

                break;
            case R.id.tv_weibao_order://维保订单

                break;
            case R.id.tv_fire_control://消防微岗

                break;
            case R.id.tv_peixun://培训教育

                break;
        }
    }
}
