package com.wb.weibao.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.lidroid.xutils.util.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_PICTURES;

public class MyUtils {
    private static final String TAG = "MyUtils";


    /**
     * 抓图路径格式：/storage/emulated/0/Android/data/com.hikvision.open.app/files/Pictures/_20180917151634445.jpg
     */
    public static String getCaptureImagePath(Context context) {
        // 首先保存图片
//        File appDir = Environment.getExternalStorageDirectory();
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = System.currentTimeMillis() + ".png";
//        File file = new File(appDir, fileName);
//        Log.i(TAG, "getCaptureImagePath: " + file.getAbsolutePath());
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri uri = Uri.fromFile(file);
//        intent.setData(uri);
//        context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
//        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
        // 首先保存图片/DICM
        File appDir = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));


        return file.getAbsolutePath();
    }


    /**
     * 录像路径格式：/storage/emulated/0/Android/data/com.hikvision.open.app/files/Movies/_20180917151636872.mp4
     */
    public static String getLocalRecordPath(Context context, String cameraname) {
//        File file = context.getExternalFilesDir(DIRECTORY_MOVIES);
//        String path = file.getAbsolutePath() + File.separator + MyUtils.getFileName("") + ".mp4";
//        Log.i(TAG, "getLocalRecordPath: " + path);

        Random rd = new Random();
        String str = "";
        for (int i = 0; i < 3; i++) {

// 你想生成几个字符的，就把9改成几，如果改成１,那就生成一个随机字母．
            str = str + (char) (Math.random() * 26 + 'a');
        }
        // 首先保存图片/DICM
        File appDir = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "video_"+ System.currentTimeMillis() + ".mp4";
//        String fileName = str + "_"+System.currentTimeMillis() + ".mp4";


        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/DCIM/Video", fileName);
        LogUtils.e("getCaptureImagePath==" + file.getAbsolutePath());

//        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 最后通知图库更新
////        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));


//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String filename = "video_" + str +"_"+ sdf.format(new Date(System.currentTimeMillis())) + ".mp4";
//        //需要在Environment.getExternalStorageDirectory().getAbsolutePath() 后边加上/DCIM/Camera
//
//
//        File cameraFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera", filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

//        saveVideoToGallery(context,cameraFile.getAbsoluteFile());
        return file.getAbsolutePath();
    }

//
//    public static void saveVideoToGallery(Context context, File file) {
//        Uri localUri = Uri.fromFile(file);
//        Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
//        context.sendBroadcast(localIntent);
//    }


    /**
     * 获取文件名称（监控点名称_年月日时分秒毫秒）
     *
     * @return 文件名称
     */
    public static String getFileName(String name) {
        Calendar calendar = Calendar.getInstance();
        return name + "_" +
                String.format(Locale.CHINA, "%04d%02d%02d%02d%02d%02d%03d",
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND),
                        calendar.get(Calendar.MILLISECOND));
    }

}
