package com.yk.myselfview.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yk.myselfview.R;
import com.yk.myselfview.views.BMoveView;

public class BMoveViewActivity extends Activity {

    private int mFirstPos;
    private int mLastPos;
    private BMoveView mBMoveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmove_view);
        bMoveInit();
    }

    private void bMoveInit() {
        mBMoveView = (BMoveView) findViewById(R.id.bmoveview);
        RadioGroup radioGroup= (RadioGroup) findViewById(R.id.rg_group);
        ((RadioButton) (radioGroup.getChildAt(0))).setChecked(true);
        mFirstPos = 0;
        mBMoveView.startAnim();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    boolean checked = ((RadioButton) (group.getChildAt(i))).isChecked();
                    if(checked){
                        mLastPos = i;
                        mBMoveView.setTwoPos(mFirstPos, mLastPos);
                        mFirstPos = mLastPos;
                    }
                }
            }
        });
    }
}
