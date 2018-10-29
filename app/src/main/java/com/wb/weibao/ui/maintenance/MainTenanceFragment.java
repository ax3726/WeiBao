package com.wb.weibao.ui.maintenance;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.lm.lib_common.adapters.recyclerview.CommonAdapter;
import com.lm.lib_common.adapters.recyclerview.base.ViewHolder;
import com.lm.lib_common.base.BaseFragment;
import com.lm.lib_common.base.BaseFragmentPresenter;
import com.lm.lib_common.base.BaseNetListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntMainTenanceBinding;
import com.wb.weibao.databinding.ItemMaintenanceLayoutBinding;
import com.wb.weibao.model.earlywarning.OrderListModel;
import com.wb.weibao.model.event.AddOderEvent;
import com.wb.weibao.model.main.ChooseTypeModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.view.PopupWindow.ChooseTypePopupwindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class MainTenanceFragment extends BaseFragment<BaseFragmentPresenter, FragemntMainTenanceBinding> {

    private List<OrderListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<OrderListModel.DataBean.ListBean> mAdapter;
    private OrderListModel.DataBean data;
    private int mPage = 1;
    private int mPageSize = 15;
    private DecimalFormat df=new DecimalFormat("0.00");
    private String name="";
    private List<ChooseTypeModel> mChooseList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_main_tenance;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    private void initChooseData(){
        mChooseList.add(new ChooseTypeModel("全部", ""));
        mChooseList.add(new ChooseTypeModel("待平台定价", "1"));
        mChooseList.add(new ChooseTypeModel("用户撤销", "2"));
        mChooseList.add(new ChooseTypeModel("代交预付款", "3"));
        mChooseList.add(new ChooseTypeModel("付款失败", "4"));
        mChooseList.add(new ChooseTypeModel("待维保", "5"));
        mChooseList.add(new ChooseTypeModel("维保中", "6"));
        mChooseList.add(new ChooseTypeModel("失效", "7"));
        mChooseList.add(new ChooseTypeModel("完成", "8"));

    }
    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        initChooseData();
        mAdapter = new CommonAdapter<OrderListModel.DataBean.ListBean>(aty, R.layout.item_maintenance_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, OrderListModel.DataBean.ListBean item, int position) {
                ItemMaintenanceLayoutBinding binding = holder.getBinding(ItemMaintenanceLayoutBinding.class);
                RelativeLayout rly_item = holder.getView(R.id.rly_item);
                rly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(aty, DetailActivity.class);
                        intent.putExtra("userId", item.getUserId().toString());
                        intent.putExtra("id", ""+item.getId());
                        intent.putExtra("status",item.getStatus());
                        intent.putExtra("hasProcessing",""+data.isHasProcessing());
                        startActivity(intent);
                    }
                });
                switch (item.getStatus()) {
                    case "1":
                        binding.tvHint.setText("待平台定价");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case "2":
                        binding.tvHint.setText("用户撤销");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorF15453));
                        break;
                    case "3":
                        binding.tvHint.setText("代交预付款");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case "4":
                        binding.tvHint.setText("付款失败");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorF15453));
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
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorF15453));
                        break;
                    default:
                        binding.tvHint.setText("完成");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorF15453));
                        break;
                }

                binding.tvName.setText(item.getPrincipalName());
               String type= item.getType().equals("1")?"维保\t￥":"质检\t￥";
                binding.tvPrice.setText(type+df.format(item.getAmount()));
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getCreateTime(), "yyyy.MM.dd"));
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        mBinding.rcBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId, mPage, mPageSize,name)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<OrderListModel>(this, false) {
                    @Override
                    public void onSuccess(OrderListModel baseBean) {
                        stopRefersh();
                         data = baseBean.getData();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(AddOderEvent event) {
        mBinding.srlBody.resetNoMoreData();
        mPage = 1;
        getDataList();
    }
    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.llyType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showType();
            }
        });
    }
    private void showType() {
        ChooseTypePopupwindow chooseTypePopupwindow = new ChooseTypePopupwindow(aty, mChooseList, name);
        chooseTypePopupwindow.setChooseTypeListener(new ChooseTypePopupwindow.ChooseTypeListener() {
            @Override
            public void onItem(ChooseTypeModel item) {
                name = item.getId();
                mBinding.srlBody.resetNoMoreData();
                mPage = 1;
                getDataList();
            }
        });
        chooseTypePopupwindow.showPopupWindow(mBinding.llyType);
    }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

}
