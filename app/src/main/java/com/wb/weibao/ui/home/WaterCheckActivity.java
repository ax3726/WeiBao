package com.wb.weibao.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityWaterCheckBinding;
import com.wb.weibao.model.home.WaterListModel;
import com.wb.weibao.model.home.WaterModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.picker.common.LineConfig;
import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.picker.SinglePicker;
import com.wb.weibao.widget.FloatCalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 用水-液位检测
 */
public class WaterCheckActivity extends BaseActivity<BasePresenter, ActivityWaterCheckBinding> {
    private List<WaterListModel.DataBean> mDataList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_water_check;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return false;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.llyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataList.size() == 0) {
                    return;
                }
                String[] strs = new String[mDataList.size()];
                for (int i = 0; i < mDataList.size(); i++) {
                    strs[i] = mDataList.get(i).getName();
                }

                projectcameralist(strs);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getWaterList();
    }

    private void getWaterList() {
        Api.getApi().getWaterList(MyApplication.getInstance().getProjectId(), "10003")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<WaterListModel>(this, true) {
                    @Override
                    public void onSuccess(WaterListModel waterListModel) {
                        initView(waterListModel);
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    private void initView(WaterListModel waterListModel) {
        if (waterListModel == null || waterListModel.getData() == null || waterListModel.getData().size() == 0) {
            return;
        }
        mDataList.clear();
        mDataList.addAll(waterListModel.getData());
        setProjectData(mDataList.get(0));
    }

    private void setProjectData(WaterListModel.DataBean dataBean) {
        setText(mBinding.tvTitle, dataBean.getName());
        Api.getApi().getWater(String.valueOf(dataBean.getId()))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<WaterModel>(this, true) {
                    @Override
                    public void onSuccess(WaterModel waterModel) {
                        WaterModel.DataBean data = waterModel.getData();
                        if (data != null) {
                        }
                        mBinding.tvTime.setText("更新时间\t" + DemoUtils.ConvertTimeFormat(data.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
                        mBinding.tvNumber.setText(df.format(data.getRealtimeData()));
                        setText(mBinding.tvNetNo, data.getLotCard());
                        setText(mBinding.tvCaiNo, data.getCode());
                        setText(mBinding.tvName, data.getName());
                        setText(mBinding.tvType, data.getTypeName());
                        setText(mBinding.tvAddress, data.getPosition());
                        mBinding.thvScale.setProgressValue((int) FloatCalculator.divide( data.getRealtimeData(), 0.05f));
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });


    }

    private void setText(TextView txt, String str) {
        txt.setText(TextUtils.isEmpty(str) ? "--" : str);
    }


    @SuppressLint("ResourceAsColor")
    public void projectcameralist(String[] strs) {
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
                mBinding.tvTitle.setText(item);
                setProjectData(mDataList.get(index));
            }
        });
        picker.show();
    }
}
