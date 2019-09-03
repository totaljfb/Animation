package com.example.viewanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View view1;
    private View view2;
    private View view3;
    private FloatingActionButton fab;
    private ObjectAnimator objectAnimator;
    private FloatingActionButton fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(this);
        fab2 = findViewById(R.id.floatingActionButton3);
        fab2.setOnClickListener(this);
        view1 = findViewById(R.id.imageView1);
        view2 = findViewById(R.id.imageView2);
        view3 = findViewById(R.id.imageView3);
    }

    @Override
    public void onClick(View v){
        int h = 0;
        ViewGroup.LayoutParams layoutParams = null;
        switch (v.getId()){
            case R.id.floatingActionButton2:
                h = view2.getHeight();
                layoutParams = view3.getLayoutParams();
                layoutParams.height = h/2;
                view3.setLayoutParams(layoutParams);
                objectAnimator = ObjectAnimator.ofFloat(view3, "y", 2*h, h/2);
                objectAnimator.setDuration(500);
                objectAnimator.start();

                ValueAnimator va = ValueAnimator.ofInt(h,h/2);
                int mDuration = 500; //in millis
                va.setDuration(mDuration);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int val = (Integer) animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                        layoutParams.height = val;
                        view2.setLayoutParams(layoutParams);
                    }
                });
                va.start();

                objectAnimator = ObjectAnimator.ofFloat(view2, "y", 0, 0);
                objectAnimator.setDuration(500);
                objectAnimator.start();
                break;
            case R.id.floatingActionButton3:
                h = view2.getHeight();

                objectAnimator = ObjectAnimator.ofFloat(view3, "y", h, 2*h);
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
            default:
                break;
        }
    }
}
