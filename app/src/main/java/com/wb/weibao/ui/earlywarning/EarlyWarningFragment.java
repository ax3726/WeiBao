package com.wb.weibao.ui.earlywarning;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.lm.lib_common.adapters.recyclerview.CommonAdapter;
import com.lm.lib_common.adapters.recyclerview.base.ViewHolder;
import com.lm.lib_common.base.BaseFragment;
import com.lm.lib_common.base.BaseFragmentPresenter;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntEarlyWarningBinding;
import com.wb.weibao.databinding.ItemEarlyWarningLayoutBinding;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.model.earlywarning.ErrorListModel;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.ui.main.MainActivity;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class EarlyWarningFragment extends BaseFragment<BaseFragmentPresenter, FragemntEarlyWarningBinding> {
    private List<ErrorListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<ErrorListModel.DataBean.ListBean> mAdapter;
    private String mProjectId = "";//当前项目id
    private int mPage = 1;
    private int mPageSize = 15;

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_early_warning;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<ErrorListModel.DataBean.ListBean>(aty, R.layout.item_early_warning_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, ErrorListModel.DataBean.ListBean item, int position) {
                ItemEarlyWarningLayoutBinding binding = holder.getBinding(ItemEarlyWarningLayoutBinding.class);
                binding.tvName.setText(item.getProjectArea());
                binding.tvError.setText(item.getProjectName());
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getEarlyTime(),"yyyy.MM.dd"));
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

                            if (data.getList() != null && data.getList().size() > 0) {
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(1);
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
        Api.getApi().getError_list(MyApplication.getInstance().getUserData().institutions.getCode(),
                "" + MyApplication.getInstance().getUserData().userRoles.get(0).userId,
                mProjectId, 1, mPage, mPageSize).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ErrorListModel>(this, false) {
                    @Override
                    public void onSuccess(ErrorListModel baseBean) {
                        stopRefersh();
                        ErrorListModel.DataBean data = baseBean.getData();
                        if (data!=null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<ErrorListModel.DataBean.ListBean> list = data.getList();
                            if (list!=null&&list.size()>0) {
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
