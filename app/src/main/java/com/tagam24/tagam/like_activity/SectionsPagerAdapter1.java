package com.tagam24.tagam.like_activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tagam24.tagam.dil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AkshayeJH on 11/06/17.
 */

public class SectionsPagerAdapter1 extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    public SectionsPagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    dil dd = new dil();


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {
        dd.set_text();

        switch (position) {
            case 0:
                return dd.cafe;
            case 1:
                return dd.tagamlar;
            case 2:
                return dd.beylekiler;

            default:
                return null;
        }

    }

    public void AddFragment(Fragment fragment) {
        fragments.add(fragment);
    }

}