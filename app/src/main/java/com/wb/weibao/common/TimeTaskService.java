package com.wb.weibao.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wb.weibao.utils.picker.util.LogUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.service.AlarmReceiver;

/**
 * Created by Administrator on 2019/3/20.
 */

public class TimeTaskService extends Service {

    private static final String TAG = "LocationService";





    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "start LocationService!");
        netThread.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "StartCommand LocationService!");

//        Message message = netHandler.obtainMessage(1);
//        netHandler.sendMessage(message);
        netThread.start();
        return super.onStartCommand(intent, flags, startId);

    }

    Handler netHandler = null;

    /**
     * 收发网络数据的线程
     */
    Thread netThread = new Thread(){
        @Override
        public void run() {
            Looper.prepare();
            com.lidroid.xutils.util.LogUtils.e("qwqw===1");
//            netHandler = new Handler(){
//                public void dispatchMessage(Message msg) {
//                    switch(msg.what){
//                        case 1: //发送位置
//
//                            break;
//
//                    }
//                };
//            };
            Looper.loop();
        }
    };



//    /**
//     * 方式三：采用AlarmManager机制
//     */
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                countDownTimer = new CountDownTimer(20*1000+50, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                       if((millisUntilFinished/1000)%2==0)
//                       {
//                          processOffFlash();
//                       }else
//                           {
//                               processOnFlash();
//                           }
//                }
//
//                @Override
//                public void onFinish() {
//                    processOffFlash();
//                }
//            };
//            countDownTimer.start();
//                System.out.println("99999988");//这是定时所执行的任务
//                com.lidroid.xutils.util.LogUtils.e("99999988==1");
//            }
//        }).start();
//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
//        Intent intent2 = new Intent(this, AutoUpdateReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent2, 0);
//        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
//        return super.onStartCommand(intent, flags, startId);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;

    }

    Camera camera = Camera.open();
    Camera.Parameters p = camera.getParameters();

    //手电筒闪光开启
    private void processOnFlash(){
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
    }
    //手电筒闪光关闭
    private void processOffFlash(){
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(p);
        camera.stopPreview();
    }

    //及时释放
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }




    private CountDownTimer countDownTimer;



}

