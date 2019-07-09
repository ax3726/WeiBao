package com.wb.weibao.ui.earlywarning;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragmentDclBinding;
import com.wb.weibao.databinding.ItemRecordTbcLayoutBinding;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.record.RecordDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DCLFragment extends BaseFragment<BaseFragmentPresenter, FragmentDclBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dcl;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    private List<RecordListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<RecordListModel.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    private String name="";

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        mAdapter = new CommonAdapter<RecordListModel.DataBean.ListBean>(aty, R.layout.item_record_tbc_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, RecordListModel.DataBean.ListBean item, int position) {
                ItemRecordTbcLayoutBinding binding = holder.getBinding(ItemRecordTbcLayoutBinding.class);
//                binding.tvError.setText(item.getProjectName());
//                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(), "yyyy.MM.dd HH:mm:ss"));
                binding.tvProjectname.setText(item.getProjectName());
                binding.tvTime.setText(item.getWarningTime());
                switch (item.getEquipmentType())
                {
                    case "1":
                        binding.tvDianwei.setText("采集器");
                        break;
                    case "2":
                        binding.tvDianwei.setText("无线设备");
                        break;
                    case "3":
                        binding.tvDianwei.setText("点位(" + item.getPloop() + "," + item.getPpoint() + ")");
                        break;
                    case "4":
                        binding.tvDianwei.setText("电力设备");
                        break;

                }

//                switch (item.getStatus())
//                {
//                    case "1":
//                        binding.tvError.setText("预警中");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
//                        break;
//                    case "2":
                        binding.tvError.setText("火警待处理");
                        binding.tvError.setTextColor(getResources().getColor(R.color.colorC8241D));
//                        break;
//                    case "3":
//                        binding.tvError.setText("无灾情");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
//                        break;
//                    case "4":
//                        binding.tvError.setText("有灾情");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.colorF15453));
//                        break;
//                    case "5":
//                        binding.tvError.setText("系统复位");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
//                        break;
//                }



                RelativeLayout rly_item = holder.getView(R.id.rly);
                rly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(aty,RecordDetailActivity.class);
                        intent.putExtra("title", "待处理详情");
                        intent.putExtra("title2", "火警");
                        intent.putExtra("item", (Serializable) item);
                        intent.putExtra("userId", ""+MyApplication.getInstance().getUserData().getPrincipal().getUserId());
                        intent.putExtra("id", ""+item.getId());
                        startActivity(intent);
                    }
                });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(RecordDetailEvent event) {
        mBinding.srlBody.resetNoMoreData();
        mPage = 1;
        getErrorList();
    }
    /**
     * 获取预警列表
     */
    private void getErrorList() {
        Api.getApi().getRecordList(""+ MyApplication.getInstance().getUserData().getPrincipal().getUserId(),MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",MyApplication.getInstance().getProjectId(),"1","2","","2",mPage,mPageSize).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordListModel>(this, false) {
                    @Override
                    public void onSuccess(RecordListModel baseBean) {
                        if (mBinding.srlBody==null) {
                            return;
                        }
                        stopRefersh();
                        RecordListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<RecordListModel.DataBean.ListBean> list = data.getList();
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
        if (mBinding.srlBody==null) {
            return;
        }
        mBinding.srlBody.finishRefresh();
        mBinding.srlBody.finishLoadmore();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
