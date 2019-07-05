package com.wb.weibao.utils;


import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.PowerManager;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/9/27.
 */

public class DemoUtils {


    public static String ConvertTimeFormat(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(new Date(time));
    }

    /**
     * 格式化日期
     */
    public static String formatDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = null;//获取当前时间
        try {
            curDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str = formatter.format(curDate);
        return str;
    }

    public static Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 检测空字符 或者 换行 空格 等情况
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static <T> String ListToString(ArrayList<String> array, String Separator) {
        if (array == null || array.size() == 0) {
            return "";
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            String str = (String) array.get(i);
            csvBuilder.append(str);
            csvBuilder.append(Separator);
        }
        String csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - Separator.length());
        return csv;
    }

    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return 距离：单位为公里
     */
    public static double DistanceOfTwoPoints(double lat1, double lng1,
                                             double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
//        s = Math.round(s * 10000) / 10;
//        Log.i("距离", s + "");

        s = Math.round(s * 10000d) / 10000d;
        Log.i("距离", s + "");
        s = s * 1000;

        return s;
    }

    public static void wakeUpAndUnlock(Context context) {
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }



    /**
     * 图片按比例大小压缩方法
     * @param srcPath （根据路径获取图片并压缩）
     */
    public static byte[] getimageByte(String srcPath) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float ww = 720f;// 这里设置宽度为720f
        float hh = 1280f;// 这里设置高度为1280f
        // 缩放比。，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1由于是固定比例缩放表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap,1024*2);// 压缩好比例大小后再进行质量压缩
    }
    private static byte[] compressImage(Bitmap image, float expectSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 95;
        while (baos.toByteArray().length / 1024 > expectSize) { // 循环判断如果压缩后图片是否大于1.5M,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 5;// 每次都减少10
        }
        long lengh = baos.toByteArray().length / 1024;
        Log.d("lm", "压缩后的大小:" + lengh);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }



}
