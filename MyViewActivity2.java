package com.berlin.recyvlerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by berlin on 2016/7/6 0006.
 */
public class MyViewActivity2 extends AppCompatActivity {

    private MyAnimView animView;
    private int height = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        animView = new MyAnimView(this);
//        animView.setMaxHeight(500);
//        animView.setColor(Color.GREEN,Color.RED);
        animView.setLayoutParams(new ViewGroup.LayoutParams(getResources().getDisplayMetrics().widthPixels, 0));
//        setContentView(animView);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (height > getResources().getDisplayMetrics().heightPixels) {
                    height = 0;
                }
                setHeight(height);
                height += 100;
                sendEmptyMessageDelayed(1,1000);
            }
        };
        handler.sendEmptyMessageDelayed(1,1000);
        AnimView animView2 = new AnimView(this);
        animView2.setColor(Color.GREEN,Color.RED);
        linearLayout.addView(animView);
//        linearLayout.addView(animView2);
        setContentView(linearLayout);
    }

    public void setHeight(int a) {
        ViewGroup.LayoutParams params = animView.getLayoutParams();
        params.height = a;
        animView.setLayoutParams(params);
    }
}
