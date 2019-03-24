package com.wb.weibao.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lidroid.xutils.util.LogUtils;

import java.util.Date;

public class DataChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Date date = new Date(System.currentTimeMillis());
        int minutes = date.getMinutes();
        if (minutes % 2 == 0) {//2的倍数
            LogUtils.e( "ee==开始获取接口");
        } else {
            LogUtils.e(  "ee==当前分钟" + minutes);
        }

    }
}
