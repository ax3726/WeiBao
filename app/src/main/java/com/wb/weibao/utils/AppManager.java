package com.wb.weibao.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;


public class AppManager {

    private static Stack<Activity> activityStack = new Stack<Activity>();
    private static List<Activity> activityList = new ArrayList<>();

    /**
     * 添加Activity到堆栈
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
        activityList.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity2(Class<?> cls) {
        ListIterator<Activity> iterator = activityList.listIterator();
        while (iterator.hasNext()){
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                if (activity != null) {
                    iterator.remove();
                    if(!activity.isFinishing()) {
                        activity.finish();
                    }
                }
            }

        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity2(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressLint("MissingPermission")
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}