package com.truongpq.englishcommunication.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.truongpq.englishcommunication.fragments.OfflineFragment;
import com.truongpq.englishcommunication.fragments.OnlineFragment;

/**
 * Created by TruongPQ on 5/6/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_COUNT = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OnlineFragment.newInstance();
            case 1:
                return OfflineFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ONLINE";
            case 1:
                return "OFFLINE";
        }
        return null;
    }
}
