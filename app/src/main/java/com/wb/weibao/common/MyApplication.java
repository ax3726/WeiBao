package com.wb.weibao.common;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hikvision.open.hikvideoplayer.HikVideoPlayerFactory;
import com.lling.photopicker.PhotoPickerApplication;
import com.lm.lib_common.utils.CacheUtils;
import com.lm.lib_common.utils.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;
import com.wb.weibao.base.ThisApplication;
import com.wb.weibao.model.LoginModel;

import java.util.LinkedList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/11/22 0022.
 */

public class MyApplication extends ThisApplication {
    private static MyApplication instance;
    public static String Base_Path = "";
    private String token = "";//token
    private LoginModel.DataBean mUserData = null;//用户信息
    private String mProjectId = "";//当前项目id
    private String mRegistrationID = "";//RegistrationID
    private String JSESSIONID = "";

    public static MyApplication getInstance() {
        return instance;
    }

    public static List<Activity> mList = new LinkedList<>();

    public String getJSESSIONID() {
        return JSESSIONID;
    }

    public void setJSESSIONID(String JSESSIONID) {
        this.JSESSIONID = JSESSIONID;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Base_Path = Utils.getCacheDirectory(this, Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        PhotoPickerApplication.setUp(this);
        HikVideoPlayerFactory.initLib(null, true);
        Fresco.initialize(this);
        //缓存初始化
        CacheUtils.getInstance().init(CacheUtils.CacheMode.CACHE_MAX,
                Utils.getCacheDirectory(this, Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
        initJpush();//初始化极光
        this.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                mList.add(activity);
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mList.remove(activity);
            }
        });
        //Bugly
        CrashReport.initCrashReport(getApplicationContext(), "f7035a841a", false);
        //  startAlarm();
    }

    public void startAlarm() {
        /**
         首先获得系统服务
         */
        AlarmManager am = (AlarmManager)
                getSystemService(Context.ALARM_SERVICE);

        /** 设置闹钟的意图，我这里是去调用一个服务，该服务功能就是获取位置并且上传*/
        Intent intent = new Intent(this, TimeTaskService.class);
        PendingIntent pendSender = PendingIntent.getService(this, 0, intent, 0);
        am.cancel(pendSender);

        /**AlarmManager.RTC_WAKEUP 这个参数表示系统会唤醒进程；我设置的间隔时间是10分钟 */
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3 * 1000, pendSender);
    }


    public String getRegistrationID() {
        if (TextUtils.isEmpty(mRegistrationID)) {
            mRegistrationID = JPushInterface.getRegistrationID(this);
        }
        return mRegistrationID;
    }


    public void initJpush() {
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        // 通过极光推送，推送了很多通知到客户端时，如果用户不去处理，就会有很多保留在那里。
//        新版本 SDK (v1.3.0) 增加此功能，限制保留的通知条数。默认为保留最近 5 条通知。
        JPushInterface.setLatestNotificationNumber(this, 5);
        mRegistrationID = JPushInterface.getRegistrationID(this);
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //    layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public String getProjectId() {
        return mProjectId;
    }

    public void setProjectId(String mProjectId) {
        this.mProjectId = mProjectId;
    }

    public LoginModel.DataBean getUserData() {
        return mUserData;
    }

    public void setUserData(LoginModel.DataBean mUserData) {
        this.mUserData = mUserData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static String getBase_Path() {
        return Base_Path;
    }

    public static List<Activity> getList() {
        return mList;
    }

    public static void setList(List<Activity> mList) {
        MyApplication.mList = mList;
    }

    public void exit() {
        try {
            for (Activity activity : mList)
                if (activity != null)
                    activity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

    public static void backToLogin(Context context, Intent intent) {
//        if (TextUtils.isEmpty(getInstance().getToken())) {
//            return;
//        }
//        getInstance().setToken("");
        context.startActivity(intent);
        try {
            for (Activity activity : mList)
                if (activity != null)
                    activity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
