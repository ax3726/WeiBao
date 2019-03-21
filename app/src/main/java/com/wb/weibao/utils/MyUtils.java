package com.wb.weibao.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.lidroid.xutils.util.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Locale;

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
        File appDir = Environment.getExternalStorageDirectory();
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        Log.i(TAG, "getCaptureImagePath: " + file.getAbsolutePath());
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
        return file.getAbsolutePath();
    }


    /**
     * 录像路径格式：/storage/emulated/0/Android/data/com.hikvision.open.app/files/Movies/_20180917151636872.mp4
     */
    public static String getLocalRecordPath(Context context) {
//        File file = context.getExternalFilesDir(DIRECTORY_MOVIES);
//        String path = file.getAbsolutePath() + File.separator + MyUtils.getFileName("") + ".mp4";
//        Log.i(TAG, "getLocalRecordPath: " + path);


        // 首先保存图片/DICM
        File appDir = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "video_"+System.currentTimeMillis() + ".mp4";

        File file = new File(appDir, fileName);
        LogUtils.e("getCaptureImagePath=="+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

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
