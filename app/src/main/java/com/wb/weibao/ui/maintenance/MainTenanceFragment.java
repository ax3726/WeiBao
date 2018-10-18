package com.wb.weibao.ui.maintenance;

import android.graphics.Color;
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
import com.wb.weibao.model.earlywarning.OrderListModel;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.utils.DemoUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class MainTenanceFragment extends BaseFragment<BaseFragmentPresenter, FragemntMainTenanceBinding> {

    private List<OrderListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<OrderListModel.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 15;
    private DecimalFormat df=new DecimalFormat("0.00");
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
        mAdapter = new CommonAdapter<OrderListModel.DataBean.ListBean>(aty, R.layout.item_maintenance_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, OrderListModel.DataBean.ListBean item, int position) {
                ItemMaintenanceLayoutBinding binding = holder.getBinding(ItemMaintenanceLayoutBinding.class);

                switch (item.getStatus()) {
                    case "1":
                        binding.tvHint.setText("待平台定价");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case "2":
                        binding.tvHint.setText("用户撤销");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color999999));
                        break;
                    case "3":
                        binding.tvHint.setText("代交预付款");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case "4":
                        binding.tvHint.setText("付款失败");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color999999));
                        break;
                    case "5":
                        binding.tvHint.setText("待维保");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case "6":
                        binding.tvHint.setText("维保中");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case "7":
                        binding.tvHint.setText("失效");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color999999));
                        break;
                    default:
                        binding.tvHint.setText("完成");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color999999));
                        break;
                }

                binding.tvName.setText(item.getOrderNo());
                binding.tvPrice.setText(df.format(item.getAmount()));
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getCreateTime(), "yyyy.MM.dd"));
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
                .subscribe(new BaseNetListener<OrderListModel>(this, false) {
                    @Override
                    public void onSuccess(OrderListModel baseBean) {
                        stopRefersh();
                        OrderListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<OrderListModel.DataBean.ListBean> list = data.getList();
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


    private void stopRefersh() {
        mBinding.srlBody.finishRefresh();
        mBinding.srlBody.finishLoadmore();
    }


}
