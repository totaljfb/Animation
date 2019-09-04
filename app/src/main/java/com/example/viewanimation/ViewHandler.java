package com.example.viewanimation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.viewanimation.FloatingButtonStatus.*;

public class ViewHandler {
    private View view1, view2, view3;
    private ListView listView;
    private ObjectAnimator objectAnimator;
    private ValueAnimator valueAnimator;
    private int mDuration = 500;
    private ViewGroup.LayoutParams layoutParams;
    private FloatingButtonStatus fabstatus = Initiate;


    public ViewHandler(View view1, View view2, View view3, ListView listView){
        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        this.listView = listView;
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


    public FloatingButtonStatus Scene1To2(FloatingActionButton fab){

        int h = view2.getHeight();
        layoutParams = listView.getLayoutParams();
        layoutParams.height = h/2;
        listView.setLayoutParams(layoutParams);
        objectAnimator = ObjectAnimator.ofFloat(listView, "y", h, h/2);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        valueAnimator = ValueAnimator.ofInt(h,h/2);
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                layoutParams.height = val;
                view2.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();

        objectAnimator = ObjectAnimator.ofFloat(view2, "y", 0, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        fabstatus = Navigate;
        fab.setImageResource(android.R.drawable.ic_menu_directions);
        //fab.setEnabled(false);
        return fabstatus;
    }

    public FloatingButtonStatus Scene2To3(FloatingActionButton fab){

        int h = view2.getHeight();
        objectAnimator = ObjectAnimator.ofFloat(listView, "y", h, 2*h);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        objectAnimator = ObjectAnimator.ofFloat(view2, "y", 0, h);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        layoutParams = view1.getLayoutParams();
        layoutParams.height = h;
        view1.setLayoutParams(layoutParams);
        objectAnimator = ObjectAnimator.ofFloat(view1, "y", -h, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        fabstatus = Stop;
        fab.setImageResource(android.R.drawable.presence_busy);
        return fabstatus;
    }

    public FloatingButtonStatus Scene3To1(FloatingActionButton fab){

        int h = view1.getHeight();
        objectAnimator = ObjectAnimator.ofFloat(view1, "y", 0, -h);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        valueAnimator = ValueAnimator.ofInt(h,2*h);
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                layoutParams.height = val;
                view2.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();

        objectAnimator = ObjectAnimator.ofFloat(view2, "y", 2*h, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        fabstatus = Initiate;
        fab.setImageResource(android.R.drawable.ic_menu_search);
        return fabstatus;
    }

    public FloatingButtonStatus Scene3To2(FloatingActionButton fab){

        int h = view2.getHeight();
        objectAnimator = ObjectAnimator.ofFloat(view1, "y", 0, -h);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        objectAnimator = ObjectAnimator.ofFloat(view2, "y", h, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        objectAnimator = ObjectAnimator.ofFloat(listView, "y", 2*h, h);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        fabstatus = Navigate;
        fab.setImageResource(android.R.drawable.ic_menu_directions);
        return fabstatus;
    }

    public FloatingButtonStatus Scene2To1(FloatingActionButton fab){

        int h = view2.getHeight();
        objectAnimator = ObjectAnimator.ofFloat(listView, "y", h, 2*h);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        objectAnimator = ObjectAnimator.ofFloat(view2, "y", 0, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();


        valueAnimator = ValueAnimator.ofInt(h,2*h);
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                layoutParams.height = val;
                view2.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
        fabstatus = Initiate;
        fab.setImageResource(android.R.drawable.ic_menu_search);
        return fabstatus;
    }

}
