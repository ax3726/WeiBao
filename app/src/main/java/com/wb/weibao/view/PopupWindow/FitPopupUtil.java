package com.wb.weibao.view.PopupWindow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wb.weibao.R;
import com.wb.weibao.view.WheelStyle;
import com.wb.weibao.view.WheelView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DongJr on 2017/2/27.
 */

public class FitPopupUtil implements View.OnClickListener {

    private final WheelView wheel;
    private View contentView;

    private Activity context;

    private TextView reason1;
    private TextView reason2;
    private TextView reason3;

    private Button btnCommit;
    private Button btnReturn;
    private boolean reason1Selected;
    private boolean reason2Selected;
    private boolean reason3Selected;

    private FitPopupWindow mPopupWindow;

    private OnCommitClickListener listener;

    public FitPopupUtil(Activity context,List<String> wheelString) {

        this.context = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        contentView = inflater.inflate(R.layout.layout_popupwindow, null);
        wheel = (WheelView) contentView.findViewById(R.id.wheel_week_wheel);
        btnCommit= (Button) contentView.findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(this);
        btnReturn= (Button) contentView.findViewById(R.id.btnRuten);
        btnReturn.setOnClickListener(this);
        wheel.setWheelItemList(wheelString);
    }

    public void setOnClickListener(OnCommitClickListener listener) {
        this.listener = listener;
    }

    /**
     * 弹出自适应位置的popupwindow
     *
     * @param anchorView 目标view
     */
    public View showPopup(View anchorView) {
        if (mPopupWindow == null) {
            mPopupWindow = new FitPopupWindow(context,
                    ScreenUtils.getScreenWidth(context) - DensityUtils.dp2px(20),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }

        mPopupWindow.setView(contentView, anchorView);
        mPopupWindow.show();
        return contentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRuten:
                if (listener != null) {
                  mPopupWindow.dismiss();
                }
                break;
            case R.id.btnCommit:
                if (listener != null) {
                    listener.onClick(""+wheel.getCurrentItem());
                }
                mPopupWindow.dismiss();
                break;
        }


    }

    public interface OnCommitClickListener {
        void onClick(String reason);
    }

}
