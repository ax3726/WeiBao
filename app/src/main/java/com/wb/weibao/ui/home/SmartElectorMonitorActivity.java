package com.wb.weibao.ui.home;


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
import com.wb.weibao.databinding.ActivitySmartElectorMonitorBinding;
import com.wb.weibao.databinding.ItemSmartelectormonitorLayoutBinding;
import com.wb.weibao.model.home.RecordListAppBean;
import com.wb.weibao.model.home.SmartElectorBean;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.DemoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartElectorMonitorActivity extends BaseActivity<BasePresenter, ActivitySmartElectorMonitorBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_elector_monitor;
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
        mTitleBarLayout.setTitle("智慧用电路监控");

    }

    private List<SmartElectorBean.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<SmartElectorBean.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    private String name = "";

    @Override
    protected void initData() {
        super.initData();



        mAdapter = new CommonAdapter<SmartElectorBean.DataBean.ListBean>(aty, R.layout.item_smartelectormonitor_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, SmartElectorBean.DataBean.ListBean listBean, int position) {
                showToast("" + mDataList.size());
                ItemSmartelectormonitorLayoutBinding binding = holder.getBinding(ItemSmartelectormonitorLayoutBinding.class);
                binding.tv01.setText(mDataList.get(position).getName());
                binding.tv02.setText(mDataList.get(position).getStatusName());
                String CreateTime = mDataList.get(position).getUpdateTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(mDataList.get(position).getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
                binding.tv03.setText(CreateTime);
            }
        };

        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);


        mBinding.srlBody.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                getErrorList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mBinding.srlBody.resetNoMoreData();
                mPage = 1;
                getErrorList();
            }
        });

        getErrorList();


    }


    public void loadData() {
        mPage = 1;
        getErrorList();
    }


    /**
     * 获取电路监控列表
     */
    private void getErrorList() {
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.execute(new Runnable() {
            @Override
            public void run() {
                Api.getApi().getPowerListApp("" + mPage, "" + mPageSize)
                        .subscribe(new BaseNetListener<SmartElectorBean>(SmartElectorMonitorActivity.this, false) {
                            @Override
                            public void onSuccess(SmartElectorBean baseBean) {

                                stopRefersh();
                                SmartElectorBean.DataBean data = baseBean.getData();
                                if (data != null) {
                                    if (mPage == 1) {
                                        mDataList.clear();
                                    }
                                    List<SmartElectorBean.DataBean.ListBean> list = data.getList();
                                    if (list != null && list.size() > 0) {
                                        mDataList.addAll(list);
                                        if (list.size() < mPageSize) {
                                            mBinding.srlBody.finishLoadmoreWithNoMoreData();
                                        }
                                    }
                                    mAdapter.notifyDataSetChanged();

                                }

                            }

                            @Override
                            public void onFail(String errMsg) {

                                stopRefersh();
                            }
                        });
            }

        });
        executorService2.shutdown();

    }


    private void stopRefersh() {
        mBinding.srlBody.finishRefresh();
        mBinding.srlBody.finishLoadmore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}

