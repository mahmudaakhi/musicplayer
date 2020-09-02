package com.r.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    PagerAdapter mPagerAdapter;
    TabLayout mTabLayout;
    TabItem audio;
    TabItem video;
    ViewPager mViewPager;
    private File storage;
    private String[] storagePaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
         setSupportActionBar(mToolbar);
         getSupportActionBar().setTitle(getString(R.string.app_name));

        mTabLayout = findViewById(R.id.tabLayout);
        audio = findViewById(R.id.musicTabItem);
        video = findViewById(R.id.albumTabItem);
        mViewPager = findViewById(R.id.pager);


        mPagerAdapter = new com.r.myapplication.PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter); // set adapter for pager

        // do something when the tab is selected
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition()); // set current tab position

                if (tab.getPosition() == 1) {
                    Toast.makeText(MainActivity.this, "Audio Tab Selected.", Toast.LENGTH_SHORT).show();
                } else if (tab.getPosition() == 2) {
                    Toast.makeText(MainActivity.this, "Video Tab Selected.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


        }
    }




