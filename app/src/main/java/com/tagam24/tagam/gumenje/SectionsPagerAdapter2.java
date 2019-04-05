package com.tagam24.tagam.gumenje;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tagam24.tagam.dil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AkshayeJH on 11/06/17.
 */

public class SectionsPagerAdapter2 extends FragmentPagerAdapter {
    dil dd;

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    public SectionsPagerAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);


    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){

        switch (position) {
            case 0:
                return dd.nahar_tayarlansy;
            case 1:
                return dd.futbal;

            default:
                return null;
        }

    }
    public void AddFragment(Fragment fragment) {
        fragments.add(fragment);
        dd=new dil();
        dd.set_text();
    }

}