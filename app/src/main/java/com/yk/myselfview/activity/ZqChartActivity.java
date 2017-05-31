package com.yk.myselfview.activity;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.yk.myselfview.R;
import com.yk.myselfview.utils.Chart;
import com.yk.myselfview.views.ZqChart;

import java.util.ArrayList;
import java.util.Random;

public class ZqChartActivity extends AppCompatActivity {

    private ZqChart mchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zg_chart);
        mchart = (ZqChart) findViewById(R.id.chart);
        ArrayList<Chart> datas = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            for (int i1 = 0; i1 < 30; i1++) {
                Chart chart=new Chart();
                chart.dateDay= i1+1;
                chart.dateMonth= i+1;
                chart.money= new Random().nextInt(50)+100;
                datas.add(chart);
            }
        }
        mchart.setDatas(datas);
    }
}
