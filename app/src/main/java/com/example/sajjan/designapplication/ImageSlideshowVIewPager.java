package com.example.sajjan.designapplication;



import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;


public class ImageSlideshowVIewPager extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    SlideShowAdapter slideShowAdapter;
//    CircleIndicator circleIndicator;

    Handler handler;
    Runnable runnable;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slideshow_view_pager);
//        toolbar = findViewById(R.id.toolbarCustom);
//        circleIndicator = findViewById(R.id.circle_indececator_id);
//        toolbar.setTitle("View Pager");
//        setSupportActionBar(toolbar);
//
        viewPager = findViewById(R.id.view_pager_id);
        slideShowAdapter = new SlideShowAdapter(this);
        viewPager.setAdapter(slideShowAdapter);

//        circleIndicator.setViewPager(viewPager);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
               int i =  viewPager.getCurrentItem();


               if(i == slideShowAdapter.images.length-1){
                   i = 0;
               }else {
                   i++;
               }



               viewPager.setCurrentItem(i);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },1000,1000);

    }
}
