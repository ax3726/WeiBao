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
import com.wb.weibao.utils.DemoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DCLFragment extends BaseFragment<BaseFragmentPresenter, FragmentDclBinding> {


    private String subwarningtype="";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dcl;
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
//                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(), "yyyy.MM.dd HH:mm:ss"));
                binding.tvProjectname.setText(item.getProjectName());
                binding.tvTime.setText(item.getWarningTime());
//                switch (item.getEquipmentType()) {
//                    case "1":
//                        binding.tvDianwei.setText("采集器");
//                        break;
//                    case "2":
//                        binding.tvDianwei.setText("无线设备");
//                        break;
//                    case "3":
//                        binding.tvDianwei.setText("点位(" + item.getPloop() + "," + item.getPpoint() + ")");
//                        break;
//                    case "4":
//                        binding.tvDianwei.setText("电力设备");
//                        break;
//
//                }
                binding.tvDianwei.setText(item.getEquipmentName());

               switch (mType)
               {
                   case 1:
                   case 2:
                       binding.tvError.setText("火警待处理");
                       binding.tvError.setTextColor(getResources().getColor(R.color.colorC8241D));
                       break;
                       default:
                           binding.tvError.setText(item.getSubWarningTypeName());
                           binding.tvError.setTextColor(getResources().getColor(R.color.colorC8241D));
                           break;
               }



                RelativeLayout rly_item = holder.getView(R.id.rly);
                rly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(aty, RecordDetailActivity.class);
                        intent.putExtra("title", "待处理详情");
                        if (mType == 1 || mType == 2 || mType == 4) {
                            intent.putExtra("title2", "火警");
                        } else {
                            intent.putExtra("title2", "告警");
                        }
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
        switch (mType)
        {
            case 1:
                subwarningtype="37，53，65";
                break;
            case 2:
                subwarningtype="711，712，713，719";
                break;
            case 4:
                subwarningtype="41，42，43，44，45，46，47";
                break;
            case 3:
                subwarningtype="11，12，13，14，22，23，31，39，35，36，701，704，714，715，716，717，718，720，721";
                break;
            case 6:
                subwarningtype="703，722";
                break;
            case 7:
                subwarningtype="21，32，33，34，38，51，52，54，55，56，61，62，63，64，65，66，67，702，705";
                break;
        }
        Api.getApi().getRecordList("" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(), MyApplication.getInstance().getUserData().getPrincipal().getInstCode() + "", MyApplication.getInstance().getProjectId(), null, "2", "", String.valueOf(mType), mPage, mPageSize).compose(callbackOnIOToMainThread())
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
