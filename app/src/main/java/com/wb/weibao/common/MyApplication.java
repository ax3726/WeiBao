package com.wb.weibao.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;

import com.wb.weibao.base.ThisApplication;
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
import com.wb.weibao.model.LoginModel;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2017/11/22 0022.
 */

public class MyApplication extends ThisApplication {
    private static MyApplication instance;
    public static String Base_Path = "";
    private String token = "";//token
    private LoginModel.Data mUserData=null;//用户信息
    private String mProjectId = "";//当前项目id
    public static MyApplication getInstance() {
        return instance;
    }

    public static List<Activity> mList = new LinkedList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Base_Path = Utils.getCacheDirectory(this, Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();

        //缓存初始化
        CacheUtils.getInstance().init(CacheUtils.CacheMode.CACHE_MAX,
                Utils.getCacheDirectory(this, Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
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

    public LoginModel.Data getUserData() {
        return mUserData;
    }

    public void setUserData(LoginModel.Data mUserData) {
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
