package com.wb.weibao.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
/**
 * Created by Jf on 2017/8/17
 * 注解： SharedPreferences 工具类
 */
public class SpfUtils {

    private SharedPreferences MsharedPreferences;
    private SharedPreferences.Editor Editor;
    private static SpfUtils spfUtils;

    private String TAG_LOG = "SpfUtils";

    public SpfUtils(Context context) {
        MsharedPreferences = context.getSharedPreferences(TAG_LOG, Context.MODE_PRIVATE);
        Editor = MsharedPreferences.edit();
    }

    public static synchronized SpfUtils getInstance(Context context) {
        if (null == spfUtils) {
            spfUtils = new SpfUtils(context);
        }
        return spfUtils;
    }

    public void clearSpf() {
        Editor.clear();
        Editor.commit();
    }

    public String getSpfString(String key) {
        return MsharedPreferences.getString(key, "");
    }

    public void setSpfString( String key, String value) {
        Editor.putString(key, value);
        Editor.commit();
    }

    public boolean getSpfBoolean(String key) {
        return MsharedPreferences.getBoolean(key, false);
    }

    public void setSpfBoolean( String key, boolean value) {
        Editor.putBoolean(key, value);
        Editor.commit();
    }

    public int getSpfInt(String key) {
        return MsharedPreferences.getInt(key, 0);
    }

    public void setSpfInt( String key, int value) {
        Editor.putInt(key, value);
        Editor.commit();
    }

    public long getSpfLong(String key) {
        return MsharedPreferences.getLong(key, -1L);
    }

    public void setSpfLong( String key, long value) {
        Editor.putLong(key, value);
        Editor.commit();
    }




}
