package com.wb.weibao.ui.home;

import android.content.Intent;
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
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityWeiRecordBinding;
import com.wb.weibao.databinding.ItemRecordTbcLayoutBinding;
import com.wb.weibao.databinding.ItemWeibaorecordLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.RecordListAppBean;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.earlywarning.TBCFragment;
import com.wb.weibao.utils.DemoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class WeiBaoRecordActivity extends BaseActivity<BasePresenter, ActivityWeiRecordBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wei_record;
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
        mTitleBarLayout.setTitle("维保记录");
        mTitleBarLayout.setRightTxt("添加记录");
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddDayWeiBaoActivity.class);
            }
        });
    }

    private List<RecordListAppBean.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<RecordListAppBean.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    private String name = "";

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);


        mAdapter = new CommonAdapter<RecordListAppBean.DataBean.ListBean>(aty, R.layout.item_weibaorecord_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, RecordListAppBean.DataBean.ListBean listBean, int position) {
                ItemWeibaorecordLayoutBinding binding = holder.getBinding(ItemWeibaorecordLayoutBinding.class);
                binding.tv02.setText(mDataList.get(position).getCoverProjectName());
                String CreateTime = mDataList.get(position).getCreateTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(mDataList.get(position).getCreateTime(), "yyyy.MM.dd HH.mm.ss");
                binding.tv04.setText(CreateTime);

                binding.rlyItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(aty, WeibaoDetailActivity.class).putExtra("id", listBean.getId() + "").putExtra("type","1").putExtra("projectid",listBean.getProjectId()));

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
             * 获取维保记录列表
             */
            private void getErrorList() {
                Api.getApi().getRecordlistApp("" + mPage, "" + mPageSize, MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", MyApplication.getInstance().getProjectId()).compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<RecordListAppBean>(this, false) {
                            @Override
                            public void onSuccess(RecordListAppBean baseBean) {

                        stopRefersh();
                        RecordListAppBean.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<RecordListAppBean.DataBean.ListBean> list = data.getList();
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


            private void stopRefersh() {
                mBinding.srlBody.finishRefresh();
                mBinding.srlBody.finishLoadmore();
            }
        }
