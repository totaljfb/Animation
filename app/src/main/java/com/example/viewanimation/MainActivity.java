package com.example.viewanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import static com.example.viewanimation.FloatingButtonStatus.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View view1;
    private View view2;
    private View view3;
    private FloatingActionButton fab;
    private FloatingButtonStatus fabstatus = Initiate;
    private ViewHandler viewHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(this);
        view1 = findViewById(R.id.imageView1);
        view2 = findViewById(R.id.imageView2);
        view3 = findViewById(R.id.imageView3);
        viewHandler = new ViewHandler(view1, view2, view3);
    }

    @Override
    public void onClick(View v){
        int h = 0;
        ViewGroup.LayoutParams layoutParams = null;
        ObjectAnimator objectAnimator = null;
        ValueAnimator va = null;
        int mDuration = 500;
        switch (v.getId()){
            case R.id.floatingActionButton2:
                switch (fabstatus){
                    case Initiate:
                        h = view2.getHeight();
                        layoutParams = view3.getLayoutParams();
                        layoutParams.height = h/2;
                        view3.setLayoutParams(layoutParams);
                        objectAnimator = ObjectAnimator.ofFloat(view3, "y", h, h/2);
                        objectAnimator.setDuration(500);
                        objectAnimator.start();

                        va = ValueAnimator.ofInt(h,h/2);
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
                        fabstatus = Navigate;
                        fab.setImageResource(android.R.drawable.ic_menu_directions);
                        break;
                    case Navigate:
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
                        fabstatus = Stop;
                        fab.setImageResource(android.R.drawable.presence_busy);
                        break;
                    case Stop:
                        h = view1.getHeight();
                        objectAnimator = ObjectAnimator.ofFloat(view1, "y", 0, -h);
                        objectAnimator.setDuration(500);
                        objectAnimator.start();

                        va = ValueAnimator.ofInt(h,2*h);
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

                        objectAnimator = ObjectAnimator.ofFloat(view2, "y", 2*h, 0);
                        objectAnimator.setDuration(500);
                        objectAnimator.start();
                        fabstatus = Initiate;
                        fab.setImageResource(android.R.drawable.ic_menu_search);
                        break;
                    default:
                        break;
                }

            default:
                break;
        }
    }
    @Override
    public void onBackPressed(){
        switch(fabstatus){
            case Initiate:
                System.exit(-1);
                break;
            case Navigate:

                fabstatus = Initiate;
                break;
            case Stop:

                fabstatus = Navigate;
                break;
        }
    }
}
