package com.wb.weibao.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.wb.weibao.common.MyApplication;


/**
 * Created by lm on 2018/7/26
 * 定位帮助类
 */

public class LocationHelper {


    private static LocationHelper mLocationHelper = null;


    public static LocationHelper getInstance() {
        if (mLocationHelper == null) {
            mLocationHelper = new LocationHelper();
        }
        return mLocationHelper;
    }

    private Activity mAty = null;

    private LocationHelper() {
        initLocation();
    }

  /*  //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (mILocationListener != null && aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    mILocationListener.onLocationChanged(aMapLocation);
                } else if (mAty != null && aMapLocation.getErrorCode() == 12)//定位权限没有
                {
                    //创建AlertDialog的构造器的对象
                    AlertDialog.Builder builder = new AlertDialog.Builder(mAty);
                    //设置构造器标题
                    builder.setTitle("温馨提示！");
                    //构造器内容,为对话框设置文本项(之后还有列表项的例子)
                    builder.setMessage("手机定位权限未开启,是否立即开启？");
                    //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
                    builder.setPositiveButton("立即设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getAppDetailSettingIntent(mAty);

                        }
                    });
                    //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
                    builder.setNegativeButton("稍后再说", null);
                    builder.create().show();
                } else {
                    Toast.makeText(MyApplication.getInstance(), aMapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    };*/
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();

    private ILocationListener mILocationListener;

    public void setILocationListener(ILocationListener mILocationListener) {
        this.mILocationListener = mILocationListener;
    }

    /**
     * 跳转到权限设置界面
     */
    private void getAppDetailSettingIntent(Context context) {

        // vivo 点击设置图标>加速白名单>我的app
        //      点击软件管理>软件管理权限>软件>我的app>信任该软件
        Intent appIntent = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
        if (appIntent != null) {
            context.startActivity(appIntent);
         /*  floatingView = new SettingFloatingView(this, "SETTING", mAty.getApplication(), 0);
            floatingView.createFloatingView();*/
            return;
        }

        // oppo 点击设置图标>应用权限管理>按应用程序管理>我的app>我信任该应用
        //      点击权限隐私>自启动管理>我的app
        appIntent = context.getPackageManager().getLaunchIntentForPackage("com.oppo.safe");
        if (appIntent != null) {
            context.startActivity(appIntent);
          /* floatingView = new SettingFloatingView(this, "SETTING", mAty.getApplication(), 1);
            floatingView.createFloatingView();*/
            return;
        }

//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (Build.VERSION.SDK_INT >= 9) {
//            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//            intent.setData(Uri.fromParts("package", mAty.getPackageName(), null));
//        } else if (Build.VERSION.SDK_INT <= 8) {
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//            intent.putExtra("com.android.settings.ApplicationPkgName", mAty.getPackageName());
//        }

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        mAty.startActivity(intent);
    }


    /**
     * 初始化定位
     */
    private void initLocation() {

        // 定位初始化
        mLocClient = new LocationClient(MyApplication.getInstance());
        mLocClient.registerLocationListener(myListener);
        //启动定位
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//高精度模式
        option.setOpenGps(true); // 打开gps
        option.setCoorType("GCJ02"); // 设置坐标类型

        //mLocClient.startIndoorMode();
        option.setScanSpan(3000);
        mLocClient.setLocOption(option);
    }


    public void closeLocation() {
        // 退出时销毁定位
        mLocClient.stop();
        mLocationHelper = null;
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner extends BDAbstractLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null) {
                return;
            }

            if (location.getLocType() == 61 || location.getLocType() == 161) {
                mILocationListener.onLocationChanged(location);
            } else {
                Toast.makeText(MyApplication.getInstance(), "定位失败:" + location.getLocTypeDescription(), Toast.LENGTH_SHORT);
            }



        }


        @Override
        public void onConnectHotSpotMessage(String s, int i) {
        }
    }


    public void startLocation(final Activity activity) {
        mAty = activity;

        mLocClient.start();

    }


    public interface ILocationListener {
        void onLocationChanged(BDLocation location);
    }

}
