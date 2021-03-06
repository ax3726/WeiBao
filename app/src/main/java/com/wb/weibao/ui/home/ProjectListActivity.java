package com.wb.weibao.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.SearchView;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityProjectListBinding;
import com.wb.weibao.databinding.ItemProjectListLayoutBinding;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.ErrorEvent;
import com.wb.weibao.model.event.ProjectChangeEvent;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;


import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends BaseActivity<BasePresenter, ActivityProjectListBinding> {


    private List<ProjectListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<ProjectListModel.DataBean.ListBean> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_list;
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
        mTitleBarLayout.setTitle("项目单位选择");
    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<ProjectListModel.DataBean.ListBean>(aty, R.layout.item_project_list_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, ProjectListModel.DataBean.ListBean item, int position) {
                ItemProjectListLayoutBinding binding = holder.getBinding(ItemProjectListLayoutBinding.class);
                binding.tvName.setText(item.getName());
                binding.tvName.setSelected(MyApplication.getInstance().getProjectId().equals(item.getId() + ""));
                binding.img.setVisibility(MyApplication.getInstance().getProjectId().equals(item.getId() + "") ? View.VISIBLE : View.GONE);
                binding.rlyItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SpfUtils spfUtils = SpfUtils.getInstance(aty);
                        if(item.getName().equals("全部项目"))
                        {
                            spfUtils.setSpfString(SpfKey.INST_ID, String.valueOf(""));
                            spfUtils.setSpfString(SpfKey.INST_NAME, item.getName());
                        }else {
                            spfUtils.setSpfString(SpfKey.INST_ID, String.valueOf(item.getId()));
                            spfUtils.setSpfString(SpfKey.INST_NAME, item.getName());
                        }
                        spfUtils.setSpfString(SpfKey.LatiTude, String.valueOf(item.getLatitude()));
                        spfUtils.setSpfString(SpfKey.LongiTude, String.valueOf(item.getLongitude()));
                        spfUtils.setSpfString(SpfKey.InstCode, String.valueOf(item.getInstCode()));
                        MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                        MyApplication.getInstance().setmProjectName(spfUtils.getSpfString(SpfKey.INST_NAME));
                        notifyDataSetChanged();
                        EventBus.getDefault().post(new ProjectChangeEvent());
                        EventBus.getDefault().post(new RecordDetailEvent());
                        finish();
                    }
                });
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        getProjectList();



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
                    getProjectList(newText);
                }else
                    {
                        LogUtils.e("121==="+newText);
                        getProjectList();
                    }
                return false;
            }
        });


    }

    /**
     * 获取项目列表
     */
    private void getProjectList() {
        Api.getApi().getProject_list(MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",
                "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId()).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            mDataList.clear();
                            ProjectListModel.DataBean.ListBean listBean = new ProjectListModel.DataBean.ListBean();
                            listBean.setName("全部项目");
                            listBean.setId(Integer.parseInt(MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"")); //id等于-1代表全部
                            mDataList.add(listBean);
                            if (data.getList() != null && data.getList().size() > 0) {
                                mDataList.addAll(data.getList());

                            }
                            mAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }


    /**
     * 获取项目列表
     */
    private void getProjectList(String name) {
        Api.getApi().getProject_listserach(MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",
                "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(),name).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            mDataList.clear();
                            ProjectListModel.DataBean.ListBean listBean = new ProjectListModel.DataBean.ListBean();
                            listBean.setName("全部项目");
                            listBean.setId(Integer.parseInt(MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"")); //id等于-1代表全部
                            mDataList.add(listBean);
                            if (data.getList() != null && data.getList().size() > 0) {
                                mDataList.addAll(data.getList());

                            }
                            mAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }
}
