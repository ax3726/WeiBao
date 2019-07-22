package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragmentSecurityBinding;
import com.wb.weibao.databinding.ItemSecurityBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.MaintenanceListModel;
import com.wb.weibao.model.home.ProjectDetailbean;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

public class SecurityFragment extends BaseFragment<BaseFragmentPresenter, FragmentSecurityBinding> {

    private List<MaintenanceListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<MaintenanceListModel.DataBean.ListBean> mAdapter;
    private int mType=-1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_security;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    public SecurityFragment setType(int type)
    {
        mType=type;
        return this;
    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<MaintenanceListModel.DataBean.ListBean>(aty, R.layout.item_security, mDataList) {
            @Override
            protected void convert(ViewHolder holder, MaintenanceListModel.DataBean.ListBean listBean, int position) {
                ItemSecurityBinding binding = holder.getBinding(ItemSecurityBinding.class);
                binding.tvName.setText(listBean.getFaultTypeName());
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(listBean.getCreateTime(), "yyyy/MM/dd HH:mm:ss"));
                switch (listBean.getStatus()) {
                    case "1"://待审核
                        binding.tvStatus.setSelected(true);
                        binding.tvStatus.setText("待审批");

                        binding.tvBtn.setVisibility(mType==1?View.GONE:View.VISIBLE);
                        binding.tvBtn.setText("取消");
                        binding.tvBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                handleWeiBao(listBean.getId() + "", listBean.getProcessingName());
                            }
                        });
                        break;
                    case "2"://审核失败
                        binding.tvStatus.setSelected(true);
                        binding.tvStatus.setText("审批失败");
                        binding.tvBtn.setVisibility(View.GONE);
                        break;
                    case "3"://待维保
                        binding.tvStatus.setSelected(true);
                        binding.tvStatus.setText("待维保");
                        binding.tvBtn.setVisibility(View.GONE);
                        break;
                    case "4"://维保成功
                        binding.tvStatus.setSelected(false);
                        binding.tvStatus.setText("已完成");
                        binding.tvBtn.setVisibility(View.GONE);
                        break;
                    case "5"://维保失败
                        binding.tvStatus.setSelected(true);
                        binding.tvStatus.setText("维保失败");
                        binding.tvBtn.setVisibility(View.GONE);
                        break;
                    default://已取消
                        binding.tvStatus.setSelected(false);
                        binding.tvStatus.setText("已取消");
                        binding.tvBtn.setVisibility(View.GONE);

                        break;
                }

                binding.rlyItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Api.getApi().getMyWeiBaoInfodetail(listBean.getId()+"", MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "")
                                .compose(callbackOnIOToMainThread())
                                .subscribe(new BaseNetListener<ProjectDetailbean>(SecurityFragment.this, true) {
                                    @Override
                                    public void onSuccess(ProjectDetailbean baseBean) {
                                        startActivity(new Intent(aty, SecurityInfoActivity.class).putExtra("id", listBean.getId() + "").putExtra("type",mType).putExtra("projectid",listBean.getProjectId()));

                                    }

                                    @Override
                                    public void onFail(String errMsg) {

                                    }
                                });



                    }
                });
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
    }

    public void setData(List<MaintenanceListModel.DataBean.ListBean> data) {
        mDataList.clear();
        if (data != null && data.size() > 0) {
            mDataList.addAll(data);
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 我的维保
     */
    private void handleWeiBao(String id, String processingName) {
//        processingName
        Api.getApi().handleWeiBao(id, MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", "6")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("操作成功!");
                        ((MySecurityActivity) aty).getDataList();
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }
}
