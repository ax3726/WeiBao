package com.wb.weibao.ui.home;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.wb.weibao.R;
import com.wb.weibao.utils.dialog.MyItemDialogListener;
import com.wb.weibao.utils.dialog.StytledDialog;
import com.wb.weibao.utils.showImageView.adapter.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kate on 2016/5/29.
 */
public class MaxPictureActivity extends Activity {
    private TextView text_num;
    private int pos = 1;
    private ArrayList<String> imageList;
    ViewPager pager = null;

    ArrayList<View> viewContainter = new ArrayList<View>();
    ImagePagerAdapter imagePagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_picture);
        getIntentValue();
        initView();
    }

    private void getIntentValue() {
        pos = getIntent().getIntExtra("pos", 0) + 1;
        imageList = getIntent().getStringArrayListExtra("imageAddress");
    }

    private void initView() {
        pager = (ViewPager) this.findViewById(R.id.viewpager);
        text_num = (TextView) findViewById(R.id.text_num);
        text_num.setText(pos + "/" + imageList.size());
        for (int i = 0; i < imageList.size(); i++) {
            viewContainter.add(LayoutInflater.from(this).inflate(R.layout.viewpager_page, null));
        }

        pager.addOnPageChangeListener(new PageChangeListener());
        imagePagerAdapter=new ImagePagerAdapter(viewContainter,MaxPictureActivity.this,imageList);
        pager.setAdapter(imagePagerAdapter);
        pager.setCurrentItem(pos - 1);

        imagePagerAdapter.setOnItemDeleteClickListener(new ImagePagerAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(View view) {
                finish();
            }
        });


        imagePagerAdapter.setOnItemlongClickListener(new ImagePagerAdapter.onItemlongListener() {
            @Override
            public void onlongClick(View view) {


                final List<String> strings = new ArrayList<>();
                strings.add("保存");
                StytledDialog.showBottomItemDialog(MaxPictureActivity.this, strings, "取消", true, true, new MyItemDialogListener() {
                    @Override
                    public void onItemClick(String text, int position) {

                        switch (position) {
                            case 0:
                                 ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
                                saveImageByUniversal(imageView);
                                break;

                        }
                    }

                    @Override
                    public void onBottomBtnClick() {

                    }
                });

            }
        });
    }


    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            text_num.setText(position + 1 + "/" + imageList.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    protected static final int READ_EXTERNAL_STORAGE = 100;
    protected static final int WRITE_EXTERNAL_STORAGE = 101;

    /**
     * 使用 Universal 作为图片加载器时，保存图片到相册使用的方法
     *
     * @param imageView
     */
    protected void saveImageByUniversal(ImageView imageView) {
        if (checkWriteStoragePermission()) {
            BitmapDrawable bmpDrawable = (BitmapDrawable) imageView.getDrawable();
            MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    bmpDrawable.getBitmap(),
                    String.valueOf(System.currentTimeMillis()),
                    "");
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkWriteStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }


}