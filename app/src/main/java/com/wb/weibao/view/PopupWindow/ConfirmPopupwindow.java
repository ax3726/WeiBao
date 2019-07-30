package com.wb.weibao.view.PopupWindow;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.lling.photopicker.PhotoPickerActivity;
import com.wb.weibao.R;
import com.wb.weibao.adapters.abslistview.CommonAdapter;
import com.wb.weibao.databinding.PopuwindowsOkLayoutBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lm on 2018/10/22.
 */

public class ConfirmPopupwindow extends PopupWindow {
    private             Activity                   aty;
    private             PopuwindowsOkLayoutBinding mBinding;
    private             ArrayList<String>          mImageUUid  = new ArrayList<>();
    private             ConfirmListener            mConfirmListener;
    public static final int                        RequestCode = 1001;
    private             List<String>               mImgs       = new ArrayList<>();
    private             CommonAdapter<String>      mAdapter;

    public ConfirmPopupwindow(Activity activity) {
        aty = activity;

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.popuwindows_ok_layout, null, false);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mBinding.getRoot());
        // 设置Popupwindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置Popupwindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //点击空白处时，隐藏掉pop窗口

        this.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.7f);
        //添加pop窗口关闭事件
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss();
                backgroundAlpha(1f);
            }
        });


        initView();
    }

    private void initView() {
        mBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mBinding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etContent.getText().toString().trim();
                if (mConfirmListener != null) {
                    mConfirmListener.onOk(mImageUUid, content);
                }
                dismiss();


            }
        });
        initAdapter();
        this.update();

    }

    public void addData(String str) {
        mImageUUid.add(str);
    }

    public void setData(String img) {

        mImgs.set(mImgs.size() - 1, img);
        if (mImgs.size() < 4) {
            mImgs.add("");
        }

        mAdapter.notifyDataSetChanged();


    }

    private void initAdapter() {
        mImgs.add("");
        mAdapter = new com.wb.weibao.adapters.abslistview.CommonAdapter<String>(aty, R.layout.item_add_point_img_layout, mImgs) {
            @Override
            protected void convert(com.wb.weibao.adapters.abslistview.ViewHolder viewHolder, String item, int position) {
                ImageView img     = viewHolder.getView(R.id.img);
                ImageView img_del = viewHolder.getView(R.id.img_del);

                if (TextUtils.isEmpty(item)) {
                    img_del.setVisibility(View.GONE);
                    Glide.with(aty).load(R.mipmap.point_add_img_icon).into(img);
                } else {
                    img_del.setVisibility(View.VISIBLE);
                    Glide.with(aty).load(item).into(img);
                }
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(item)) {
                            Intent intent = new Intent(aty, PhotoPickerActivity.class);
                            intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, PhotoPickerActivity.MODE_SINGLE);
                            intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, true);
                            intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 1);
                            aty.startActivityForResult(intent, RequestCode);
                        }
                    }
                });
                img_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(mImgs.get(mImgs.size() - 1))) {
                            mImgs.add("");
                        }
                        mImgs.remove(position);
                        mImageUUid.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mBinding.gvBody.setAdapter(mAdapter);
    }

    /**
     * 显示popupWindow
     */
    public void showPopupWindow() {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(getRootView(), Gravity.BOTTOM, 0, 0);
        } else {
            // this.dismiss();
        }
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = aty.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        aty.getWindow().setAttributes(lp);
    }

    private View getRootView() {
        return ((ViewGroup) aty.findViewById(android.R.id.content)).getChildAt(0);
    }

    public void setConfirmListener(ConfirmListener mConfirmListener) {
        this.mConfirmListener = mConfirmListener;
    }

    public interface ConfirmListener {
        void onOk(ArrayList<String> imageUUid, String txt);//
    }

}
