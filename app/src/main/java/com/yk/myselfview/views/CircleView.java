package com.yk.myselfview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yukun on 17-4-18.
 */
public class CircleView extends View {

    private Paint mPaint;
    int mStartAngle=0;
    private ArrayList<Integer> mData;
    private int mSum=0;
    private ArrayList<Float> mAvager;
    private int mMWidth;
    private int mMHeight;
    private float mCurrentStartAngle;
    private ArrayList<Integer> mColors=new ArrayList<>();
    private Random random=new Random();

    public CircleView(Context context) {
        super(context);
        initPaint();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mColors.add(Color.RED);
        mColors.add(Color.BLACK);
        mColors.add(Color.BLUE);
        mColors.add(Color.CYAN);
        mColors.add(Color.DKGRAY);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMWidth = w;
        mMHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mData==null){
            return;
        }
        mCurrentStartAngle = mStartAngle;
        canvas.translate(mMWidth/2,mMHeight/2);
        float r=(float) (mMWidth/2*0.5);
        RectF rect=new RectF(-r,-r,r,r);
        for (int i = 0; i < mData.size(); i++) {
            int anInt = random.nextInt(5);
            mPaint.setColor(mColors.get(anInt));
            canvas.drawArc(rect,mCurrentStartAngle,mAvager.get(i),true,mPaint);
            mCurrentStartAngle +=mAvager.get(i);
        }
    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    // 设置数据
    public void setData(ArrayList<Integer> mData) {
        this.mData = mData;
        initDate(mData);
        invalidate();   // 刷新
    }

    private void initDate(ArrayList<Integer> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;
        for (int i = 0; i < mData.size(); i++) {
            mSum +=mData.get(i);
        }

        mAvager = new ArrayList<>();
        for (int j = 0; j < mData.size(); j++) {
            float avag = ((float)mData.get(j) )/ mSum;
            mAvager.add(avag*360);  //对应的角度
            Log.i("---",avag*360+"+"+avag);
        }


    }
}
