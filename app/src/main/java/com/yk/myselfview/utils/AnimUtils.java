package com.yk.myselfview.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by yukun on 17-2-21.
 */
public class AnimUtils {

    public static void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90)/(total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        //动画周期为500ms
        set.setDuration(1 * 400).start();
    }

    public static void doAnimateClose(final View view, int index, int total,
                                int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f), //bug
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),//0.1为了避免消失为0，会产生bug
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));

        set.setDuration(1 * 400).start();
    }
}
