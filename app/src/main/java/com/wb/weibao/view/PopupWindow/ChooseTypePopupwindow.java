package com.wb.weibao.view.PopupWindow;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;


import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.R;
import com.wb.weibao.databinding.ItemChooseTypeLayoutBinding;
import com.wb.weibao.databinding.PopupwindowChooseTypeBinding;
import com.wb.weibao.model.main.ChooseTypeModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lm on 2018/10/22.
 */

public class ChooseTypePopupwindow extends PopupWindow {
    private Activity aty;
    private PopupwindowChooseTypeBinding mBinding;

    private ChooseTypeListener mChooseTypeListener;
    private List<ChooseTypeModel> mDataList = new ArrayList<>();
    private CommonAdapter<ChooseTypeModel> mAdapter;
    private String mId = "";

    public ChooseTypePopupwindow(Activity activity, List<ChooseTypeModel> dataList, String id) {
        aty = activity;
        mId = id;
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
        }

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.popupwindow_choose_type, null, false);

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
        mAdapter = new CommonAdapter<ChooseTypeModel>(aty, R.layout.item_choose_type_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, ChooseTypeModel chooseTypeModel, int position) {
                ItemChooseTypeLayoutBinding binding = holder.getBinding(ItemChooseTypeLayoutBinding.class);
                binding.tvTxt.setText(chooseTypeModel.getName());
                binding.tvTxt.setSelected(chooseTypeModel.getId().equals(mId));
                binding.rlyItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mChooseTypeListener != null) {
                            mChooseTypeListener.onItem(chooseTypeModel);
                        }
                        dismiss();
                    }
                });
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);
        this.update();
    }

    /**
     * 显示popupWindow
     */
    public void showPopupWindow(View view) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(view, 0, 0);
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

    public void setChooseTypeListener(ChooseTypeListener mChooseTypeListener) {
        this.mChooseTypeListener = mChooseTypeListener;
    }

    public interface ChooseTypeListener {
        void onItem(ChooseTypeModel item);//选中内容
    }

}
