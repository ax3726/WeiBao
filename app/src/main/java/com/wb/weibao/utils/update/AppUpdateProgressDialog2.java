package com.wb.weibao.utils.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.wb.weibao.BuildConfig;
import com.wb.weibao.R;


/**
 * Created by zhh on 2017/4/10.
 */

public  class AppUpdateProgressDialog2 extends Dialog {

    private NumberProgressBar numberProgressBar;

    private TextView context;
    private TextView version;
    private TextView text_update;


    public AppUpdateProgressDialog2(Context context,String str,String version) {
        super(context, R.style.Custom_Progress);
        initLayout(str,version);
    }

    public AppUpdateProgressDialog2(Context context, int theme,String str,String version) {
        super(context, R.style.Custom_Progress);
        initLayout(str,version);
    }

    private void initLayout(String str ,String versions) {
        this.setContentView(R.layout.update_progress2);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        context= (TextView) findViewById(R.id.text);
        text_update= (TextView) findViewById(R.id.update_tv);
        version= (TextView) findViewById(R.id.version);
        version.setText("V"+ versions);
        Spanned spanned=Html.fromHtml(str);
        context.setText(spanned);
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
