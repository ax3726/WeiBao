package com.wb.weibao.ui.home;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityAddDayWeiBaoBinding;
import com.wb.weibao.utils.imageshowpicker.ImageBean;
import com.wb.weibao.utils.imageshowpicker.ImageShowPickerBean;
import com.wb.weibao.utils.imageshowpicker.ImageShowPickerListener;
import com.wb.weibao.utils.imageshowpicker.Loader;
import com.wb.weibao.utils.picker.picker.DatePicker;
import com.wb.weibao.utils.picker.picker.DateTimePicker;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddDayWeiBaoActivity extends BaseActivity<BasePresenter, ActivityAddDayWeiBaoBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_day_wei_bao;
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
        mTitleBarLayout.setTitle("添加维保记录");
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
                Matisse.from(aty)
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


        mBinding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picker();
            }
        });
        mBinding.tvNextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextPicker();
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
                mBinding.itPickerView.addData(new ImageBean(getRealFilePath(aty, uriList.get(0))));
            } else {
                List<ImageBean> list = new ArrayList<>();
                for (Uri uri : uriList) {
                    list.add(new ImageBean(getRealFilePath(aty, uri)));
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

    private void Picker() {
        DatePicker datePicker = new DatePicker(aty, DateTimePicker.YEAR_MONTH_DAY);


        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                mBinding.tvTime.setText(year + "年" + month + "月" + day + "日");
            }
        });
        datePicker.show();
    }
    private void NextPicker() {
        DatePicker datePicker = new DatePicker(aty, DateTimePicker.YEAR_MONTH_DAY);


        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                mBinding.tvNextTime.setText(year + "年" + month + "月" + day + "日");
            }
        });
        datePicker.show();
    }
}
