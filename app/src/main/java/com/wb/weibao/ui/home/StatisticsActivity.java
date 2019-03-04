package com.wb.weibao.ui.home;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityStatisticsBinding;
import com.wb.weibao.model.home.StatisticsModel;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计
 */
public class StatisticsActivity extends BaseActivity<BasePresenter, ActivityStatisticsBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistics;
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
        mTitleBarLayout.setTitle("统计分析");
    }

    @Override
    protected void initData() {
        super.initData();
        initLineData();
        initView(mBinding.lcHead, false);
        initView(mBinding.lcFooter, true);
        getErrorData("1");
        getErrorData("2");
        getFaultData("1");
        getFaultData("2");
    }

    LineData lineData1 = new LineData();
    LineData lineData2 = new LineData();

    private void initLineData() {

        // 不显示坐标点的数据
        lineData1.setDrawValues(false);
        // 不显示定位线
        lineData1.setHighlightEnabled(true);

        // 不显示坐标点的数据
        lineData2.setDrawValues(false);
        // 不显示定位线
        lineData2.setHighlightEnabled(true);

    }

    private void setData(List<StatisticsModel.DataBean> data, String flag, int type) {
        if (data == null) {
            return;
        }
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < data.size(); i++) {
            if (type == 1) {
                entries.add(new Entry((i + 1) * 10, data.get(i).getSum() * 10));

            } else {
                entries.add(new Entry((i + 1) * 10, (int) (data.get(i).getRate() * 100)));
            }
        }

        if (flag.equals("1")) {
            LineDataSet dataSet = new LineDataSet(entries, "本周"); // add entries to dataset
            dataSet.setColor(Color.BLUE);
            dataSet.setValueTextColor(Color.BLUE); // styling, ..
            dataSet.setCircleHoleColor(Color.BLUE);
            dataSet.setCircleColor(Color.BLUE);
            if (type == 1) {
                lineData1.addDataSet(dataSet);
            } else {
                lineData2.addDataSet(dataSet);
            }
        } else {
            LineDataSet dataSet1 = new LineDataSet(entries, "上周"); // add entries to dataset
            dataSet1.setColor(Color.parseColor("#FC5C5E"));
            dataSet1.setValueTextColor(Color.parseColor("#FC5C5E")); // styling, ..
            dataSet1.setCircleHoleColor(Color.parseColor("#FC5C5E"));
            dataSet1.setCircleColor(Color.parseColor("#FC5C5E"));
            if (type == 1) {
                lineData1.addDataSet(dataSet1);
            } else {
                lineData2.addDataSet(dataSet1);
            }
        }


        if (type == 1) {
            if (lineData1.getDataSets().size() == 2) {

                boolean iszero = true;
                for (int i = 0; i < lineData1.getDataSets().size(); i++) {
                    List<Entry> entries1 = ((LineDataSet) lineData1.getDataSets().get(i)).getValues();
                    for (Entry entry : entries1) {
                        if (entry.getY() > 0) {
                            iszero = false;
                        }
                    }

                }

                if (iszero) {
                    mBinding.lcHead.getAxisLeft().setAxisMaximum(50);
                }

                lineData1.setDrawValues(false);
                mBinding.lcHead.setData(lineData1);
                mBinding.lcHead.invalidate(); // refresh
            }

        } else {
            if (lineData2.getDataSets().size() == 2) {
                lineData2.setDrawValues(false);
                mBinding.lcFooter.setData(lineData2);
                mBinding.lcFooter.invalidate(); // refresh
            }

        }

    }


    private void initView(LineChart lineChart, boolean bl) {
        // 不显示数据描述
        lineChart.getDescription().setEnabled(false);
        // 没有数据的时候，显示“暂无数据”
        lineChart.setNoDataText("暂无数据");
        // 不可以缩放
        lineChart.setScaleEnabled(false);


        lineChart.setGridBackgroundColor(Color.WHITE);
        // 不显示y轴右边的值
        lineChart.getAxisRight().setEnabled(false);
        // 不显示图例
        Legend legend = lineChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setYOffset(10f);
        legend.setXOffset(-10f);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.parseColor("#99A9CB"));
        xAxis.setAxisMinimum(10);
        xAxis.setAxisMaximum(70);
        xAxis.setLabelCount(7);
        xAxis.setGridColor(Color.parseColor("#EFEFEF"));
        xAxis.setAxisLineColor(Color.parseColor("#BDBDBD"));


        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.e("tag", "value" + value);
                return toStr((int) value);
            }
        });

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setTextSize(10f);
        yAxis.setTextColor(Color.parseColor("#99A9CB"));
        yAxis.setGridColor(Color.parseColor("#EFEFEF"));
        yAxis.setAxisLineColor(Color.parseColor("#BDBDBD"));
        if (bl) {
            yAxis.setAxisMinimum(0);
            yAxis.setAxisMaximum(100);
            yAxis.setLabelCount(6);
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return (int) value + "%";
                }
            });
        } else {
            yAxis.setAxisMinimum(0);
//            yAxis.setAxisMaximum(50);
            yAxis.setLabelCount(6);
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return ((int) value) / 10 + "次";
                }
            });
        }

    }

    /**
     * 设置推送
     */
    private void getErrorData(String flag) {
        Api.getApi().getErrorData(MyApplication.getInstance().getUserData().getId() + "",
                MyApplication.getInstance().getProjectId(), MyApplication.getInstance().getUserData().getCompanyId(), flag)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<StatisticsModel>(this, true) {
                    @Override
                    public void onSuccess(StatisticsModel baseBean) {

                        setData(baseBean.getData(), flag, 1);
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 故障率
     */
    private void getFaultData(String flag) {
        Api.getApi().getFaultData(MyApplication.getInstance().getUserData().getId() + "",
                MyApplication.getInstance().getProjectId(), MyApplication.getInstance().getUserData().getCompanyId(), flag)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<StatisticsModel>(this, true) {
                    @Override
                    public void onSuccess(StatisticsModel baseBean) {
                        setData(baseBean.getData(), flag, 2);
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    private String toStr(int index) {
        switch (index) {
            case 10:
                return "星期一";

            case 20:
                return "星期二";

            case 30:
                return "星期三";

            case 40:
                return "星期四";

            case 50:
                return "星期五";

            case 60:
                return "星期六";

            case 70:
                return "星期天";

        }
        return "";
    }

}
