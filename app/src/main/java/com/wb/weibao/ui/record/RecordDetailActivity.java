package com.wb.weibao.ui.record;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.wb.weibao.adapters.abslistview.CommonAdapter;
import com.wb.weibao.adapters.abslistview.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Link;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityRecordDetaulBinding;
import com.wb.weibao.model.record.EventReportListbean;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.home.ChangeShiftsActivity;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.view.MyAlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class RecordDetailActivity extends BaseActivity<BasePresenter, ActivityRecordDetaulBinding> {


    RecordListModel.DataBean.ListBean list = null;

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
        list = (RecordListModel.DataBean.ListBean) getIntent().getSerializableExtra("item");
        mId = getIntent().getStringExtra("id");
        muserId = getIntent().getStringExtra("userId");
        Log.e("qw", list.toString());
        Log.e("qw", list.getPloop());
        mBinding.tv1.setText(list.getWarningTime());
        initAdapter();
//        mBinding.tv1.setText(DemoUtils.ConvertTimeFormat(list.getEarlyTime(), "yyyy-MM-dd HH:mm:ss"));
//        showToast("11===="+list.getStatus());
        switch (list.getStatus()) {
            case "1":
                if (getIntent().getStringExtra("title2").equals("火警")) {
                    mBinding.tv2.setText("待确认");
//                mBinding.tv2.setTextColor(getResources().getColor(R.color.color00A0F1));
                    mBinding.aly.setVisibility(View.VISIBLE);
                    mBinding.affirm3.setVisibility(View.VISIBLE);
                }else
                    {
                        mBinding.tv2.setText("待处理");
                        mBinding.aly.setVisibility(View.VISIBLE);
                        mBinding.affirm1.setVisibility(View.VISIBLE);
                        mBinding.aly.setVisibility(View.VISIBLE);
                        mBinding.affirm4.setVisibility(View.GONE);
                    }
                break;
            case "2":
                mBinding.tv2.setText("待处理");
//                mBinding.tv2.setTextColor(getResources().getColor(R.color.colorFACF28));
                if (getIntent().getStringExtra("title2").equals("火警")) {
                    mBinding.aly.setVisibility(View.VISIBLE);
                    mBinding.affirm1.setVisibility(View.VISIBLE);
                    mBinding.affirm1.setBackgroundColor(getResources().getColor(R.color.color36519E));
                } else {
                    mBinding.aly.setVisibility(View.VISIBLE);
                    mBinding.affirm1.setVisibility(View.VISIBLE);
                    mBinding.aly.setVisibility(View.VISIBLE);
                    mBinding.affirm4.setVisibility(View.GONE);
                }

//                mBinding.tv13.setText();
//                mBinding.tv14.setText();
                break;
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
                getEventReportList();
                mBinding.tv2.setText("已处理");
//                mBinding.tv2.setTextColor(getResources().getColor(R.color.colorFACF28));
//                mBinding.aly.setVisibility(View.VISIBLE);
//                mBinding.affirm1.setVisibility(View.VISIBLE);
//                mBinding.tv13.setText();
//                mBinding.tv14.setText();
                mBinding.relLay3.setVisibility(View.VISIBLE);
//                mBinding.tv14.setText((list.getRdescribe() == null) ? "" : ""+list.getRdescribe());
                break;



        }

//        switch (list.getStatus())
//        {
//            case "1":
//                mBinding.tv13.setText("预警中");
//
//                break;
//            case "2":
//                mBinding.tv13.setText("处理中");
//
//                break;
//            case "3":
//                mBinding.tv13.setText("无灾情");
//                break;
//            case "4":
//                mBinding.tv13.setText("发生火灾");
//                break;
//            case "5":
//                mBinding.tv13.setText("系统复位");
//                break;
//            case "6":
//                mBinding.tv13.setText("火灾误报");
//                break;
//            case "7":
//                mBinding.tv13.setText("测试");
//                break;
//            case "8":
//                mBinding.tv13.setText("其他");
//                break;
//        }

        mBinding.tv3.setText(getIntent().getStringExtra("title3").toString());
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

        switch (list.getEquipmentType()) {
            case "1":
//                mBinding.tv4.setText("采集器");

                mBinding.tv7.setText(list.getEquipmentDetails());
                break;
            case "2":
//                mBinding.tv4.setText("无线设备");
//                mBinding.tv4.setText(list.getEquipmentName());
                mBinding.tv7.setText(list.getEquipmentDetails());
                break;
            case "3":
//                mBinding.tv4.setText("点位");
//                mBinding.tv4.setText(list.getEquipmentName());
                mBinding.tv7.setText(list.getRdescribe());
                break;
            case "4":
//                mBinding.tv4.setText("电力设备");
//                mBinding.tv4.setText(list.getEquipmentName());
                mBinding.tv7.setText(list.getEquipmentDetails());
                break;

        }
        mBinding.tv4.setText(list.getEquipmentName());
        mBinding.tv5.setText("点位 (" + list.getPloop() + "," + list.getPpoint() + ")");

        mBinding.tv6.setText(list.getLevel());


        mBinding.tv9.setText(list.getInstName());
        mBinding.tv91.setText(list.getProjectName());
        mBinding.tv10.setText((list.getProjectPrincipalName() == null) ? "" : "" + list.getProjectPrincipalName());
        mBinding.tv11.setText((list.getProjectPrincipalPhone() == null) ? "" : "" + list.getProjectPrincipalPhone());
        mBinding.tv12.setText(list.getProjectArea());
//
///mBinding.tv15.setText((list.getRdescribe() == null) ? "" : ""+list.getRdescribe());
        mBinding.affirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.getApi().getearlyRecordUpdate("" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(), "2", MyApplication.getInstance().getUserData().getName(), mId)
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
                if (getIntent().getStringExtra("title2").equals("告警")) {
                    Intent intent = new Intent(aty, CLActivity.class);
                    intent.putExtra("id", mId);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(aty, AlarmCLActivity.class);
                    intent.putExtra("id", mId);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mBinding.affirm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api.getApi().getQuickAdd("" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(), MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"", "1", MyApplication.getInstance().getProjectId(), mId)
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

    private List<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    public void getEventReportList() {
        Api.getApi().getEventReportList("" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(), getIntent().getStringExtra("id").toString())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<EventReportListbean>(RecordDetailActivity.this, false) {
                    @Override
                    public void onSuccess(EventReportListbean baseBean) {
                        if(baseBean.getData()!=null) {
                            if(baseBean.getData().getList().size()>0) {
                                if (getIntent().getStringExtra("title2").equals("火警")) {
                                    mBinding.tv13.setText(baseBean.getData().getList().get(0).getCauseName());
                                    mBinding.tv14.setText(baseBean.getData().getList().get(0).getDetailReasons());
                                } else {
                                    if (baseBean.getData().getList().get(0).getIsFault().equals("2")) {
                                        mBinding.tv13.setText("非故障");
                                    } else {
                                        mBinding.tv13.setText("故障");
                                    }
                                    mBinding.tv14.setText(baseBean.getData().getList().get(0).getDetailReasons());
                                }
                                if (!TextUtils.isEmpty(baseBean.getData().getList().get(0).getPicturesOssKeys())) {
                                    String[] split = baseBean.getData().getList().get(0).getPicturesOssKeys().split(";");
                                    if (split != null && split.length > 0) {
                                        for (String str : split) {
                                            mImgs.add(Link.SEREVE+"oss/download?fileName=" + str);
                                        }
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }



                            }
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    private void initAdapter() {
        mAdapter = new CommonAdapter<String>(aty, R.layout.item_add_point_img_layout, mImgs) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                ImageView img = viewHolder.getView(R.id.img);
                ImageView img_del = viewHolder.getView(R.id.img_del);
                img_del.setVisibility(View.GONE);
                GlideUrl glideUrl = new GlideUrl(item, new LazyHeaders.Builder()
                        .addHeader("Cookie", "JSESSIONID=" + MyApplication.getInstance().getJSESSIONID())
                        .build());
                Glide.with(aty).load(glideUrl).into(img);

            }
        };
        mBinding.gvBody.setAdapter(mAdapter);
    }



}
