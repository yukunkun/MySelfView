package com.yk.myselfview.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.yk.myselfview.R;

/**
 * Created by yukun on 17-4-25.
 */
public class LeafLoadView extends View {
    private Paint mPaint=new Paint();
    int mWidth;
    int mHeight;
    private RectF mRect;
    private RectF mRectSeek;
    private Rect mRectCen;
    private Rect seekRect;
    protected int progress=0;
    private int mCurrentProgressPosition;
    private boolean mCircleStyle=true;
    private int mTotalProgress=100;
    private int mAngle;
    private int mStartAngle;
    private Bitmap mRightBitmap;
    private int mBackgroundColor;
    private int mBorderColor;
    private int mSeekColor;
    private int mRotationSpeed=1000;
    private int mBorderWidth=12;
    private int mCircleWidth=6;
    private int mCircleColor;


    public void setRightBitmap(Bitmap rightBitmap) {
        mRightBitmap = rightBitmap;
    }

    public void setPrograss(int prograss) {
        this.progress = prograss;
        if(prograss<=mTotalProgress){
            invalidate();
        }else {
            // TODO: 17-4-26 超出范围了
        }
    }

    public void setTotalProgress(int totalProgress) {
        mTotalProgress = totalProgress;
    }

    private Path mPath=new Path();
    public LeafLoadView(Context context) {
        super(context);
        init(context,null,0);
    }

    public LeafLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public LeafLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
//        mRightBitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.fengshan);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.leafView, defStyleAttr, 0);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.leafView_backgroundColor:
                    mBackgroundColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.leafView_borderColor:
                    mBorderColor = a.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.leafView_seekColor:
                    mSeekColor = a.getColor(attr, Color.YELLOW);
                    break;
                case R.styleable.leafView_rotationSpeed:
                    mRotationSpeed = a.getInt(attr, 1000);
                    break;
                case R.styleable.leafView_circleLeftStyle:
                    mCircleStyle = a.getBoolean(attr,true);
                    break;
                case R.styleable.leafView_borderWidth:
                    mBorderWidth = a.getInt(attr,4);
                    break;
                case R.styleable.leafView_circleWidth:
                    mCircleWidth = a.getInt(attr,6);
                    break;
                case R.styleable.leafView_circleColor:
                    mCircleColor = a.getColor(attr, Color.YELLOW);
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth=w;
        mHeight=h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //外层
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBackgroundColor);
        mRect = new RectF(0,0,mHeight,mHeight);
        mRectCen=new Rect(mHeight/2,0,mWidth-mHeight/2,mHeight);
        canvas.drawArc(mRect,90,180,true,mPaint);
        canvas.drawRect(mRectCen,mPaint);

        /***********seek****************/
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mSeekColor);
        mCurrentProgressPosition = (mWidth/*-mHeight/2*/) * progress / mTotalProgress;
        mRectSeek=new RectF(mBorderWidth,mBorderWidth,mHeight-mBorderWidth,mHeight-mBorderWidth);
        //判断在圆弧内部
        if(mCurrentProgressPosition<=mHeight-mBorderWidth){
            //单边角度
            mAngle = (int) Math.toDegrees(Math.acos((mHeight-mBorderWidth - mCurrentProgressPosition)/ (float) (mHeight-mBorderWidth)));
            //扫过的角度
            mStartAngle = 180 - mAngle;
            //进度的seekbar
            canvas.drawArc(mRectSeek,mStartAngle,mAngle*2,mCircleStyle,mPaint);
        }else { //判断在圆弧外了,需要填满圆弧,并且还要填矩形
            canvas.drawArc(mRectSeek,90,180,mCircleStyle,mPaint);
            seekRect=new Rect(mHeight/2,mBorderWidth,mCurrentProgressPosition-mHeight/2,mHeight-mBorderWidth);
            canvas.drawRect(seekRect,mPaint);
        }
        /***********seek over****************/
        //内层弧度
        mRect=new RectF(mBorderWidth/2,mBorderWidth/2,mHeight-mBorderWidth/2,mHeight-mBorderWidth/2);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mBorderColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        canvas.drawArc(mRect,90,180,false,mPaint);
        //上下两条线
        mPath.moveTo(mHeight/2,mBorderWidth/2);
        mPath.lineTo(mWidth-mHeight/2,mBorderWidth/2);
        canvas.drawPath(mPath,mPaint);
        mPath.close();
        mPath.moveTo(mHeight/2,mHeight-mBorderWidth/2);
        mPath.lineTo(mWidth-mHeight/2,mHeight-mBorderWidth/2);
        canvas.drawPath(mPath,mPaint);
        mPath.close();
        //画圆
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mCircleColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
        canvas.drawCircle(mWidth-mHeight/2,mHeight/2,mHeight/2-mCircleWidth/2,mPaint);
        //画右边的图片
//        mPaint.reset();
//        canvas.drawBitmap(mRightBitmap,mWidth-mHeight+mBorderWidth,mBorderWidth,mPaint);
    }

    public void rotationView(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0,360);
        animator.setDuration(mRotationSpeed);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(Animation.RESTART);
        animator.start();
    }

}
