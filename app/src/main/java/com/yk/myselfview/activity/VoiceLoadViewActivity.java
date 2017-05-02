package com.yk.myselfview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.yk.myselfview.R;
import com.yk.myselfview.views.VoiceLoadView;

public class VoiceLoadViewActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private VoiceLoadView mVoiceLoadView;
    private SeekBar mSeekBar;
    private int max=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_load_view);
        init();
    }

    private void init() {
        mVoiceLoadView = (VoiceLoadView) findViewById(R.id.voiceloadview);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setMax(max);
        mVoiceLoadView.setCurrentCount(0);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            seekBar.setProgress(progress);
            mVoiceLoadView.setCurrentCount((int) (12*progress)/max);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
