package com.yk.myselfview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yk.myselfview.R;
import com.yk.myselfview.views.GestureLockViewGroup;

public class LockViewActivity extends AppCompatActivity {

    private GestureLockViewGroup mGestureLockViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_view);
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        mGestureLockViewGroup.setAnswer(new int[] { 1, 2, 3, 4,5 });
        mGestureLockViewGroup
                .setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {

                    @Override
                    public void onUnmatchedExceedBoundary() {
                        Toast.makeText(LockViewActivity.this, "错误5次...",
                                Toast.LENGTH_SHORT).show();
                        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                    }

                    @Override
                    public void onGestureEvent(boolean matched) {
                        Toast.makeText(LockViewActivity.this, matched+"",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBlockSelected(int cId) {
                        Toast.makeText(LockViewActivity.this, cId+"",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
