package com.wb.weibao.ui.record;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.wb.weibao.databinding.FragemntMineBinding;
import com.wb.weibao.databinding.FragemntRecordBinding;
import com.wb.weibao.databinding.ItemEarlyWarningLayoutBinding;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.view.PopupWindow.FitPopupUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class RecordFragment extends BaseFragment<BaseFragmentPresenter,FragemntRecordBinding> {

    private List<RecordListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<RecordListModel.DataBean.ListBean> mAdapter;
    private String mProjectId = "";//当前项目id
    private int mPage = 1;
    private int mPageSize = 15;
    private List<ProjectListModel.DataBean.ListBean> mProjectList = new ArrayList<>();//项目列表

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
                ItemEarlyWarningLayoutBinding binding = holder.getBinding(ItemEarlyWarningLayoutBinding.class);
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

        getProjectList();

    }

    @Override
    protected void initEvent() {
        super.initEvent();

        mBinding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("112",""+mProjectList.size()+mProjectList.toString());
                List<String> wheelString = new ArrayList<>();
                for (int i = 0; i < mProjectList.size(); i++) {
                    wheelString.add(mProjectList.get(i).getInstName());
                }
                if(wheelString.size()>1) {
                    FitPopupUtil fitPopupUtil = new FitPopupUtil(getActivity(), wheelString);
                    fitPopupUtil.setOnClickListener(new FitPopupUtil.OnCommitClickListener() {
                        @Override
                        public void onClick(String reason) {
                            ProjectListModel.DataBean.ListBean listBean = mProjectList.get(Integer.parseInt(reason));
                            mProjectId = listBean.getInstId();
                            mBinding.tvName.setText(listBean.getInstName());
                            mPage = 1;
                            getErrorList();
                            Toast.makeText(getActivity(), reason, Toast.LENGTH_SHORT).show();
                        }
                    });
                    fitPopupUtil.showPopup(v);
                }
            }
        });
    }

    /**
     * 获取项目列表
     */
    private void getProjectList() {
        Api.getApi().getProject_list(MyApplication.getInstance().getUserData().institutions.getCode(),
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            mProjectList.clear();
                            if (data.getList() != null && data.getList().size() > 0) {
                                mProjectList.addAll(data.getList());
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);
                                mProjectId = listBean.getInstId();
                                mBinding.tvName.setText(listBean.getInstName());
                                getErrorList();
                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    /**
     * 获取预警列表
     */
    private void getErrorList() {
        Api.getApi().getRecord_list(MyApplication.getInstance().getUserData().institutions.getCode(),
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId,
                mProjectId, mPage, mPageSize).compose(callbackOnIOToMainThread())
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
