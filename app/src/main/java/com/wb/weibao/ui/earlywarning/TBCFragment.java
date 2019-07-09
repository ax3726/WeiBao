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
import com.wb.weibao.databinding.FragmentTbcBinding;
import com.wb.weibao.databinding.ItemRecordTbcLayoutBinding;
import com.wb.weibao.model.BaseBean;
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

public class TBCFragment extends BaseFragment<BaseFragmentPresenter, FragmentTbcBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tbc;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }


    private List<RecordListModel.DataBean.ListBean>          mDataList = new ArrayList<>();
    private CommonAdapter<RecordListModel.DataBean.ListBean> mAdapter;
    private int                                              mPage     = 1;
    private int                                              mPageSize = 10;
    private String                                           name      = "";
    private int                                              mType     = 1;

    public void setType(int mType) {
        this.mType = mType;
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        mAdapter = new CommonAdapter<RecordListModel.DataBean.ListBean>(aty, R.layout.item_record_tbc_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, RecordListModel.DataBean.ListBean item, int position) {
                ItemRecordTbcLayoutBinding binding = holder.getBinding(ItemRecordTbcLayoutBinding.class);
//                binding.tvError.setText(item.getProjectName());
                binding.tvProjectname.setText(item.getProjectName());
                binding.affirm.setVisibility(View.VISIBLE);
                binding.affirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Api.getApi().getearlyRecordUpdate("" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(), "2", MyApplication.getInstance().getUserData().getName(), item.getId() + "")
                                .compose(callbackOnIOToMainThread())
                                .subscribe(new BaseNetListener<BaseBean>(TBCFragment.this, true) {
                                    @Override
                                    public void onSuccess(BaseBean baseBean) {
                                        mBinding.srlBody.resetNoMoreData();
                                        mPage = 1;
                                        getErrorList();
                                        EventBus.getDefault().post(new RecordDetailEvent());

                                    }

                                    @Override
                                    public void onFail(String errMsg) {

                                    }
                                });
                    }
                });
//                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getWarningTime(), "yyyy.MM.dd HH.mm.ss"));
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
                switch (mType) {
                    case 1://远程监控火警
                        binding.tvError.setText("火警待确认");
                        break;
                    case 2://九小场所火警
                        binding.tvError.setText("火警待确认");
                        break;
                    case 3://故障111
                        binding.tvError.setText("主机低压报警");
                        break;
                    case 4://用电异常
                        binding.tvError.setText("温度报警");
                        break;
                    case 5://用水异常111
                        binding.tvError.setText("用水异常");
                        break;
                    case 6://拆除
                        binding.tvError.setText("主机防拆报警");
                        break;
                    case 7://其他
                        binding.tvError.setText("其他");
                        break;
                }

//                switch (item.getStatus())
//                {
//                    case "1":

                binding.tvError.setTextColor(getResources().getColor(R.color.colorC8241D));
//                        break;
//                    case "2":
//                        binding.tvError.setText("待处理详情");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.colorFACF28));
//                        break;
//                    case "3":
//                        binding.tvError.setText("已处理详情");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
//                        break;
//                    case "4":
//                        binding.tvError.setText("待确认详情");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.colorF15453));
//                        break;
//                    case "5":
//                        binding.tvError.setText("已处理详情");
//                        binding.tvError.setTextColor(getResources().getColor(R.color.color00A0F1));
//                        break;
//                }


                RelativeLayout rly_item = holder.getView(R.id.rly);
                rly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(aty, RecordDetailActivity.class);
                        intent.putExtra("title", "待确认详情");
                        intent.putExtra("title2", "火警");
                        intent.putExtra("title3", DemoUtils.typeToString(mType));
                        intent.putExtra("item", (Serializable) item);
                        intent.putExtra("userId", "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId());
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
        if (isHidden) {
            return;
        }
        mBinding.srlBody.resetNoMoreData();
        mPage = 1;
        getErrorList();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(RecordDetailEvent event) {
        if (isHidden) {
            return;
        }
        mBinding.srlBody.resetNoMoreData();
        mPage = 1;
        getErrorList();
    }

    /**
     * 获取预警列表
     */
    private void getErrorList() {
        Api.getApi().getRecordList("" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(), MyApplication.getInstance().getUserData().getPrincipal().getInstCode() + "", MyApplication.getInstance().getProjectId(), null, "1", "", String.valueOf(mType), mPage, mPageSize).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordListModel>(this, false) {
                    @Override
                    public void onSuccess(RecordListModel baseBean) {
                        if (mBinding.srlBody == null) {
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
//
                        }

                    }

                    @Override
                    public void onFail(String errMsg) {
                        stopRefersh();
                    }
                });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mBinding.srlBody.resetNoMoreData();
            mPage = 1;
            getErrorList();
        }
    }

    private void stopRefersh() {
        if (mBinding.srlBody == null) {
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
