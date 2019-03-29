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
import com.wb.weibao.databinding.FragmentYclBinding;
import com.wb.weibao.databinding.ItemRecordTbcLayoutBinding;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.record.RecordDetailActivity;
import com.wb.weibao.utils.DemoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlarmYCLFragment extends BaseFragment<BaseFragmentPresenter, FragmentYclBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ycl;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }


    private List<RecordListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<RecordListModel.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 15;
    private String name = "";

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
                binding.tvTime.setText(item.getWarningTime());
                switch (item.getEquipmentType()) {
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
//
//

                switch (item.getSubWarningType())
                {

                    case "11":
                        binding.tvError.setText("采集器监测连接线路故障");
                        break;
                    case "12":
                        binding.tvError.setText("采集器监控中心通信信道故障");
                        break;
                    case "13":
                        binding.tvError.setText("采集器备电源故障");
                        break;
                    case "14":
                        binding.tvError.setText("采集器主电源故障");
                        break;
                    case "21":
                        binding.tvError.setText("主机复位");
                        break;
                    case "22":
                        binding.tvError.setText("主机备电源故障");
                        break;
                    case "23":
                        binding.tvError.setText("主机主电源故障");
                        break;
                    case "31":
                        binding.tvError.setText("延时");
                        break;
                    case "32":
                        binding.tvError.setText("反馈");
                        break;
                    case "33":
                        binding.tvError.setText("启动");
                        break;
                    case "34":
                        binding.tvError.setText("监管");
                        break;
                    case "35":
                        binding.tvError.setText("屏蔽");
                        break;
                    case "36":
                        binding.tvError.setText("故障");
                        break;
                    case "37":
                        binding.tvError.setText("火警");
                        break;
                    case "38":
                        binding.tvError.setText("测试");
                        break;
                    case "39":
                        binding.tvError.setText("电源故障");
                        break;
                    case "41":
                        binding.tvError.setText("漏电报警");
                        break;
                    case "42":
                        binding.tvError.setText("漏电短路");
                        break;
                    case "43":
                        binding.tvError.setText("漏电开路");
                        break;
                    case "45":
                        binding.tvError.setText("温度报警");
                        break;
                    case "46":
                        binding.tvError.setText("温度短路");
                        break;
                    case "47":
                        binding.tvError.setText("温度开路");
                        break;
                    case "51":
                        binding.tvError.setText("测试");
                        break;
                    case "52":
                        binding.tvError.setText("自检");
                        break;
                    case "53":
                        binding.tvError.setText("手动报警");
                        break;
                    case "54":
                        binding.tvError.setText("消音");
                        break;
                    case "55":
                        binding.tvError.setText("复位");
                        break;
                    case "56":
                        binding.tvError.setText("查岗应答");
                        break;
                }
                binding.tvError.setTextColor(getResources().getColor(R.color.color36519E));


                RelativeLayout rly_item = holder.getView(R.id.rly);
                rly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(aty, RecordDetailActivity.class);
                        intent.putExtra("title", "已处理详情");
                        intent.putExtra("title2", "告警");
                        intent.putExtra("item", (Serializable) item);
                        intent.putExtra("userId", "" + MyApplication.getInstance().getUserData().getId());
                        intent.putExtra("id", "" + item.getId());
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
        Api.getApi().getRecordList("" + MyApplication.getInstance().getUserData().getId(), MyApplication.getInstance().getUserData().getCompanyId(), MyApplication.getInstance().getProjectId(), "1", "9,10,11", "","1", mPage, mPageSize).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordListModel>(this, false) {
                    @Override
                    public void onSuccess(RecordListModel baseBean) {
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
        mBinding.srlBody.finishRefresh();
        mBinding.srlBody.finishLoadmore();
    }
}
