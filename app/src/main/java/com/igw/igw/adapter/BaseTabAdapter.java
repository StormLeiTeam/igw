package com.igw.igw.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class BaseTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    private List<String> str;

    public BaseTabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> str) {
        super(fm);
        this.fragments = fragments;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
