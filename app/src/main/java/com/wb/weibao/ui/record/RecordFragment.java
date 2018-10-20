package com.wb.weibao.ui.record;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

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
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.utils.DemoUtils;

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

        mAdapter = new CommonAdapter<RecordListModel.DataBean.ListBean>(aty, R.layout.item_record_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, RecordListModel.DataBean.ListBean item, int position) {
                ItemRecordLayoutBinding binding = holder.getBinding(ItemRecordLayoutBinding.class);
                binding.tvName.setText(item.getProjectArea());
                binding.tvError.setText(item.getProjectName());
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(), "yyyy.MM.dd"));
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
