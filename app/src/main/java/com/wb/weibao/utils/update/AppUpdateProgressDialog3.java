package com.wb.weibao.utils.update;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.wb.weibao.R;


/**
 * Created by zhh on 2017/4/10.
 */

public  class AppUpdateProgressDialog3 extends Dialog {

    private NumberProgressBar numberProgressBar;

    private TextView context;


    public AppUpdateProgressDialog3(Context context) {
        super(context, R.style.Custom_Progress);
        initLayout();
    }

    public AppUpdateProgressDialog3(Context context, int theme) {
        super(context, R.style.Custom_Progress);
        initLayout();
    }

    private void initLayout() {
        this.setContentView(R.layout.update_progress4);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        context= (TextView) findViewById(R.id.text);
      TextView  text_update= (TextView) findViewById(R.id.update_tv);
        text_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemUpdateListener.onUpdateClick(v);
            }
        });
        this.setCanceledOnTouchOutside(false);//点击dialog背景部分不消失
//        this.setCancelable(false);//dialog出现时，点击back键不消失
    }

    public void setProgress(int mProgress) {
        numberProgressBar.setProgress(mProgress);
    }


    /**
     *  取消按钮的监听接口
     */
    public interface onItemUpdateListener {
        void onUpdateClick(View view);
    }

    private onItemUpdateListener mOnItemUpdateListener;

    public void setOnItemUpdateClickListener(onItemUpdateListener mOnItemUpdateListener) {
        this.mOnItemUpdateListener = mOnItemUpdateListener;
    }

}
