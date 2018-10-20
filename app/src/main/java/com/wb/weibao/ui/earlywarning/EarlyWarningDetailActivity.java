package com.wb.weibao.ui.earlywarning;

import android.util.Log;
import android.view.View;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.databinding.ActivityEarlyWarningDetailBinding;
import com.wb.weibao.model.earlywarning.EarilDetailEvent;
import com.wb.weibao.model.earlywarning.ErrorListModel;

import org.greenrobot.eventbus.EventBus;

public class EarlyWarningDetailActivity extends BaseActivity<BasePresenter,ActivityEarlyWarningDetailBinding> {

    ErrorListModel.DataBean.ListBean list=null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_early_warning_detail;
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
        mTitleBarLayout.setTitle("预警记录详情");
    }
    private String mId = "";
    private String muserId = "";
    @Override
    protected void initData() {
        super.initData();
        list= (ErrorListModel.DataBean.ListBean) getIntent().getSerializableExtra("item");
        mId=getIntent().getStringExtra("id");
        muserId=getIntent().getStringExtra("userId");
        Log.e("qw",list.toString());
        Log.e("qw",list.getPloop());
        mBinding.tv1.setText(list.getInstName());
        mBinding.tv2.setText((list.getFlowNo() == null) ? "" : ""+list.getFlowNo());
        switch (list.getStatus())
        {
            case "1":
                mBinding.tv3.setText("预警中");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.colorTheme));
                mBinding.aly.setVisibility(View.VISIBLE);
                mBinding.affirm3.setVisibility(View.VISIBLE);
                break;
            case "2":
                mBinding.tv3.setText("处理中");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.text_color));
                mBinding.aly.setVisibility(View.VISIBLE);
                mBinding.affirm1.setVisibility(View.VISIBLE);
                mBinding.affirm2.setVisibility(View.VISIBLE);
                break;
            case "3":
                mBinding.tv3.setText("无灾情");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
            case "4":
                mBinding.tv3.setText("有灾情");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.colorC8241D));
                break;
            case "5":
                mBinding.tv3.setText("系统复位");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.colorTheme));
                break;
        }
        switch (list.getWarningType()) {
            case "1":
                mBinding.tv4.setText("采集器预警");

                break;
            case "2":
                mBinding.tv4.setText("主机预警");

                break;
            case "3":
                mBinding.tv4.setText("设备预警");

                break;
        }
        switch (list.getSubWarningType())
        {
            case "11":
                mBinding.tv5.setText("采集器监测连接线路故障");
                break;
            case "12":
                mBinding.tv5.setText("采集器监控中心通信信道故障");
                break;
            case "13":
                mBinding.tv5.setText("采集器备电源故障");
                break;
            case "14":
                mBinding.tv5.setText("采集器主电源故障");
                break;
            case "21":
                mBinding.tv5.setText("主机复位");
                break;
            case "22":
                mBinding.tv5.setText("主机备电源故障");
                break;
            case "23":
                mBinding.tv5.setText("主机主电源故障");
                break;
            case "31":
                mBinding.tv5.setText("延时");
                break;
            case "32":
                mBinding.tv5.setText("反馈");
                break;
            case "33":
                mBinding.tv5.setText("启动");
                break;
            case "34":
                mBinding.tv5.setText("监管");
                break;
            case "35":
                mBinding.tv5.setText("屏蔽");
                break;
            case "36":
                mBinding.tv5.setText("故障");
                break;
            case "37":
                mBinding.tv5.setText("火警");
                break;
            case "38":
                mBinding.tv5.setText("测试");
                break;
            case "39":
                mBinding.tv5.setText("电源故障");
                break;
        }

        mBinding.tv6.setText(list.getWarningTime());
        mBinding.tv7.setText(list.getPloop());
        mBinding.tv8.setText(list.getPpoint());
        mBinding.tv9.setText(list.getLevel());
        mBinding.tv10.setText(list.getProjectName());
        mBinding.tv11.setText(list.getProjectId());
        mBinding.tv12.setText((list.getProjectPrincipalName() == null) ? "" : ""+list.getProjectPrincipalName());
        mBinding.tv13.setText((list.getProjectPrincipalPhone() == null) ? "" : ""+list.getProjectPrincipalPhone());
        mBinding.tv14.setText(list.getProjectArea());
        mBinding.tv15.setText((list.getRdescribe() == null) ? "" : ""+list.getRdescribe());
        mBinding.affirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.getApi().getearlyRecordUpdate(muserId,"2",mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(EarlyWarningDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                EventBus.getDefault().post(new EarilDetailEvent());
                                finish();
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });
            }
        });


        mBinding.affirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.getApi().getearlyRecordUpdate(muserId,"4",mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(EarlyWarningDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                EventBus.getDefault().post(new EarilDetailEvent());
                                finish();
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });
            }
        });
        mBinding.affirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.getApi().getearlyRecordUpdate(muserId,"3",mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(EarlyWarningDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                EventBus.getDefault().post(new EarilDetailEvent());
                                finish();
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });
            }
        });
    }
}
