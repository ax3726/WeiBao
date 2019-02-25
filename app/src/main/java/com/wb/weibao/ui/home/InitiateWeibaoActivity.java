package com.wb.weibao.ui.home;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityInitiateWeibaoBinding;
import com.wb.weibao.utils.imageshowpicker.ImageBean;
import com.wb.weibao.utils.imageshowpicker.ImageShowPickerBean;
import com.wb.weibao.utils.imageshowpicker.ImageShowPickerListener;
import com.wb.weibao.utils.imageshowpicker.ImageShowPickerView;
import com.wb.weibao.utils.imageshowpicker.Loader;
import com.wb.weibao.utils.picker.common.LineConfig;
import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.picker.DateTimePicker;
import com.wb.weibao.utils.picker.picker.SinglePicker;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 发起维保activity
 */
public class InitiateWeibaoActivity extends BaseActivity<BasePresenter,ActivityInitiateWeibaoBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiate_weibao;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("维保");
    }



    private static final int REQUEST_CODE_CHOOSE = 233;
    List<ImageBean> list;


    @Override
    protected void initData() {
        super.initData();

            list = new ArrayList<>();

        Log.e("list", "======" + list.size());
        mBinding.itPickerView.setImageLoaderInterface(new Loader());
        mBinding.itPickerView.setNewData(list);
        //展示有动画和无动画

        mBinding.itPickerView.setShowAnim(true);

        mBinding.itPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Matisse.from(InitiateWeibaoActivity.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(5)
                        .gridExpectedSize(300)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE);
//                Toast.makeText(InitiateWeibaoActivity.this, "remainNum" + remainNum, Toast.LENGTH_SHORT).show();

//                list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
//                Toast.makeText(OnePickerActivity.this, list.size() + "========" + position + "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {
//                Toast.makeText(OnePickerActivity.this, "delOnClickListenerremainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.itPickerView.show();



        mBinding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picker();
            }
        });
    }


    List<Uri> mSelected;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
//            mSelected = Matisse.obtainResult(data);
            List<Uri> uriList = Matisse.obtainResult(data);
            if (uriList.size() == 1) {
                mBinding.itPickerView.addData(new ImageBean(getRealFilePath(InitiateWeibaoActivity.this, uriList.get(0))));
            } else {
                List<ImageBean> list = new ArrayList<>();
                for (Uri uri : uriList) {
                    list.add(new ImageBean(getRealFilePath(InitiateWeibaoActivity.this, uri)));
                }
                mBinding.itPickerView.addData(list);
            }
        }
    }


    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;




    }




    @SuppressLint("ResourceAsColor")
    public void Picker() {
        SinglePicker<String> picker = new SinglePicker<>(this,
               new String[]{"水瓶座", "双鱼座", "白羊", "金牛座", "双子座", "巨蟹座",
                        "狮子座", "处女座", "天秤座", "天蝎座", "射手", "摩羯座"} );
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(R.color.btn_cancel_color);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(Color.BLUE);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0x00000000);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFEEEEEE);
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                mBinding.tv1.setText(item);
            }
        });
        picker.show();
    }



}
