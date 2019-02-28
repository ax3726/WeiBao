package com.wb.weibao.ui.home;

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
import com.wb.weibao.databinding.ActivitySignBinding;
import com.wb.weibao.databinding.ItemSignLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.SignListModel;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

public class SignActivity extends BaseActivity<BasePresenter, ActivitySignBinding> {

    private List<SignListModel.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<SignListModel.DataBean.ListBean> mAdapter;
    private int mSignType = -1;// 1签到   0签退

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("值班签到");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSignType > -1) {
                    if (mSignType == 1) {
                        addSignOut();
                    } else {
                        addSignIn();
                    }
                } else {
                    showToast("数据错误!");
                }


            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<SignListModel.DataBean.ListBean>(aty, R.layout.item_sign_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, SignListModel.DataBean.ListBean item, int position) {
                ItemSignLayoutBinding binding = holder.getBinding(ItemSignLayoutBinding.class);

                binding.tvState.setSelected(!"1".equals( item.getStatus()));
                binding.tvState.setText("1".equals( item.getStatus())?"签到":"签退");
                binding.tvTime.setText(DemoUtils.ConvertTimeFormat(item.getSignTime(), "yyyy/MM/dd HH:mm:ss"));
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        mBinding.rcBody.setNestedScrollingEnabled(false);
        mBinding.srlBody.setEnableLoadmore(false);
        mBinding.srlBody.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                addSignList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                addSignList();
            }
        });


        addSignList();
        checkSign();
    }

    /**
     * 检查状态
     */
    private void checkSign() {
        Api.getApi().checkSign(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        mSignType = "1".equals(baseBean.getData().toString()) ? 1 : 0;
                        mBinding.tvSign.setText(mSignType == 1 ? "值班签退" : "值班签到");
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 签到
     */
    private void addSignIn() {
        Api.getApi().addSignIn(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("签到成功!");
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 签退
     */
    private void addSignOut() {
        Api.getApi().addSignOut(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("签退成功!");
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 签到退   列表
     */
    private void addSignList() {
        Api.getApi().getSignList(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<SignListModel>(this, true) {
                    @Override
                    public void onSuccess(SignListModel baseBean) {
                        stopRefersh();
                        if (baseBean.getData() != null) {
                            List<SignListModel.DataBean.ListBean> list = baseBean.getData().getList();
                            mDataList.clear();
                            if (list != null && list.size() > 0) {
                                mDataList.addAll(list);
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
