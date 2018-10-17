package com.wb.weibao.ui.maintenance;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lm.lib_common.adapters.recyclerview.CommonAdapter;
import com.lm.lib_common.adapters.recyclerview.base.ViewHolder;
import com.lm.lib_common.base.BaseFragment;
import com.lm.lib_common.base.BaseFragmentPresenter;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.model.BaseBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntEarlyWarningBinding;
import com.wb.weibao.databinding.FragemntMainTenanceBinding;
import com.wb.weibao.databinding.ItemEarlyWarningLayoutBinding;
import com.wb.weibao.databinding.ItemMaintenanceLayoutBinding;
import com.wb.weibao.model.earlywarning.ErrorListModel;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class MainTenanceFragment extends BaseFragment<BaseFragmentPresenter, FragemntMainTenanceBinding> implements View.OnClickListener {

    private List<ErrorListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<ErrorListModel.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 15;
    private List<ProjectListModel.DataBean.ListBean> mProjectList = new ArrayList<>();//项目列表

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_main_tenance;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter = new CommonAdapter<ErrorListModel.DataBean.ListBean>(aty, R.layout.item_maintenance_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, ErrorListModel.DataBean.ListBean item, int position) {
                ItemMaintenanceLayoutBinding binding = holder.getBinding(ItemMaintenanceLayoutBinding.class);
                binding.tvName.setText(item.getProjectArea());
                binding.tvPrice.setText(item.getProjectName());
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(), "yyyy.MM.dd"));
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);


        mBinding.srlBody.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                getDataList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mBinding.srlBody.resetNoMoreData();
                mPage = 1;
                getDataList();
            }
        });

        getDataList();
    }

    /**
     * 获取订单列表
     */
    private void getDataList() {
        Api.getApi().getOrderList(
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId, mPage, mPageSize)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, false) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        stopRefersh();
                     /*   ErrorListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<ErrorListModel.DataBean.ListBean> list = data.getList();
                            if (list != null && list.size() > 0) {
                                mDataList.addAll(list);
                                if (list.size() < mPageSize) {
                                    mBinding.srlBody.finishLoadmoreWithNoMoreData();
                                }
                            }
                            mAdapter.notifyDataSetChanged();
                        }*/

                    }

                    @Override
                    public void onFail(String errMsg) {
                        stopRefersh();
                    }
                });
    }


    private void stopRefersh() {
        mBinding.srlBody.finishRefresh();
        mBinding.srlBody.finishLoadmore();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvAddOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_order:
                startActivity(AddOrderActivity.class);
                break;
        }
    }
}
