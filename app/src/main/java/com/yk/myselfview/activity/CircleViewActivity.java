package com.yk.myselfview.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yk.myselfview.R;
import com.yk.myselfview.views.CircleView;

import java.util.ArrayList;

public class CircleViewActivity extends AppCompatActivity {

    private CircleView mCircleView;
    private ArrayList<Integer> mIntegers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        mCircleView = (CircleView) findViewById(R.id.circleview);
        getInfo();
        mCircleView.setStartAngle(0);
        mCircleView.setData(mIntegers);
    }
    private void getInfo() {
        mIntegers = new ArrayList<>();
        mIntegers.add(50);
        mIntegers.add(20);
        mIntegers.add(120);
        mIntegers.add(80);
        mIntegers.add(10);
        mIntegers.add(5);
        mIntegers.add(20);
        mIntegers.add(5);
        mIntegers.add(10);
        mIntegers.add(5);
    }
}
