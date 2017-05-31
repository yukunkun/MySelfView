package com.yk.myselfview.utils;

import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by CLOUD on 2017/5/27.
 */

public class Chart {
    public int dateDay;
    public int dateMonth;
    public float money;
    /**
     * 折线图上的文职
     */
    public float pointX;
    public float pointY;
    public RectF ares;
    /**
     * Y轴 上的位置（只用于Y轴绘图 和 选择月份）
     */

    public float monthPointX;
    public float monthPointY;
    public RectF monthAres;

    public RectF getMonthAres() {
        return monthAres;
    }

    public void setMonthAres(RectF monthAres) {
        this.monthAres = monthAres;
    }

    public float getMonthPointX() {
        return monthPointX;
    }

    public void setMonthPointX(float monthPointX) {
        this.monthPointX = monthPointX;
    }

    public float getMonthPointY() {
        return monthPointY;
    }

    public void setMonthPointY(float monthPointY) {
        this.monthPointY = monthPointY;
    }

    public RectF getAres() {
        return ares;
    }

    public void setAres(RectF ares) {
        this.ares = ares;
    }

    public float getPointX() {
        return pointX;
    }

    public void setPointX(float pointX) {
        this.pointX = pointX;
    }

    public float getPointY() {
        return pointY;
    }

    public void setPointY(float pointY) {
        this.pointY = pointY;
    }

    public int getDateDay() {
        return dateDay;
    }

    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
