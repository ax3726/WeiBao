package com.wb.weibao.ui.record;

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
import com.wb.weibao.databinding.FragemntRecordBinding;
import com.wb.weibao.databinding.ItemRecordLayoutBinding;
import com.wb.weibao.model.earlywarning.EarilDetailEvent;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.earlywarning.EarlyWarningDetailActivity;
import com.wb.weibao.utils.DemoUtils;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_record;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        mAdapter = new CommonAdapter<RecordListModel.DataBean.ListBean>(aty, R.layout.item_record_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, RecordListModel.DataBean.ListBean item, int position) {
                ItemRecordLayoutBinding binding = holder.getBinding(ItemRecordLayoutBinding.class);
                binding.tvName.setText("("+item.getPloop()+","+item.getPpoint()+")");
                binding.tvError.setText(item.getProjectName());
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(), "yyyy.MM.dd"));

                switch (item.getSubWarningType())
                {
                    case "11":
                        binding.tvHint.setText("采集器监测连接线路故障");
                        break;
                    case "12":
                        binding.tvHint.setText("采集器监控中心通信信道故障");
                        break;
                    case "13":
                        binding.tvHint.setText("采集器备电源故障");
                        break;
                    case "14":
                        binding.tvHint.setText("采集器主电源故障");
                        break;
                    case "21":
                        binding.tvHint.setText("主机复位");
                        break;
                    case "22":
                        binding.tvHint.setText("主机备电源故障");
                        break;
                    case "23":
                        binding.tvHint.setText("主机主电源故障");
                        break;
                    case "31":
                        binding.tvHint.setText("延时");
                        break;
                    case "32":
                        binding.tvHint.setText("反馈");
                        break;
                    case "33":
                        binding.tvHint.setText("启动");
                        break;
                    case "34":
                        binding.tvHint.setText("监管");
                        break;
                    case "35":
                        binding.tvHint.setText("屏蔽");
                        break;
                    case "36":
                        binding.tvHint.setText("故障");
                        break;
                    case "37":
                        binding.tvHint.setText("火警");
                        break;
                    case "38":
                        binding.tvHint.setText("测试");
                        break;
                    case "39":
                        binding.tvHint.setText("电源故障");
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
                        intent.putExtra("userId", ""+MyApplication.getInstance().getUserData().userRoles.get(0).userId);
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
        mBinding.sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.srlBody.resetNoMoreData();
                name=mBinding.etName.getText().toString();
                mPage = 1;
                getErrorList();
            }
        });

        mBinding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("name=",name);
                if(s.length()>0)
                {
                    name=s.toString();
                }else
                {
                    name="";
                }
            }
        });
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
        Api.getApi().getRecord_list(MyApplication.getInstance().getUserData().institutions.getCode(),
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId,
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

}
