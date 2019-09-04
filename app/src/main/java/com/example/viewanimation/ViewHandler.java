package com.example.viewanimation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class ViewHandler {
    private View view1, view2, view3;
    private ObjectAnimator objectAnimator;
    private ValueAnimator valueAnimator;
    private int originalHeight;
    private int mDuration = 500;
    ViewGroup.LayoutParams layoutParams;


    public ViewHandler(View view1, View view2, View view3){
        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        originalHeight = view2.getHeight();
    }

    public void setView1(View view1) {
        this.view1 = view1;
    }

    public void setView2(View view2) {
        this.view2 = view2;
    }

    public void setView3(View view3) {
        this.view3 = view3;
    }


    public void Scene1To2(){

    }

    public void Scene2To3(){

    }

    public void Scene3To1(){

    }

    public void Scene3To2(){

    }

    public void Scene2To11(){

    }
}
