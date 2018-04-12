package com.white.headfootrecyclerview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MainViewPagerAdapter adapter;


    private List<Fragment> fragmentList;
    private String[] title = new String[99];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        init();
    }

    private void init() {

        fragmentList = new ArrayList<>();
        fragmentList.clear();
        fragmentList.add(TestFragment.newInstance());

        if (null == adapter) {
            adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList, title);
        }
        viewPager.setAdapter(adapter);

        title[0] = "TestFragment";
        tabLayout.addTab(tabLayout.newTab().setText(title[0]), true);

        tabLayout.setupWithViewPager(viewPager);
    }


}
