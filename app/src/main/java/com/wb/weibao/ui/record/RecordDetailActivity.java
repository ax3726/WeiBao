package com.wb.weibao.ui.record;

import android.content.Intent;
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
import com.wb.weibao.ui.home.ChangeShiftsActivity;
import com.wb.weibao.view.MyAlertDialog;

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

    }
    private String mId = "";
    private String muserId = "";
    @Override
    protected void initData() {
        super.initData();
        mTitleBarLayout.setTitle(getIntent().getStringExtra("title").toString());
        list= (RecordListModel.DataBean.ListBean) getIntent().getSerializableExtra("item");
        mId=getIntent().getStringExtra("id");
        muserId=getIntent().getStringExtra("userId");
        Log.e("qw",list.toString());
        Log.e("qw",list.getPloop());
        mBinding.tv1.setText(list.getWarningTime());
        switch (list.getStatus())
        {
            case "1":
                mBinding.tv2.setText("待确认");
//                mBinding.tv2.setTextColor(getResources().getColor(R.color.color00A0F1));
                mBinding.aly.setVisibility(View.VISIBLE);
                mBinding.affirm3.setVisibility(View.VISIBLE);
                break;
            case "2":
                mBinding.tv2.setText("待处理");
//                mBinding.tv2.setTextColor(getResources().getColor(R.color.colorFACF28));
                if(getIntent().getStringExtra("title2").equals("火警")){
                    mBinding.aly.setVisibility(View.VISIBLE);
                    mBinding.affirm1.setVisibility(View.VISIBLE);
                    mBinding.affirm1.setBackgroundColor(getResources().getColor(R.color.color36519E));
                }else
                    {
                        mBinding.aly.setVisibility(View.VISIBLE);
                        mBinding.affirm1.setVisibility(View.VISIBLE);
                        mBinding.aly.setVisibility(View.VISIBLE);
                        mBinding.affirm4.setVisibility(View.VISIBLE);
                    }

//                mBinding.tv13.setText();
//                mBinding.tv14.setText();
                break;
            case "3":
            case "4":
                mBinding.tv2.setText("已处理");
//                mBinding.tv2.setTextColor(getResources().getColor(R.color.colorFACF28));
//                mBinding.aly.setVisibility(View.VISIBLE);
//                mBinding.affirm1.setVisibility(View.VISIBLE);
//                mBinding.tv13.setText();
//                mBinding.tv14.setText();

                mBinding.relLay3.setVisibility(View.VISIBLE);
                mBinding.tv14.setText((list.getRdescribe() == null) ? "" : ""+list.getRdescribe());
                break;
        }

        switch (list.getStatus())
        {
            case "1":
                mBinding.tv13.setText("预警中");

                break;
            case "2":
                mBinding.tv13.setText("处理中");

                break;
            case "3":
                mBinding.tv13.setText("无灾情");
                break;
            case "4":
                mBinding.tv13.setText("有灾情");
                break;
            case "5":
                mBinding.tv13.setText("系统复位");
                break;
        }

        mBinding.tv3.setText(getIntent().getStringExtra("title2").toString());
//        switch (list.getWarningType()) {
//            case "1":
//                mBinding.tv4.setText("采集器预警");
//                break;
//            case "2":
//                mBinding.tv4.setText("主机预警");
//
//                break;
//            case "3":
//                mBinding.tv4.setText("设备预警");
//
//                break;
//            case "4":
//                mBinding.tv4.setText("电力预警");
//                break;
//        }

        switch (list.getEquipmentType())
        {
            case "1":
                mBinding.tv4.setText("采集器");
                break;
            case "2":
                mBinding.tv4.setText("无线设备");
                break;
            case "3":
                mBinding.tv4.setText("点位");
                break;
            case "4":
                mBinding.tv4.setText("电力设备");
                break;

        }
        mBinding.tv5.setText("点位 ("+list.getPloop()+","+list.getPpoint()+")");

        mBinding.tv6.setText(list.getLevel());
        mBinding.tv7.setText(list.getEquipmentDetails());

        mBinding.tv9.setText(list.getInstName());
        mBinding.tv91.setText(list.getProjectName());
        mBinding.tv10.setText((list.getProjectPrincipalName() == null) ? "" : ""+list.getProjectPrincipalName());
        mBinding.tv11.setText((list.getProjectPrincipalPhone() == null) ? "" : ""+list.getProjectPrincipalPhone());
        mBinding.tv12.setText(list.getProjectArea());
//
///mBinding.tv15.setText((list.getRdescribe() == null) ? "" : ""+list.getRdescribe());
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
                if(getIntent().getStringExtra("title2").equals("告警")) {
                    Intent intent=new Intent(aty,CLActivity.class);
                    intent.putExtra("id",mId);
                    startActivity(intent);
finish();
                }else
                    {
                        Intent intent=new Intent(aty,AlarmCLActivity.class);
                        intent.putExtra("id",mId);
                        startActivity(intent);
                        finish();
                    }
            }
        });
        mBinding.affirm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api.getApi().getQuickAdd("" + MyApplication.getInstance().getUserData().getId(),MyApplication.getInstance().getUserData().getCompanyId(),"1",MyApplication.getInstance().getProjectId(),mId)
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(RecordDetailActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                new MyAlertDialog(RecordDetailActivity.this).builder().setTitle("提示").setMsg("一键发起维保成功，请在首页-我的维保中查看具体进度").setPositiveButton("我知道了", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        EventBus.getDefault().post(new RecordDetailEvent());
                                        finish();
                                    }
                                }).show();

                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });

            }
        });

    }

}
