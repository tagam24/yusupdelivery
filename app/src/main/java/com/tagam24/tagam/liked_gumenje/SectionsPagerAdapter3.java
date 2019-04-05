package com.tagam24.tagam.liked_gumenje;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tagam24.tagam.dil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AkshayeJH on 11/06/17.
 */

public class SectionsPagerAdapter3 extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    public SectionsPagerAdapter3(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public CharSequence getPageTitle(int position){

        switch (position) {
            case 0:
                return dil.futbal;
            case 1:
                return dil.resepti;


            default:
                return null;
        }

    }
    public void AddFragment(Fragment fragment) {
        fragments.add(fragment);
    }

}