package com.wb.weibao.ui.record;

import android.util.Log;
import android.view.View;

import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityRecordDetaulBinding;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;

import org.greenrobot.eventbus.EventBus;

public class RecordDetailActivity extends BaseActivity<BasePresenter,ActivityRecordDetaulBinding> {


    RecordListModel.DataBean.ListBean list=null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_detaul;
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
        list= (RecordListModel.DataBean.ListBean) getIntent().getSerializableExtra("item");
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
                mBinding.tv3.setTextColor(getResources().getColor(R.color.color00A0F1));
                mBinding.aly.setVisibility(View.VISIBLE);
                mBinding.affirm3.setVisibility(View.VISIBLE);
                break;
            case "2":
                mBinding.tv3.setText("处理中");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.colorFACF28));
                mBinding.aly.setVisibility(View.VISIBLE);
                mBinding.affirm1.setVisibility(View.VISIBLE);
                mBinding.affirm2.setVisibility(View.VISIBLE);
                break;
            case "3":
                mBinding.tv3.setText("无灾情");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.color00A0F1));
                break;
            case "4":
                mBinding.tv3.setText("有灾情");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.colorF15453));
                break;
            case "5":
                mBinding.tv3.setText("系统复位");
                mBinding.tv3.setTextColor(getResources().getColor(R.color.color00A0F1));
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
            case "4":
                mBinding.tv4.setText("电力预警");
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
            case "41":
                mBinding.tv5.setText("漏电报警");
                break;
            case "42":
                mBinding.tv5.setText("漏电短路");
                break;
            case "43":
                mBinding.tv5.setText("漏电开路");
                break;
            case "45":
                mBinding.tv5.setText("温度报警");
                break;
            case "46":
                mBinding.tv5.setText("温度短路");
                break;
            case "47":
                mBinding.tv5.setText("温度开路");
                break;
        }
        switch (list.getEquipmentType())
        {
            case "1":
                mBinding.tv91.setText("采集器");
                break;
            case "2":
                mBinding.tv91.setText("无线设备");
                break;
            case "3":
                mBinding.tv91.setText("点位("+list.getPloop()+","+list.getPpoint()+")");
                break;
            case "4":
                mBinding.tv91.setText("电力设备");
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
                Api.getApi().getearlyRecordUpdate("" + MyApplication.getInstance().getUserData().getId(),"2",MyApplication.getInstance().getUserData().getName(),mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(RecordDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                EventBus.getDefault().post(new RecordDetailEvent());
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
                Api.getApi().getearlyRecordUpdate2("" + MyApplication.getInstance().getUserData().getId(),"4",MyApplication.getInstance().getUserData().getName(),mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(RecordDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                EventBus.getDefault().post(new RecordDetailEvent());
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
                Api.getApi().getearlyRecordUpdate2("" + MyApplication.getInstance().getUserData().getId(),"3",MyApplication.getInstance().getUserData().getName(),mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(RecordDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                EventBus.getDefault().post(new RecordDetailEvent());
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
