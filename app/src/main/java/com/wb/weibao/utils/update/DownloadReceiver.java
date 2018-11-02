package com.wb.weibao.utils.update;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;


import com.wb.weibao.common.MyApplication;

import java.io.File;

/**
 * Created by Administrator on 2018/7/9.
 */

public class DownloadReceiver extends ResultReceiver {
    AppUpdateProgressDialog appUpdateProgressDialogs;
    public DownloadReceiver(Handler handler,AppUpdateProgressDialog appUpdateProgressDialog) {
        super(handler);
        appUpdateProgressDialogs=appUpdateProgressDialog;
    }


    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (resultCode == DownloadService.UPDATE_PROGRESS) {
            int progress = resultData.getInt("progress");
            //(true)就是根据你的进度可以设置现在的进度值。
            //(false)就是滚动条的当前值自动在最小到最大值之间来回移动，形成这样一个动画效果
            appUpdateProgressDialogs.setProgress(progress);


            if (progress == 100) {
                appUpdateProgressDialogs.dismiss();
                //自动安装下载的apk
                File file=new File("/sdcard/jiuhe.apk");
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getInstance().startActivity(installIntent);

            }
        }
    }


}


