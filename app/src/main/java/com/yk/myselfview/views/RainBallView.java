package com.yk.myselfview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yk.myselfview.utils.Chart;

/**
 * Created by yukun on 17-12-13.
 */

public class RainBallView extends View{
    private Paint mPaint=new Paint();
    private int mWidth=40;
    private int mHeight=40;
    private  Paint.Style painStyle = Paint.Style.STROKE;
    private int color=Color.BLUE;
    private int width=3;

    public RainBallView(Context context) {
        super(context);
        init(context,null,0);
    }

    public RainBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public RainBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
        mWidth=dip2px(context,mWidth);
        mHeight=dip2px(context,mHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth,mWidth);
    }

    public void setPainStyle(Paint.Style painStyle) {
        this.painStyle = painStyle;
    }

    public void setColors(int color) {
        this.color = color;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(painStyle);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(width);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2-width*2,mPaint);
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
