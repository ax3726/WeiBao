package com.wb.weibao.base;

import android.content.DialogInterface;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;


/**
 * Created by lm on 2017/11/22.
 * Description:
 */

public interface BaseFragmentView {


    void showToast(String s);

    void setEmptyState(@EmptyState int emptyState);

    void showToast(int id);

    Observable<FragmentEvent> lifecycle();
    <T> LifecycleTransformer<T> bindUntilEvent(FragmentEvent event);
    <T> LifecycleTransformer<T> bindToLifecycle();

    /**
     * 隐藏WaitDialog
     */
    void hideWaitDialog();

    /**
     * 显示
     *
     * @return
     */
    void showWaitDialog();


    /**
     * 显示
     *
     * @param message 消息
     * @return
     */
    void showWaitDialog(String message);

    /**
     * 显示
     *
     * @param isCancel       是否可取消
     * @param cancelListener 取消监听
     * @return
     */
    void showWaitDialog(boolean isCancel, DialogInterface.OnCancelListener cancelListener);


    /**
     * @param message        消息
     * @param isCancel       是否可取消
     * @param cancelListener 取消监听
     * @return
     */
    void showWaitDialog(String message, boolean isCancel, DialogInterface.OnCancelListener cancelListener);
    void backToLogin();
}
