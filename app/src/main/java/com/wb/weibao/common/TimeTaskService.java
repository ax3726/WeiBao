package com.wb.weibao.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.service.AlarmReceiver;

/**
 * Created by Administrator on 2019/3/20.
 */

public class TimeTaskService extends Service {

    private Runnable runnable;
    private Handler handler;
    private int Time = 1000*3;//周期时间
    private int anHour =8*60*60*1000;// 这是8小时的毫秒数 为了少消耗流量和电量，8小时自动更新一次
    private Timer timer = new Timer();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 方式一：采用Handler的postDelayed(Runnable, long)方法
         */
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                // handler自带方法实现定时器
                System.out.println("33331");
                handler.postDelayed(this, 1000*3);//每隔3s执行

            }
        };
        handler.postDelayed(runnable, 1000*60);//延时多长时间启动定时器

        /**
         * 方式二：采用timer及TimerTask结合的方法
         */
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("99999998");
            }
        };
        timer.schedule(timerTask,
                1000,//延迟1秒执行
                Time);//周期时间


    }
    /**
     * 方式三：采用AlarmManager机制
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                countDownTimer = new CountDownTimer(20*1000+50, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                       if((millisUntilFinished/1000)%2==0)
                       {
                          processOffFlash();
                       }else
                           {
                               processOnFlash();
                           }
                }

                @Override
                public void onFinish() {
                    processOffFlash();
                }
            };
            countDownTimer.start();
                System.out.println("99999988");//这是定时所执行的任务
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent intent2 = new Intent(this, AutoUpdateReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent2, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

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

