package com.yk.myselfview.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yk.myselfview.R;
import com.yk.myselfview.views.CircleProgressbar;

import java.util.ArrayList;
import java.util.List;

public class CircleProgressActivity extends AppCompatActivity {
    private CircleProgressbar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        init();
    }

    private void init() {
        mProgressbar = (CircleProgressbar) findViewById(R.id.circleprogress);
        List<Integer> mColorList=new ArrayList<>();
        mColorList.add(Color.GRAY);
        mColorList.add(Color.GREEN);
        mColorList.add(Color.BLUE);
        mColorList.add(Color.RED);
        mColorList.add(Color.YELLOW);
        mProgressbar.setColorList(mColorList);

    }
}
