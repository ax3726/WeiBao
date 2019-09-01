package com.wb.weibao.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class ThermometerView extends View {

    public ThermometerView(Context context) {
        super(context);
    }

    public ThermometerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private int DEFAULT = dp2px(160);
    private int mNormalColor = Color.parseColor("#DDDDDD");
    private int mTextColor = Color.parseColor("#2A2B1D");
    private int mSelectColor = Color.parseColor("#047AFF");
    //圆形宽度
    private int mRoundWith = dp2px(15);
    //短刻度
    private int mLineShort = dp2px(5);
    //长刻度
    private int mLineLong = dp2px(15);
    private Paint mNormalPaint;
    private Paint mTextPaint;
    private Paint mSelectPaint;
    private int width = 0;
    private int height = 0;
    private int mCurrValue = 0;
    private int mValueLenth = 45;
    private int mValueStage = 5;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth, mHeight;
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = getPaddingLeft() + DEFAULT + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(mWidth, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = getPaddingTop() + DEFAULT + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(mHeight, heightSize);
            }
        }
        width = mWidth;
        height = mHeight;
        setMeasuredDimension(mWidth, mHeight);
    }

    private void initPaint() {
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setColor(mNormalColor);


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(30);

        mSelectPaint = new Paint();
        mSelectPaint.setAntiAlias(true);// 设置画笔的锯齿效果
        mSelectPaint.setColor(mSelectColor);
        mSelectPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRound(canvas);//画圆角矩形
        drawLine(canvas);//画线
    }

    private void drawRound(Canvas canvas) {

        mSelectPaint.setStyle(Paint.Style.STROKE);
        mSelectPaint.setStrokeWidth(dp2px(1));
        int roundHeight = (height -mRoundWith*2) / mValueLenth;
        LinearGradient backGradient = new LinearGradient(width / 2 - (mRoundWith - dp2px(2)),
                mRoundWith*2 + roundHeight * (mValueLenth - mCurrValue-2),
                width / 2 + (mRoundWith - dp2px(2)), height - dp2px(2),
                new int[]{Color.parseColor("#0AB1FF"), Color.parseColor("#047AFF")}, null, Shader.TileMode.CLAMP);
        mSelectPaint.setShader(backGradient);

        RectF oval = new RectF(width / 2 - mRoundWith, 0, width / 2 + mRoundWith, height);// 设置个新的长方形
        canvas.drawRoundRect(oval, mRoundWith, mRoundWith, mSelectPaint);//第二个参数是x半径，第三个参数是y半径

        mSelectPaint.setStyle(Paint.Style.FILL);


        RectF oval1 = new RectF(width / 2 - (mRoundWith - dp2px(2)),mRoundWith*2+roundHeight*(mValueLenth-mCurrValue-2),
                width / 2 + (mRoundWith - dp2px(2)), height - dp2px(2));// 设置个新的长方形
        canvas.drawRoundRect(oval1, (mRoundWith - dp2px(2)), (mRoundWith - dp2px(2)), mSelectPaint);//第二个参数是x半径，第三个参数是y半径


    }


    private void drawLine(Canvas canvas) {

        int lineHeight = (height - (mRoundWith * 2)) / mValueLenth;
        int lineSpacing = lineHeight - dp2px(2);

        mSelectPaint.setStrokeWidth(dp2px(1));
        mNormalPaint.setStrokeWidth(dp2px(1));

        for (int i = 0; i <= mValueLenth; i++) {
            float multiply = FloatCalculator.multiply(0.05f, i);
            String txt = multiply % 1 == 0 ? String.valueOf((int) multiply) : String.valueOf(multiply);
            float txtWidth = mTextPaint.measureText(txt);
            //左边刻度
            if (i % mValueStage == 0) {//长线
                canvas.drawLine(width / 2 - mRoundWith, height - mRoundWith - (lineHeight * i),
                        width / 2 - mRoundWith - mLineLong,
                        height - mRoundWith - (lineHeight * i), i <= mCurrValue ? mSelectPaint : mNormalPaint);
                // 画文本
                canvas.drawText(txt, width / 2 - mRoundWith - mLineLong - txtWidth - dp2px(5),
                        height - mRoundWith - (lineHeight * i) + dp2px(2), i <= mCurrValue ? mSelectPaint : mTextPaint);
            } else {
                canvas.drawLine(width / 2 - mRoundWith, height - mRoundWith - (lineHeight * i),
                        width / 2 - mRoundWith - mLineShort,
                        height - mRoundWith - (lineHeight * i), i <= mCurrValue ? mSelectPaint : mNormalPaint);
            }

            //右边刻度
            if (i % mValueStage == 0) {//长线
                canvas.drawLine(width / 2 + mRoundWith, height - mRoundWith - (lineHeight * i),
                        width / 2 + mRoundWith + mLineLong,
                        height - mRoundWith - (lineHeight * i), i <= mCurrValue ? mSelectPaint : mNormalPaint);
                // 画文本
                canvas.drawText(txt, width / 2 + mRoundWith + mLineLong + dp2px(5),
                        height - mRoundWith - (lineHeight * i) + dp2px(2), i <= mCurrValue ? mSelectPaint : mTextPaint);
            } else {
                canvas.drawLine(width / 2 + mRoundWith, height - mRoundWith - (lineHeight * i),
                        width / 2 + mRoundWith + mLineShort,
                        height - mRoundWith - (lineHeight * i), i <= mCurrValue ? mSelectPaint : mNormalPaint);
            }


        }

    }

    public void setProgressValue(int value) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, value);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrValue = (int) (Math.round((float) animation.getAnimatedValue() * 100)) / 100;
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(800);
        animator.start();
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }
}
