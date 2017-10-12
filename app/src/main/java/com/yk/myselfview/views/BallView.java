package com.yk.myselfview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yukun on 17-6-9.
 */

public class BallView extends View {
    private Paint mPaint;
    private Paint mPaintText;
    private int mWidth;
    private int mHeight;
    private int radio=20;
    private int lastNum=30;
    private int position=12;
    public BallView(Context context) {
        super(context);
        init(context,null,0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(dip2px(context,5));
        mPaintText=new Paint();
        mPaintText.setColor(Color.GREEN);
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setTextSize(dip2px(context,12));
        radio=dip2px(context,10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(-mWidth/4,0,radio,mPaint);
        canvas.drawLine(-mWidth/4-radio/2,0,-mWidth/4+(mWidth/2/lastNum)*position,0,mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(mWidth/4,0,radio,mPaint);
        canvas.drawLine(-mWidth/4+(mWidth/2/lastNum)*position,0,mWidth/4-radio/2,0,mPaint);

        canvas.drawText("1",-mWidth/4-"1".length()*5,radio+radio*2,mPaintText);
        canvas.drawText(lastNum+"",mWidth/4-(lastNum+"").length()*5,radio+radio*2,mPaintText);

        canvas.drawCircle(-mWidth/4+(mWidth/2/lastNum)*position,0,radio,mPaint);
        canvas.drawText(position+"",-mWidth/4+mWidth/2/lastNum*position-radio/2,radio/2,mPaintText);
    }

    //手势还没写,自己玩er了
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){

        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE:
        //触摸的位置
        float x = event.getX();
        float y = event.getY();

//        if (lastX==0||Math.abs(lastX-x)>mDMoneyRateW){
//            Log.e("onTouchEvent","x:"+x);
//            Log.e("onTouchEvent","y:"+y);
//            for (Chart chart : mDatasSingle) {
//                boolean contains = chart.ares.contains(x, y);
//                if (contains){
//                    mTouch=chart;
//                    invalidate();
//                    break;
//                }
//            }
//            lastX =x;
//        }
        break;
        case MotionEvent.ACTION_UP:
        //判断出点击的位置,是否属于月份的位置
        float xUp = event.getX();
        float yUp = event.getY();
//        if (xUp>6*mDMoneyRateW&&xUp<mMeasuredWidth){
//            for (Chart month : months) {
//                RectF monthAres = month.getMonthAres();
//                if (monthAres.contains(xUp,yUp)){
//                    setChooseMonth(month.getDateMonth());
//                }
//            }
//        }
        break;
    }

        return true;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
