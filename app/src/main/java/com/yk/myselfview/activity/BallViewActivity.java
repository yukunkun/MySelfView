package com.yk.myselfview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yk.myselfview.R;
import com.yk.myselfview.views.BallView;

public class BallViewActivity extends AppCompatActivity {

    private BallView mBallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_view);
        mBallView = (BallView) findViewById(R.id.ballview);
    }

    public void start(View view) {
        mBallView.startAnimation(mBallView);
    }
}
