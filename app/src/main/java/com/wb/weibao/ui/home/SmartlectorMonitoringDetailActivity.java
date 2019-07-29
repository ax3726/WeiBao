package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.databinding.ActivitySmartlectorMonitoringBinding;
import com.wb.weibao.databinding.ActivitySmartlectorMonitoringDetailBinding;
import com.wb.weibao.databinding.ItemSmartelectormonitorLayoutBinding;
import com.wb.weibao.databinding.ItemSmartelectormonitordetailLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.SmartElectorBean;
import com.wb.weibao.model.home.SmartElectorDetailBean;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

public class SmartlectorMonitoringDetailActivity extends BaseActivity<BasePresenter,ActivitySmartlectorMonitoringDetailBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_smartlector_monitoring_detail;
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
        mTitleBarLayout.setTitle("智慧用电电路监控");


    }




    private List<SmartElectorDetailBean.DataBean.CollectorPointsBean> mDataList = new ArrayList<>();
    private CommonAdapter<SmartElectorDetailBean.DataBean.CollectorPointsBean> mAdapter;
    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<SmartElectorDetailBean.DataBean.CollectorPointsBean>(aty, R.layout.item_smartelectormonitordetail_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, SmartElectorDetailBean.DataBean.CollectorPointsBean listBean, int position) {
                ItemSmartelectormonitordetailLayoutBinding binding = holder.getBinding(ItemSmartelectormonitordetailLayoutBinding.class);
                binding.tv01.setText(mDataList.get(position).getEquipmentName());
                binding.tv02.setText(mDataList.get(position).getMeasuredData());
                binding.tv03.setText(mDataList.get(position).getAlarmThreshold());
            }
        };

        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        getPowerDetailApp();

    }

    /**
     * 获取电路监控列表详情
     */
    private void getPowerDetailApp() {

        Api.getApi().getPowerDetailApp(getIntent().getStringExtra("id"))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<SmartElectorDetailBean>(this, false) {
                    @Override
                    public void onSuccess(SmartElectorDetailBean baseBean) {
                        mBinding.tv1.setText(mBinding.tv1.getText()+"     "+baseBean.getData().getId());
                        mBinding.tv2.setText(mBinding.tv2.getText()+"     "+baseBean.getData().getCode());
                        mBinding.tv3.setText(mBinding.tv3.getText()+"     "+baseBean.getData().getPosition());
                        mBinding.tv4.setText(mBinding.tv4.getText()+"     "+baseBean.getData().getLotCard());
                        String CreateTime =baseBean.getData().getUpdateTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(baseBean.getData().getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
                        mBinding.tv5.setText(mBinding.tv5.getText()+"     "+CreateTime);
                        SmartElectorDetailBean.DataBean data = baseBean.getData();
                        if (data != null) {
                            List<SmartElectorDetailBean.DataBean.CollectorPointsBean> list = data.getCollectorPoints();
                            if (list != null && list.size() > 0) {
                                mDataList.addAll(list);
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFail(String errMsg) {


                    }
                });


    }



}