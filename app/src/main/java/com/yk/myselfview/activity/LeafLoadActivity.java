package com.yk.myselfview.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yk.myselfview.R;
import com.yk.myselfview.views.LeafLoadView;

public class LeafLoadActivity extends AppCompatActivity {
    private int progress;
    private int max=100;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    progress = progress + 1;
                    mLeafLoadView.setPrograss(progress);
                    mLeafLoadView2.setPrograss(progress);
                    mHandler.sendEmptyMessageDelayed(1,500); //800ms发送一次
                    break;
                default:
                    break;
            }
        }
    };
    private LeafLoadView mLeafLoadView;
    private View mImageview;
    private LeafLoadView mLeafLoadView2;
    private View mMImageview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_load);
        mLeafLoadView = (LeafLoadView) findViewById(R.id.leafview);
        mImageview = findViewById(R.id.fan_p);
        mLeafLoadView2 = (LeafLoadView) findViewById(R.id.leafview_2);
        mMImageview2 = findViewById(R.id.fan_p2);
        mLeafLoadView.setTotalProgress(max);
        mLeafLoadView.rotationView(mImageview);

        mLeafLoadView2.setTotalProgress(max);
        mLeafLoadView2.rotationView(mMImageview2);
        mHandler.sendEmptyMessageDelayed(1,0);
    }
}
