package com.yk.myselfview.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yk.myselfview.R;
import com.yk.myselfview.adapter.VerticalViewpagerAdapter;
import com.yk.myselfview.views.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class VerticalViewPagerActivity extends AppCompatActivity {

    private VerticalViewPager mVerticalViewPager;
    List<Integer> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_view_pager);
        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        getInfo();
        VerticalViewpagerAdapter verticalViewpagerAdapter=new VerticalViewpagerAdapter(this,mList);
        mVerticalViewPager.setAdapter(verticalViewpagerAdapter);

        mVerticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(VerticalViewPagerActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getInfo() {
        mList.add(R.mipmap.image_1);
        mList.add(R.mipmap.image_2);
        mList.add(R.mipmap.image_3);
        mList.add(R.mipmap.image_1);
        mList.add(R.mipmap.image_2);
        mList.add(R.mipmap.image_3);
    }
}
