package com.yk.myselfview.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.yk.myselfview.R;
import com.yk.myselfview.utils.ViewPagerAdapter;
import com.yk.myselfview.views.CustomView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private CustomView mCustomView;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        mCustomView = (CustomView) findViewById(R.id.custom);
        mLayout = (LinearLayout) findViewById(R.id.ll_con);

        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(5);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(R.mipmap.image_2);
            list.add(R.mipmap.image_3);
        }
        viewPager.setPageTransformer(false, new AlphaTransformer());
        ViewPagerAdapter adater = new ViewPagerAdapter(this, list);
        viewPager.setAdapter(adater);
    }

    public void startscrol(View view) {

        mCustomView.smoothScrollBy(-100,-100);
//        mCustomView.smoothScrollTo(-300,-300);
    }



    public class AlphaTransformer implements ViewPager.PageTransformer {
        private float MINALPHA = 0.5f;
        private static final float MIN_SCALE = 0.70f;

        /**
         * position取值特点：
         * 假设页面从0～1，则：
         * 第一个页面position变化为[0,-1]
         * 第二个页面position变化为[1,0]
         * @param page
         * @param position
         */
        @Override
        public void transformPage(View page, float position) {
//            Log.i("-----",page.getTag()+" pos: "+position);
               float MIN_SCALE = 0.70f;
               float MIN_ALPHA = 0.5f;

            if (position < -1 || position > 1) {
                page.setAlpha(MIN_ALPHA);
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            } else if (position <= 1) { // [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                if (position < 0) {
                    float scaleX = 1 + 0.3f * position;
                    Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                } else {
                    float scaleX = 1 - 0.3f * position;
                    page.setScaleX(scaleX);
                    page.setScaleY(scaleX);
                }
                page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }
        }
    }



}
