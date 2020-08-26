package com.example.viewpagerapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.viewpagerapp.fragmets.CatFragment;
import com.example.viewpagerapp.fragmets.FilmFragment;
import com.example.viewpagerapp.fragmets.NewsFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new FilmFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new CatFragment();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new NewsFragment();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
