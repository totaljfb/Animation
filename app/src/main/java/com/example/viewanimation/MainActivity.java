package com.example.viewanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import static com.example.viewanimation.FloatingButtonStatus.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private View view1;
    private View view2;
    private View view3;
    private ListView listView;
    private FloatingActionButton fab;
    private FloatingButtonStatus fabstatus = Initiate;
    private ViewHandler viewHandler;
    private String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
            "Android", "iPhone", "WindowsMobile" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(this);
        view1 = findViewById(R.id.imageView1);
        view2 = findViewById(R.id.imageView2);
        view3 = findViewById(R.id.imageView3);
        listView = (ListView) findViewById(R.id.listview1);
        viewHandler = new ViewHandler(view1, view2, view3, listView);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //listView.setItemChecked(2, true);

        TextView textView = new TextView(this);
        textView.setText("Please select a starting and an ending point:");

        listView.addHeaderView(textView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                /*Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();*/
                if(listView.getCheckedItemCount()<=2){
                   listView.setEnabled(true);
                }
                else{
                    listView.setEnabled(false);
                }
            }
        });

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
                        fabstatus = viewHandler.Scene1To2(fab);
                        break;
                    case Navigate:
                        fabstatus = viewHandler.Scene2To3(fab);
                        break;
                    case Stop:
                        fabstatus = viewHandler.Scene3To1(fab);
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
                fabstatus = viewHandler.Scene2To1(fab);
                break;
            case Stop:
                fabstatus = viewHandler.Scene3To2(fab);
                break;
        }
    }
}
