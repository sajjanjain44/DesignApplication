package com.example.sajjan.designapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class TabLayout extends AppCompatActivity {

    Toolbar toolbar;
    android.support.design.widget.TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        toolbar = findViewById(R.id.toolbarCustom);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpagertab_id);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new OneFragment(),"Item one");
        viewPagerAdapter.addFragment(new TwoFragment(),"Item two");
        viewPagerAdapter.addFragment(new ThreeFragment(),"Item three");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = findViewById(R.id.tablayout_id);
        tabLayout.setupWithViewPager(viewPager);
    }
}
