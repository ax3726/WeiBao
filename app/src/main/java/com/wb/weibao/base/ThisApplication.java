package com.wb.weibao.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by LiMing on 2016/11/12.
 */
public class ThisApplication extends MultiDexApplication {
    public static Context applicationContext;
    private static ThisApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
    }

    public static ThisApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); // 初始化
    }

}
