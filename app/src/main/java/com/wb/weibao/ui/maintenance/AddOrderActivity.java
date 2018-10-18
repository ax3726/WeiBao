package com.wb.weibao.ui.maintenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.lm.lib_common.adapters.recyclerview.CommonAdapter;
import com.lm.lib_common.adapters.recyclerview.base.ViewHolder;
import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityAddOrderBinding;
import com.wb.weibao.databinding.ItemAddOrderLayoutBinding;
import com.wb.weibao.model.earlywarning.QuestItemModel;

import java.util.ArrayList;
import java.util.List;

public class AddOrderActivity extends BaseActivity<BasePresenter, ActivityAddOrderBinding> implements View.OnClickListener {

    private List<QuestItemModel> mDataList = new ArrayList<>();
    private CommonAdapter<QuestItemModel> mAdapter;

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("新增维保订单");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_order;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        mDataList.add(new QuestItemModel("1.消防主机出现问题"));
        mDataList.add(new QuestItemModel("2.末端点位损坏"));
        mDataList.add(new QuestItemModel("3.线路问题"));
        mDataList.add(new QuestItemModel("4.消防水系统出现问题"));
        mDataList.add(new QuestItemModel("5.排烟风机无法正常启动"));
        mDataList.add(new QuestItemModel("6.水泵房内设备损坏"));
        mDataList.add(new QuestItemModel("7.水系统管道问题"));
        mDataList.add(new QuestItemModel("8.消防水箱损坏"));
        mDataList.add(new QuestItemModel("9.其他问题"));
        mAdapter = new CommonAdapter<QuestItemModel>(aty, R.layout.item_add_order_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, QuestItemModel item, int position) {
                ItemAddOrderLayoutBinding binding = holder.getBinding(ItemAddOrderLayoutBinding.class);
                binding.tvTxt.setText(item.getContent());
                binding.img.setSelected(item.isIs_select());
                binding.rlyTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDataList.get(position).setIs_select(!item.isIs_select());
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);


    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvTurn.setOnClickListener(this);
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_turn:
                getSelectData();
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void getSelectData() {
        String str = "";
        for (QuestItemModel itemModel : mDataList) {
            if (itemModel.isIs_select()) {
                str = str + itemModel.getContent() + ";";
            }
        }

        mBinding.llyQuest.setVisibility(View.GONE);
        mBinding.llyOrder.setVisibility(View.VISIBLE);
        mBinding.etContent.setText(str);

    }

    private void submit() {
        String name = mBinding.etName.getText().toString();
        String phone = mBinding.etPhone.getText().toString();
        String content = mBinding.etContent.getText().toString();

        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名!");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号码!");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            showToast("请输入订单详情!");
            return;
        }
        mBinding.tvSubmit.setClickable(false);
        Api.getApi().addOrder(MyApplication.getInstance().getUserData().userRoles.get(0).userId + "",
                MyApplication.getInstance().getUserData().institutions.getCode(),
                MyApplication.getInstance().getProjectId(), 1, name, phone, content)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("添加成功！");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(1500);
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.tvSubmit.setClickable(true);
                    }
                });


    }
}
