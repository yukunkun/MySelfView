package com.yk.myselfview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yk.myselfview.utils.Chart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by on 2017/5/27.
 */

public class ZqChart extends View {
    private Paint mPaintbg;
    private Paint mPaintMonthbg;
    private Paint mPaintLine;
    private Paint mPaintText;
    private Paint mPaintChoose;
    private Paint mPaintTouch;
    private int defaultWidth;
    private int defaultUnitHeight;
    List<Chart> datas;
    TreeSet<Chart> months;
    private int mChooseMonth;

    private List<Chart> mDatasSingle;
    private Chart mTouch;
    private float lastX;
    private float mDMoneyRateW;
    private int mMeasuredWidth;



    public ZqChart(Context context) {
        super(context);
        init();
    }


    public ZqChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZqChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintbg = new Paint();
        mPaintbg.setAntiAlias(true);
        mPaintbg.setStyle(Paint.Style.FILL);
        mPaintbg.setColor(Color.DKGRAY);
        mPaintMonthbg = new Paint();
        mPaintMonthbg.setAntiAlias(true);
        mPaintMonthbg.setStyle(Paint.Style.FILL);
        mPaintMonthbg.setColor(Color.BLACK);

        mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setColor(Color.WHITE);
        mPaintTouch = new Paint();
        mPaintTouch.setAntiAlias(true);
        mPaintTouch.setStyle(Paint.Style.STROKE);
        mPaintTouch.setStrokeWidth(3);
        mPaintTouch.setColor(Color.WHITE);

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(25);

        mPaintChoose = new Paint();
        mPaintChoose.setAntiAlias(true);
        mPaintChoose.setStyle(Paint.Style.FILL);
        mPaintChoose.setColor(Color.WHITE);

        months=new TreeSet<>(new Comparator<Chart>() {
            @Override
            public int compare(Chart o1, Chart o2) {
                return o1.getDateMonth()-o2.getDateMonth();
            }
        });

        defaultWidth=dip2px(getContext(),200);
        defaultUnitHeight=dip2px(getContext(),25);

        mChooseMonth=2;
        mDatasSingle = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (datas!=null&&datas.size()>0){

            for (Chart data : datas) {
                months.add(data);
            }
            if (widthmode==MeasureSpec.AT_MOST){
                width=defaultWidth;
            }
            if (heightmode==MeasureSpec.AT_MOST){
                height= (int) (defaultUnitHeight*months.size()*1.25);
            }
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();
        Rect rect = new Rect(0,0, mMeasuredWidth,measuredHeight);
        canvas.save();
        //裁剪画布,保存控件
        canvas.clipRect(rect);
        canvas.drawRect(rect,mPaintbg);
        Rect bounds = new Rect(); //文字的宽高
        mPaintText.getTextBounds("01月",0,"01月".length(), bounds);//将文字的宽高保存到bounds里
        int xUnit=(mMeasuredWidth -bounds.width()*2)/15; //平均分为15分,号数的位置

//        int xUnit=(mMeasuredWidth -bounds.width()*3)/6; //平均分为六分,号数的位置

        for (int i = 1; i <=15; i++) {
            String day=""+new DecimalFormat("#00").format(i*2);
            //draw 每天的日期
            canvas.drawText(day+"",i*xUnit-bounds.width()/4,measuredHeight-bounds.height(),mPaintText);
        }
        //每一月的位置控制
        int count=1;
        for (Chart month : months) {
            //draw 月份
            String day=""+new DecimalFormat("#00").format(month.getDateMonth());
            if (month.getDateMonth()==mChooseMonth){ //判断触摸到了那一个月份
                mPaintMonthbg.setColor(Color.RED);
            }else {
                mPaintMonthbg.setColor(Color.BLACK);
            }
            //画椭圆,其实这就是矩形的变形,得到椭圆的两个焦点,有矩形内切出来的椭圆
            RectF oval = new RectF(
                    //起始的位置.获取到屏幕的宽度,且起始位置默认为文字的宽度的两倍
                    mMeasuredWidth - bounds.width() * 2,
                    measuredHeight - bounds.height() - count * defaultUnitHeight - bounds.height()-bounds.height()/2,
                    mMeasuredWidth - bounds.width() * 2 + bounds.width()+bounds.width()/2,
                    measuredHeight - bounds.height() - count * defaultUnitHeight + bounds.height() / 2
            );
            canvas.drawOval(oval,mPaintMonthbg);//画椭圆
            //draw 月份的文字,位置很重要.bounds.width()*2+bounds.width()/4  文字的起始位置
            canvas.drawText(day+"月", mMeasuredWidth -bounds.width()*2+bounds.width()/4,measuredHeight-bounds.height()-count*defaultUnitHeight,mPaintText);
            month.setMonthAres(oval); //这里是位置的保存,为了之后触摸的位置提供数据
            count++; //更新月份的计数更新
        }
        if (mChooseMonth==0){
            canvas.restore();
            return;
        }
        if (mDatasSingle.size()==0){
            canvas.restore();
            return;
        }
        //开始画折线,数据准备
        float minMoney= mDatasSingle.get(0).getMoney();
        float maxMoney= mDatasSingle.get(0).getMoney();
        //获取到最小和最大的数据
        for (Chart data : mDatasSingle) {
            minMoney=data.getMoney()<minMoney?data.getMoney():minMoney;
            maxMoney=data.getMoney()>maxMoney?data.getMoney():maxMoney;
        }
        //获取到比例
       float dMoneyRateH=months.size()*defaultUnitHeight/(maxMoney-minMoney);
        mDMoneyRateW = 15*xUnit/(30);
        if (mDatasSingle.size()==1){
            canvas.drawPoint(mDatasSingle.get(0).getDateDay()* mDMoneyRateW,measuredHeight-bounds.height()-(mDatasSingle.get(0).getMoney()-minMoney)*dMoneyRateH,mPaintLine);
            canvas.restore();
            return;
        }
        Path path=new Path();
        //为什么要排序??????
        Collections.sort(mDatasSingle, new Comparator<Chart>() {
            @Override
            public int compare(Chart o1, Chart o2) {
                return o1.getDateDay()-o2.getDateDay();
            }
        });
        //获取到折线的起始点
        float starX = mDatasSingle.get(0).getDateDay() * mDMoneyRateW;
        float starY = measuredHeight - bounds.height()*2 - (mDatasSingle.get(0).getMoney()-minMoney) * dMoneyRateH;
        //path 画折线
        path.moveTo(starX, starY);
        //存储点位
        mDatasSingle.get(0).pointX=starX;
        mDatasSingle.get(0).pointY=starY;
        //存储位置
        mDatasSingle.get(0).ares=new RectF(starX- mDMoneyRateW /2,measuredHeight-bounds.height()-months.size()*defaultUnitHeight,starX+ mDMoneyRateW /2,measuredHeight-bounds.height());
        for (int i = 1; i < mDatasSingle.size(); i++) {
            float x = mDatasSingle.get(i).getDateDay() * mDMoneyRateW;
            float y = measuredHeight - bounds.height()*2 - (mDatasSingle.get(i).getMoney()-minMoney) * dMoneyRateH;
            //让起始点和末尾点更新
            path.lineTo(x, y);
            mDatasSingle.get(i).pointX=x;
            mDatasSingle.get(i).pointY=y;
            mDatasSingle.get(i).ares=new RectF(x- mDMoneyRateW /2,measuredHeight-bounds.height()-months.size()*defaultUnitHeight,x+ mDMoneyRateW /2,measuredHeight-bounds.height());
            Log.i("mDatasSingle","ares:"+ mDatasSingle.get(i).ares);
        }
        //draw 折线 ,主要是位置的更新
        canvas.drawPath(path,mPaintLine);

   if (mTouch!=null){ //判断触摸的位置,draw 竖线
       //画竖线
       canvas.drawLine(mTouch.pointX, measuredHeight - bounds.height()*2,mTouch.pointX, measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight,mPaintTouch);
       //中间的圆
       canvas.drawCircle(mTouch.pointX,mTouch.pointY,Math.max(bounds.height(),bounds.width())/2,mPaintbg);
       canvas.drawCircle(mTouch.pointX,mTouch.pointY,Math.max(bounds.height(),bounds.width())/2,mPaintTouch);
       String day=""+new DecimalFormat("#00").format(mTouch.getDateDay());
       canvas.drawText( day,mTouch.pointX-bounds.width()/3,mTouch.pointY+bounds.height()/2,mPaintText);
       //具体的数值
       Path pathValue=new Path();
       pathValue.moveTo(mTouch.pointX,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight);
       float money = mTouch.getMoney();
       Rect rectMonet = new Rect();
       mPaintText.getTextBounds(money+"",0,(money+"").length(), rectMonet);
       //draw 弧形边框,贝塞尔曲线
       pathValue.lineTo(mTouch.pointX-rectMonet.width()/2,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight);
       pathValue.quadTo(mTouch.pointX-rectMonet.width()*1.0f,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight-rectMonet.height()
                        ,mTouch.pointX-rectMonet.width()/2,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight-rectMonet.height()*2);
       pathValue.lineTo(mTouch.pointX+rectMonet.width()/2,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight-rectMonet.height()*2);
       pathValue.quadTo(mTouch.pointX+rectMonet.width()*1.0f,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight-rectMonet.height()
               ,mTouch.pointX+rectMonet.width()/2,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight);
       pathValue.lineTo(mTouch.pointX,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight);
       canvas.drawPath(pathValue,mPaintTouch);
       canvas.drawText(money+"",mTouch.pointX-rectMonet.width()/2,measuredHeight - bounds.height()*4-months.size()*defaultUnitHeight-rectMonet.height()/2,mPaintText);
   }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //触摸的位置
                float x = event.getX();
                float y = event.getY();

                if (lastX==0||Math.abs(lastX-x)>mDMoneyRateW){
                    Log.e("onTouchEvent","x:"+x);
                    Log.e("onTouchEvent","y:"+y);
                    for (Chart chart : mDatasSingle) {
                        boolean contains = chart.ares.contains(x, y);
                        if (contains){
                            mTouch=chart;
                            invalidate();
                            break;
                        }
                    }
                    lastX =x;
                }

                break;
            case MotionEvent.ACTION_UP:
                //判断出点击的位置,是否属于月份的位置
                float xUp = event.getX();
                float yUp = event.getY();
                if (xUp>6*mDMoneyRateW&&xUp<mMeasuredWidth){
                    for (Chart month : months) {
                        RectF monthAres = month.getMonthAres();
                        if (monthAres.contains(xUp,yUp)){
                            setChooseMonth(month.getDateMonth());
                        }
                    }
                }
                break;
        }

        return true;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setDatas(List<Chart> datas) {
        this.datas = datas;
        mDatasSingle.clear();
        for (Chart data : datas) {
            if (data.getDateMonth()==mChooseMonth)
                mDatasSingle.add(data);
        }
        invalidate();
    }

    public void setChooseMonth(int chooseMonth) {
        mChooseMonth = chooseMonth;
        mTouch=null;
        mDatasSingle.clear();
        for (Chart data : datas) {
            if (data.getDateMonth()==mChooseMonth)
                mDatasSingle.add(data);
        }
        invalidate();
    }
}
