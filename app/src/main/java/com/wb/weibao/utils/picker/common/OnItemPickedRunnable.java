package com.wb.weibao.utils.picker.common;


import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.widget.WheelView;

final public class OnItemPickedRunnable implements Runnable {
    final private WheelView wheelView;
    private OnItemPickListener onItemPickListener;
    public OnItemPickedRunnable(WheelView wheelView, OnItemPickListener onItemPickListener) {
        this.wheelView = wheelView;
        this.onItemPickListener = onItemPickListener;
    }

    @Override
    public final void run() {
        onItemPickListener.onItemPicked(wheelView.getCurrentPosition(),wheelView.getCurrentItem());
    }
}
