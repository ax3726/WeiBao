package com.wb.weibao.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.model.event.ErrorEvent;
import com.wb.weibao.model.event.ProjectChangeEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.net.RetryWithDelayFunction;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.List;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Date date = new Date(System.currentTimeMillis());
        int minutes = date.getMinutes();
        if (minutes % 2 == 0) {//2的倍数
            LogUtils.e( "ee==开始获取接口");
            EventBus.getDefault().post(new ErrorEvent());
        } else {
            LogUtils.e(  "ee==当前分钟" + minutes);
        }

    }






}
