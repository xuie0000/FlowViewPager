package com.xuie.flowviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout root;
    private ViewPager viewPager;

    private int[] imageIds = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R.mipmap.p5, R.mipmap.p6};
    private Random random = new Random();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = (RelativeLayout) findViewById(R.id.root);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        FlowFragmentPagerAdapter flowFragmentPagerAdapter = new FlowFragmentPagerAdapter(getSupportFragmentManager());
        flowFragmentPagerAdapter.addFragment(MainFragment.newInstance(imageIds[random.nextInt(5)]));
        flowFragmentPagerAdapter.addFragment(MainFragment.newInstance(imageIds[random.nextInt(5)]));
        flowFragmentPagerAdapter.addFragment(MainFragment.newInstance(imageIds[random.nextInt(5)]));
        flowFragmentPagerAdapter.addFragment(MainFragment.newInstance(imageIds[random.nextInt(5)]));
        flowFragmentPagerAdapter.addFragment(MainFragment.newInstance(imageIds[random.nextInt(5)]));

        viewPager.setAdapter(flowFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(flowFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(5);

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
    }

}
