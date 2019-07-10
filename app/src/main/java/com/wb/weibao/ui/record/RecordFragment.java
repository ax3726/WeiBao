package com.wb.weibao.ui.record;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntRecordBinding;
import com.wb.weibao.databinding.ItemRecordLayoutBinding;
import com.wb.weibao.model.main.ChooseTypeModel;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.view.PopupWindow.ChooseTypePopupwindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class RecordFragment extends BaseFragment<BaseFragmentPresenter, FragemntRecordBinding> {

    private List<RecordListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<RecordListModel.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 15;
    private String name="";
    private List<ChooseTypeModel> mChooseList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_record;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }


    private void initChooseData(){
        mChooseList.add(new ChooseTypeModel("全部", ""));
        mChooseList.add(new ChooseTypeModel("采集器监测连接线路故障", "11"));
        mChooseList.add(new ChooseTypeModel("采集器监控中心通信信道故障", "12"));
        mChooseList.add(new ChooseTypeModel("采集器备电源故障", "13"));
        mChooseList.add(new ChooseTypeModel("采集器主电源故障", "14"));
        mChooseList.add(new ChooseTypeModel("主机复位", "21"));
        mChooseList.add(new ChooseTypeModel("主机备电源故障", "22"));
        mChooseList.add(new ChooseTypeModel("主机主电源故障", "23"));
        mChooseList.add(new ChooseTypeModel("延时", "31"));
        mChooseList.add(new ChooseTypeModel("反馈", "32"));
        mChooseList.add(new ChooseTypeModel("启动", "33"));
        mChooseList.add(new ChooseTypeModel("监管", "34"));
        mChooseList.add(new ChooseTypeModel("屏蔽", "35"));
        mChooseList.add(new ChooseTypeModel("故障", "36"));
        mChooseList.add(new ChooseTypeModel("火警", "37"));
        mChooseList.add(new ChooseTypeModel("测试", "38"));
        mChooseList.add(new ChooseTypeModel("电源故障", "39"));
        mChooseList.add(new ChooseTypeModel("漏电报警", "41"));
        mChooseList.add(new ChooseTypeModel("漏电短路", "42"));
        mChooseList.add(new ChooseTypeModel("漏电开路", "43"));
        mChooseList.add(new ChooseTypeModel("温度报警", "45"));
        mChooseList.add(new ChooseTypeModel("温度短路", "46"));
        mChooseList.add(new ChooseTypeModel("温度开路", "47"));
    }
    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        initChooseData();
        mAdapter = new CommonAdapter<RecordListModel.DataBean.ListBean>(aty, R.layout.item_record_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, RecordListModel.DataBean.ListBean item, int position) {
                ItemRecordLayoutBinding binding = holder.getBinding(ItemRecordLayoutBinding.class);
                binding.tvError.setText(item.getProjectName());
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(), "yyyy.MM.dd"));
                switch (item.getEquipmentType())
                {
                    case "1":
                        binding.tvName.setText("采集器");
                        break;
                    case "2":
                        binding.tvName.setText("无线设备");
                        break;
                    case "3":
                        binding.tvName.setText("点位(" + item.getPloop() + "," + item.getPpoint() + ")");
                        break;
                    case "4":
                        binding.tvName.setText("电力设备");
                        break;

                }
                switch (item.getSubWarningType()) {
                    case "11":
                        binding.tvHint.setText("采集器监测连接线路故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "12":
                        binding.tvHint.setText("采集器监控中心通信信道故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "13":
                        binding.tvHint.setText("采集器备电源故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "14":
                        binding.tvHint.setText("采集器主电源故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "21":
                        binding.tvHint.setText("主机复位");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "22":
                        binding.tvHint.setText("主机备电源故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "23":
                        binding.tvHint.setText("主机主电源故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "31":
                        binding.tvHint.setText("延时");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "32":
                        binding.tvHint.setText("反馈");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "33":
                        binding.tvHint.setText("启动");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "34":
                        binding.tvHint.setText("监管");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "35":
                        binding.tvHint.setText("屏蔽");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "36":
                        binding.tvHint.setText("故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "37":
                        binding.tvHint.setText("火警");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.colorF15453));
                        binding.tvName.setTextColor(getResources().getColor(R.color.colorF15453));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorF15453));
                        break;
                    case "38":
                        binding.tvHint.setText("测试");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "39":
                        binding.tvHint.setText("电源故障");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "41":
                        binding.tvHint.setText("漏电报警");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "42":
                        binding.tvHint.setText("漏电短路");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "43":
                        binding.tvHint.setText("漏电开路");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "45":
                        binding.tvHint.setText("温度报警");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "46":
                        binding.tvHint.setText("温度短路");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                    case "47":
                        binding.tvHint.setText("温度开路");
                        binding.tvHint.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                        binding.tvTime.setTextColor(getResources().getColor(R.color.colorD0D0D0));
                        break;
                }
                switch (item.getStatus())
                {
                    case "1":
                        binding.tvError.setText("预警中");
                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
                        break;
                    case "2":
                        binding.tvError.setText("处理中");
                        binding.tvError.setTextColor(getResources().getColor(R.color.colorFACF28));
                        break;
                    case "3":
                        binding.tvError.setText("无灾情");
                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
                        break;
                    case "4":
                        binding.tvError.setText("有灾情");
                        binding.tvError.setTextColor(getResources().getColor(R.color.colorF15453));
                        break;
                    case "5":
                        binding.tvError.setText("系统复位");
                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
                        break;
                }



                RelativeLayout rly_item = holder.getView(R.id.rly);
                rly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(aty,RecordDetailActivity.class);
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
        Api.getApi().getRecord_list(MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",
                "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(),
                MyApplication.getInstance().getProjectId(), mPage, mPageSize,name).compose(callbackOnIOToMainThread())
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
                getErrorList();
            }
        });
        chooseTypePopupwindow.showPopupWindow(mBinding.llyType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
