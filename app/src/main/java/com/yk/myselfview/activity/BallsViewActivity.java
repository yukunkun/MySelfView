package com.yk.myselfview.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yk.myselfview.R;
import com.yk.myselfview.views.RainBallLayout;
import com.yk.myselfview.views.RainBallView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallsViewActivity extends AppCompatActivity {

    private RainBallLayout mRainBallLayout;

    private List<Integer> mListColor=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balls_view);
        mListColor.add(Color.BLUE);
        mListColor.add(Color.BLACK);
        mListColor.add(Color.GREEN);
        mListColor.add(Color.RED);
        mListColor.add(Color.CYAN);

        mRainBallLayout = (RainBallLayout) findViewById(R.id.rain_ball_layout);
        for (int i = 0; i < 1; i++) {
            RainBallView rainBallView=getRainBallView();
            mRainBallLayout.addBallsView(rainBallView);
            //s是否可以相交
            mRainBallLayout.isReward(true);
            mRainBallLayout.startAmimation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRainBallLayout.stopAmimation();
    }

    public void addBall(View view) {
        RainBallView rainBallView = getRainBallView();
        mRainBallLayout.addBallsView(rainBallView);
    }

    @NonNull
    private RainBallView getRainBallView() {
        Random random=new Random();
        int color = random.nextInt(4);
        RainBallView rainBallView=new RainBallView(this);
        rainBallView.setColors(mListColor.get(color));
        rainBallView.setPainStyle(Paint.Style.STROKE);
        rainBallView.setWidth(4);
        return rainBallView;
    }
}
