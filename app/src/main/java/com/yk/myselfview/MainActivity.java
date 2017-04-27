package com.yk.myselfview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yk.myselfview.activity.CircleViewActivity;
import com.yk.myselfview.activity.LeafLoadActivity;
import com.yk.myselfview.activity.LockViewActivity;

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

    public void LockView(View view) {
        Intent intent=new Intent(this, LockViewActivity.class);
        startActivity(intent);
    }

    public void LeafLoadView(View view) {
        Intent intent=new Intent(this, LeafLoadActivity.class);
        startActivity(intent);
    }
}
