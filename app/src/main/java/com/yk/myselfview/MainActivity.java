package com.yk.myselfview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yk.myselfview.activity.BMoveViewActivity;
import com.yk.myselfview.activity.BallViewActivity;
import com.yk.myselfview.activity.CircleProgressActivity;
import com.yk.myselfview.activity.CircleViewActivity;
import com.yk.myselfview.activity.LeafLoadActivity;
import com.yk.myselfview.activity.LockViewActivity;
import com.yk.myselfview.activity.RoateMenusActivity;
import com.yk.myselfview.activity.ScrollViewActivity;
import com.yk.myselfview.activity.VerticalViewPagerActivity;
import com.yk.myselfview.activity.ViewPagerActivity;
import com.yk.myselfview.activity.VoiceLoadViewActivity;
import com.yk.myselfview.activity.WaterLoadViewActivity;
import com.yk.myselfview.activity.ZqChartActivity;
import com.yk.myselfview.views.ZqChart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CircleView(View view) {
        Intent intent=new Intent(this, CircleViewActivity.class);
        startActivity(intent);
    }

    public void RoateMenus(View view) {
        Intent intent=new Intent(this, RoateMenusActivity.class);
        startActivity(intent);
    }

    public void LockView(View view) {
        Intent intent=new Intent(this, LockViewActivity.class);
        startActivity(intent);
    }

    public void LeafLoadView(View view) {
        Intent intent=new Intent(this, LeafLoadActivity.class);
        startActivity(intent);
    }

    public void BMoveView(View view) {
        Intent intent=new Intent(this, BMoveViewActivity.class);
        startActivity(intent);
    }
    public void CirclePro(View view) {
        Intent intent=new Intent(this, CircleProgressActivity.class);
        startActivity(intent);
    }

    public void WaterLoad(View view) {
        Intent intent=new Intent(this, WaterLoadViewActivity.class);
        startActivity(intent);
    }

    public void VoiceLoad(View view) {
        Intent intent=new Intent(this, VoiceLoadViewActivity.class);
        startActivity(intent);
    }

    public void ZGChart(View view) {
        Intent intent=new Intent(this, ZqChartActivity.class);
        startActivity(intent);
    }

    public void BallView(View view) {
        Intent intent=new Intent(this, BallViewActivity.class);
        startActivity(intent);
    }

    public void ViewPagers(View view) {
        Intent intent=new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }

    public void scrollView(View view) {
        Intent intent=new Intent(this, ScrollViewActivity.class);
        startActivity(intent);
    }

    public void VerticalViewPager(View view) {
        Intent intent=new Intent(this, VerticalViewPagerActivity.class);
        startActivity(intent);
    }
}
