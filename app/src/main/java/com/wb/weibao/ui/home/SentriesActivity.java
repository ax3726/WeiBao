package com.wb.weibao.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lling.photopicker.utils.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivitySentriesBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.CheckListbean;
import com.wb.weibao.model.home.DeviceTypeModel;
import com.wb.weibao.utils.Timeline.ImageLoad;
import com.wb.weibao.utils.Timeline.TestO;
import com.wb.weibao.utils.Timeline.TimelineFragment;
import com.wb.weibao.utils.Timeline.TimelineGroupType;
import com.wb.weibao.utils.Timeline.TimelineObject;
import com.wb.weibao.utils.Timeline.TimelineObjectClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SentriesActivity extends BaseActivity<BasePresenter, ActivitySentriesBinding> implements TimelineObjectClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sentries;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected boolean isTitleBar() {
        return true;
    }

    ArrayList<TimelineObject> objs;

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("查岗");
    }

    private TimelineFragment mFragment;

    @Override
    protected void initData() {
        super.initData();

        getchecklist();
        // instantiate the TimelineFragment




        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.getApi().getCheckadd2(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", MyApplication.getInstance().getProjectId())
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(SentriesActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {

                                showToast("采集器已查岗");
                                getchecklist();
                            }

                            @Override
                            public void onFail(String errMsg) {
                                getchecklist();
                            }
                        });
            }
        });


    }


    private void loadFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.commit();
    }

    @Override
    public void onTimelineObjectClicked(TimelineObject timelineObject) {
        Toast.makeText(getApplicationContext(), "Clicked: " + timelineObject.getTimestamp(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimelineObjectLongClicked(TimelineObject timelineObject) {
        Toast.makeText(getApplicationContext(), "LongClicked: " + timelineObject.getTimestamp(), Toast.LENGTH_LONG).show();
    }


    public void getchecklist() {
        Api.getApi().getChecklist("1", "30", MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<CheckListbean>(this, true) {
                    @Override
                    public void onSuccess(CheckListbean baseBean) {
                        LogUtils.d("baseBean==", baseBean.toString());
                        objs = new ArrayList<>();
                        for (int i = 0; i < baseBean.getData().getList().size(); i++) {
                            String updateTime = "";
                            if (baseBean.getData().getList().get(i).getStatus().equals("1")) {
                                updateTime = "待确认";
                            }else  if (baseBean.getData().getList().get(i).getStatus().equals("2")) {
                                updateTime = transferLongToDate("yyyy.MM.dd HH:mm:ss", baseBean.getData().getList().get(i).getConfirmTime());
                            } else {
//                                updateTime = "未应答(10分中内未应答)";
                                updateTime = "超时未确认";
                            }

//                            if (baseBean.getData().getList().get(i).getStatus().equals("3")) {
//                                updateTime = "超时未确认";
//                            } else {
//                                updateTime = transferLongToDate("yyyy-MM-dd HH:mm:ss", baseBean.getData().getList().get(i).getConfirmTime());
//
//                            }
                            objs.add(new TestO(transferLongToDate("yyyy-MM-dd HH:mm:ss", baseBean.getData().getList().get(i).getCreateTime()), updateTime));
                        }

                        //Set data
                        mFragment = new TimelineFragment();
                        mFragment.setData(objs, TimelineGroupType.MONTH);
                        //Set configurations

                        loadFragment(mFragment);

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
//        mFragment.addOnClickListener(this);

    }


    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }


}
