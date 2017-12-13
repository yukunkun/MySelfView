package com.yk.myselfview.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yukun on 17-12-13.
 */

public class RainBallLayout extends RelativeLayout {
    int screenWidth;
    int screenHeight;
    int movePos=8;

    List<Balls> mViewList=new ArrayList<>();
    public RainBallLayout(Context context) {
        super(context);
        init(context,null,0);
    }

    public RainBallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public RainBallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                mHandler.sendEmptyMessageDelayed(1,5);
                startMove();
            }
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth=w;
        screenHeight=h;
    }

    private int randomX=0;
    private int randomY=0;
    public void startMove(){
        for (int i = 0; i < mViewList.size(); i++) {

            //保证每一个小球的唯一性，使用对象
            Balls rainBallView= mViewList.get(i);
            //宽度判断
//            Log.i("x-->",rainBallView.getRainBallView().getX()+"");
//            Log.i("y-->",rainBallView.getRainBallView().getY()+"");
//            Log.i("w-->",screenWidth-rainBallView.getRainBallView().getWidth()+"");

            if(rainBallView.getRainBallView().getX()<0||rainBallView.getRainBallView().getX()>=screenWidth-rainBallView.getRainBallView().getWidth()){
                rainBallView.setXtag(-rainBallView.getXtag());
                //随机数出现不能一次将值的正负改变，所以取最大值,否则会在边界上抖
                //×２为了避免是在边界碰撞，位置移动没办法移出来
                randomX=rainBallView.getXtag()*2;
            }else {
                randomX=(int)(Math.random()*rainBallView.getXtag());
            }
            //高度判断
            if(rainBallView.getRainBallView().getY()<0||rainBallView.getRainBallView().getY()>=screenHeight-rainBallView.getRainBallView().getHeight()){
                rainBallView.setYtag(-rainBallView.getYtag());
                //随机数出现不能一次将值的正负改变，所以取最大值
                //×２为了避免是在边界碰撞，位置移动没办法移出来
                randomY=rainBallView.getYtag()*2;
            }else {
                randomY=(int)(Math.random()*rainBallView.getYtag());
            }

            //判断两个小球之间的距离，如果相聚小于两个的半径值的两倍，则接触了，就反弹
            for (int j = 0; j < mViewList.size(); j++) {
                //排除自己
                if(j!=i&&isReward){
                    //x方向的距离的绝对值
                    int xTox=(int) Math.abs(rainBallView.getRainBallView().getX()-mViewList.get(j).getRainBallView().getX());
                    //y方向的距离的绝对值
                    int yToy= (int) Math.abs(rainBallView.getRainBallView().getY()-mViewList.get(j).getRainBallView().getY());
                    //两个球的距离
                    int ballToball=rainBallView.getRainBallView().getWidth();
//                    Log.i("xDis",xTox+"-"+ballToball+"-"+yToy);
                    //x和y方向的距离的绝对值
                    if(xTox<ballToball&&yToy<ballToball){
                        //当前的小球x方向取反
                        rainBallView.setXtag(-rainBallView.getXtag());
                        //随机数出现不能一次将值的正负改变，所以取最大值
                        //×２两个小球一起移动，位移是两倍
                        if(rainBallView.getXtag()>0){
                            randomX=(ballToball-xTox);
                        }else {
                            randomX=-(ballToball-xTox);
                        }
                        randomX= rainBallView.getXtag()*2;
                        //当前的小球y方向取反
                        rainBallView.setYtag(-rainBallView.getYtag());
                        //随机数出现不能一次将值的正负改变，所以取最大值
                        //×２为了避免是在边界碰撞，位置移动没办法移出来
                        if(rainBallView.getYtag()>0){
                            randomY=(ballToball-yToy);
                        }else {
                            randomY=-(ballToball-yToy);
                        }
                        //×２两个小球一起移动，位移是两倍
                        randomY=rainBallView.getYtag()*2;
                        mViewList.get(j).setXtag(-mViewList.get(j).getXtag());
                        mViewList.get(j).setYtag(-mViewList.get(j).getYtag());
                    }
                }
            }
            rainBallView.getRainBallView().setX(rainBallView.getRainBallView().getX()+randomX);
            rainBallView.getRainBallView().setY(rainBallView.getRainBallView().getY()+randomY);
        }
    }

    boolean isReward=false;
    public void isReward(boolean isReward){
        this.isReward=isReward;
    }

    /**
     * add view
     * @param view
     */
    public void addBallsView(RainBallView view){
        Balls balls=new Balls();
        balls.setRainBallView(view);
        balls.setXtag(movePos);
        balls.setYtag(movePos);
//        balls.getRainBallView().setX(200/((int)(Math.random()*9)+1));
//        balls.getRainBallView().setY(200/((int)(Math.random()*9)+1));

        mViewList.add(balls);
        addView(view);
    }

    public void startAmimation(){
        if(mHandler!=null){
            mHandler.sendEmptyMessageDelayed(1,0);
        }
    }

    public void stopAmimation(){
        if(mHandler!=null){
            mHandler.removeMessages(1);
        }
    }

    class Balls{
        private int xtag;
        private int ytag;
        private RainBallView mRainBallView;
        private int xOffer;
        private int yOffer;

        public int getxOffer() {
            return xOffer;
        }

        public void setxOffer(int xOffer) {
            this.xOffer = xOffer;
        }

        public int getyOffer() {
            return yOffer;
        }

        public void setyOffer(int yOffer) {
            this.yOffer = yOffer;
        }

        public RainBallView getRainBallView() {
            return mRainBallView;
        }

        public void setRainBallView(RainBallView rainBallView) {
            mRainBallView = rainBallView;
        }

        public int getXtag() {
            return xtag;
        }

        public void setXtag(int xtag) {
            this.xtag = xtag;
        }

        public int getYtag() {
            return ytag;
        }

        public void setYtag(int ytag) {
            this.ytag = ytag;
        }
    }

}
