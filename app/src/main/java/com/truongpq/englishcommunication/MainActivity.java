package com.truongpq.englishcommunication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.firebase.client.Firebase;
import com.truongpq.englishcommunication.adapters.ViewPagerAdapter;
import com.truongpq.englishcommunication.fragments.OnlineFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(0);
            return;
        }

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_video);
        if (videoView.isPlaying() || frameLayout.getVisibility() == View.VISIBLE) {
            videoView.stopPlayback();
            frameLayout.setVisibility(View.GONE);
            return;
        }
        super.onBackPressed();
    }
}
