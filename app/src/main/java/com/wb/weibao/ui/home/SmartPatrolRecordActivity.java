package com.wb.weibao.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SearchView;

import com.lidroid.xutils.util.LogUtils;
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
import com.wb.weibao.databinding.ActivitySmartPatrolRecordBinding;
import com.wb.weibao.databinding.ItemSmartPatrolRecordBinding;
import com.wb.weibao.model.home.PatrolPointListBean;
import com.wb.weibao.model.home.SmartElectorBean;
import com.wb.weibao.view.PopupWindow.ConfirmPopupwindow;

import java.util.ArrayList;
import java.util.List;

public class SmartPatrolRecordActivity extends BaseActivity<BasePresenter, ActivitySmartPatrolRecordBinding> {

    private List<PatrolPointListBean.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<PatrolPointListBean.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    private String name="";
    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("智慧巡查记录");
        mTitleBarLayout.setRightShow(true);
        mTitleBarLayout.setRightTxt("结束巡查");
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_patrol_record;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    ConfirmPopupwindow confirmPopupwindow;
    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<PatrolPointListBean.DataBean.ListBean>(aty, R.layout.item_smart_patrol_record, mDataList) {
            @Override
            protected void convert(ViewHolder holder, PatrolPointListBean.DataBean.ListBean item, int position) {
                ItemSmartPatrolRecordBinding binding = holder.getBinding(ItemSmartPatrolRecordBinding.class);
                binding.tvName.setText(mDataList.get(position).getName());
                binding.tvContent.setText(mDataList.get(position).getAddress());
                binding.tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         confirmPopupwindow=new ConfirmPopupwindow(aty);
                         confirmPopupwindow.showPopupWindow();
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


        mBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText))
                {
                    LogUtils.e("11==="+newText);
                    name=newText;
                    getDataList();
                }else
                {
                    LogUtils.e("121==="+newText);
                    name="";
                    getDataList();
                }
                return false;
            }
        });

    }

    private void getDataList() {
        Api.getApi().getPatrolPointList(name, MyApplication.getInstance().getProjectId(),"" + mPage, "" + mPageSize)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<PatrolPointListBean>(SmartPatrolRecordActivity.this, false) {
                    @Override
                    public void onSuccess(PatrolPointListBean baseBean) {
                        stopRefersh();
                        PatrolPointListBean.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<PatrolPointListBean.DataBean.ListBean> list = data.getList();
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
