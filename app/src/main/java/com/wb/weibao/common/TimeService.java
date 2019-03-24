package com.wb.weibao.common;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TimeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {

        super.onCreate();
        Log.i("liujun", "后台进程被创建。。。");

      //服务启动广播接收器，使得广播接收器可以在程序退出后在后天继续执行，接收系统时间变更广播事件
        DataChangeReceiver receiver=new DataChangeReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        intentFilter.setPriority(1000);
        registerReceiver(receiver,intentFilter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("liujun", "后台进程。。。");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {

        Log.i("liujun", "后台进程被销毁了。。。");
        super.onDestroy();
    }

}
