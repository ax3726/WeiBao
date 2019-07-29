package com.wb.weibao.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
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
import com.wb.weibao.databinding.ActivitySmartpatrolBinding;
import com.wb.weibao.databinding.ActivityWeiRecordBinding;
import com.wb.weibao.databinding.ItemSmartpatrolLayoutBinding;
import com.wb.weibao.databinding.ItemWeibaorecordLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.PatrolUserListBean;
import com.wb.weibao.model.home.DeviceTypeModel;
import com.wb.weibao.model.home.RecordListAppBean;
import com.wb.weibao.model.home.SmartPatrolBean;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.picker.common.LineConfig;
import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.picker.SinglePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmartPatrolActivity extends BaseActivity<BasePresenter, ActivitySmartpatrolBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_smartpatrol;
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
        mTitleBarLayout.setTitle("智慧巡查记录");
        mTitleBarLayout.setRightTxt("开始巡查");
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private List<SmartPatrolBean.DataBean.ListBean> mDataList = new ArrayList<>();
    private CommonAdapter<SmartPatrolBean.DataBean.ListBean> mAdapter;
    private int mPage = 1;
    private int mPageSize = 10;
    private String name = "";
    private String starttime = "";
    private String endtime = "";
    @Override
    protected void initData() {
        super.initData();

mBinding.tvName.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        getPatrolUserList();
    }
});
mBinding.tvTime.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mBinding.tvTime.setTextColor(getResources().getColor(R.color.color_38539f));
        Drawable drawable=getResources().getDrawable(R.drawable.patrol_name_02);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        mBinding.tvTime.setCompoundDrawables(null,null,drawable,null);
        Picker();
    }
});

        mAdapter = new CommonAdapter<SmartPatrolBean.DataBean.ListBean>(aty, R.layout.item_smartpatrol_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, SmartPatrolBean.DataBean.ListBean listBean, int position) {
                ItemSmartpatrolLayoutBinding binding = holder.getBinding(ItemSmartpatrolLayoutBinding.class);
                binding.tv02.setText(mDataList.get(position).getUserName()+"/"+mDataList.get(position).getUserPhone());
                String CreateTime = mDataList.get(position).getPatrolEndTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(mDataList.get(position).getPatrolEndTime(), "yyyy-MM-dd HH:mm:ss");
                binding.tv04.setText(CreateTime);

                binding.rlyItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(aty, WeibaoDetailActivity.class).putExtra("id", listBean.getId() + "").putExtra("type", "1").putExtra("projectid", listBean.getProjectId()));

                    }
                });
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
        getErrorList();

    }


    public void loadData() {
        mPage = 1;
        getErrorList();
    }



    /**
     * 获取维保记录列表
     */
    private void getErrorList() {
        Api.getApi().getPatrolRecordList(MyApplication.getInstance().getProjectId(),MyApplication.getInstance().getmProjectName(),starttime,endtime,name,"" + mPage, "" + mPageSize).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<SmartPatrolBean>(this, false) {
                    @Override
                    public void onSuccess(SmartPatrolBean baseBean) {
                        stopRefersh();
                        SmartPatrolBean.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (mPage == 1) {
                                mDataList.clear();
                            }
                            List<SmartPatrolBean.DataBean.ListBean> list = data.getList();
                            if (list != null && list.size() > 0) {
                                mDataList.addAll(list);
                                if (list.size() < mPageSize) {
                                    mBinding.srlBody.finishLoadmoreWithNoMoreData();
                                }
                            }
                            mAdapter.notifyDataSetChanged();
//
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




    /**
     * 类型列表
     */
    private void getPatrolUserList() {
        Api.getApi().getPatrolUserList()
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<PatrolUserListBean>(this, true) {
                    @Override
                    public void onSuccess(PatrolUserListBean baseBean) {

                        if (baseBean.getData() != null && baseBean.getData().size() > 0) {
                            List<String> lists = new ArrayList<>();
                            for (int i = 0; i < baseBean.getData().size(); i++) {
                                lists.add(baseBean.getData().get(i).toString());
                            }
                            project(lists.toArray(new String[lists.size()]));
                            mBinding.tvName.setTextColor(getResources().getColor(R.color.color_38539f));
                            Drawable drawable=getResources().getDrawable(R.drawable.patrol_name_02);
                            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                            mBinding.tvName.setCompoundDrawables(null,null,drawable,null);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    @SuppressLint("ResourceAsColor")
    public void project(String[] strs ) {
        SinglePicker<String> picker = new SinglePicker<>(this,
                strs);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(R.color.btn_cancel_color);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(Color.BLUE);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0x00000000);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFEEEEEE);
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                mBinding.tvName.setTextColor(getResources().getColor(R.color.color333333));
                Drawable drawable=getResources().getDrawable(R.drawable.patrol_name_01);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                mBinding.tvName.setCompoundDrawables(null,null,drawable,null);
                mBinding.tvName.setText(item);
                name=item;
                mBinding.srlBody.resetNoMoreData();
                mPage = 1;
                getErrorList();
            }
        });
        picker.show();
    }


    private void Picker() {

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mBinding.tvTime.setTextColor(getResources().getColor(R.color.color333333));
                Drawable drawable=getResources().getDrawable(R.drawable.patrol_name_01);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                mBinding.tvTime.setCompoundDrawables(null,null,drawable,null);
                mBinding.tvTime.setText(getTime(date));
                starttime=getTime(date)+" 00:00:00";
                endtime=getTime(date)+" 23:59:59";
                mBinding.srlBody.resetNoMoreData();
                mPage = 1;
                getErrorList();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                //.setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
//                //.setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                //.isDialog(true)//是否显示为对话框样式
                .build();

        pvTime.show();


    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        //"YYYY-MM-DD HH:MM:SS"        "yyyy-MM-dd"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


}

