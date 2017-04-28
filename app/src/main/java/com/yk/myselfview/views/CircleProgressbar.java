package com.yk.myselfview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;


import com.yk.myselfview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yukun on 17-4-28.
 */
public class CircleProgressbar extends View {
    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;
    private int mRadio;
    private List<Integer> mColorList=new ArrayList<>();
    private int mCircleWidth= 10;
    private int mCirclerColor;
    private int mCircleDuration=1500;
    private Random mRandom=new Random();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mCirclerColor=mColorList.get(mRandom.nextInt(mColorList.size())); //随机的颜色
                    handler.sendEmptyMessageDelayed(1,mCircleDuration);
                break;
            };
        }
    };
    private ValueAnimator mValueAnimator;
    private float mStop;
    private float mStart;
    private ValueAnimator mValueAnimatorEnd;
    private int mCircleRadio;

    public void setColorList(List<Integer> mColorList) {
        this.mColorList = mColorList;
        handler.sendEmptyMessageDelayed(1,0);
        setAnim();
    }

    public CircleProgressbar(Context context) {
        super(context);
        init(context,null,0);
    }

    public CircleProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public CircleProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        width=mRadio*2+mCircleWidth;
        height=mRadio*2+mCircleWidth;
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize
                : width, (heightMode == MeasureSpec.EXACTLY) ? heightSize
                : height);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgressbar, defStyleAttr, 0);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CircleProgressbar_circleDuration:
                    mCircleDuration = a.getInt(attr,500);
                    break;
                case R.styleable.CircleProgressbar_circleRadio:
                    mCircleRadio = a.getInt(attr,25);
                    break;
                case R.styleable.CircleProgressbar_circleWidth:
                    mCircleWidth = a.getColor(attr, Color.GRAY);
                    break;
            }
        }
        a.recycle();


        mRadio=dip2px(context,mCircleRadio);
        mPathMeasure = new PathMeasure();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setColor(mCirclerColor);
        mPath = new Path();
        mPath.addCircle(mRadio+mCircleWidth/2, mRadio+mCircleWidth/2, mRadio, Path.Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();
        mDst = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.addCircle(mRadio+mCircleWidth/2, mRadio+mCircleWidth/2, mRadio, Path.Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mPaint.setColor(mCirclerColor);

        mDst.reset();
        // 硬件加速的BUG
        mDst.lineTo(0,0);
        mStop = mLength * mAnimatorValue;
        mStart = (float) (mStop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        mPathMeasure.getSegment(mStart, mStop, mDst, true);
        canvas.drawPath(mDst, mPaint);
    }

    //停止progressbar
    public void destoryProgressbar() {
        if(handler!=null&&mValueAnimator!=null){
            setVisibility(GONE);
        }
    }

    public void startProgressbar(){
        if(handler!=null&&mValueAnimator!=null){
            setVisibility(VISIBLE);
        }
    }

    //开始的动画
    private void setAnim() {
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.setDuration(mCircleDuration);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.start();
    }

    private void setEndAnim() {
        mValueAnimatorEnd = ValueAnimator.ofInt(mRadio,0);
        mValueAnimatorEnd.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                mRadio=value;
                invalidate();
            }
        });
        mValueAnimatorEnd.setDuration(mCircleDuration);
        mValueAnimatorEnd.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimatorEnd.start();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
