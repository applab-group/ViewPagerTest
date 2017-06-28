package com.aal.sekihan.viewpagertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sekihan on 2017/06/27.
 */

public  class ExampleFragmentPagerAdapter extends FragmentPagerAdapter {
    public ExampleFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TimeTableFragment.newInstance(android.R.color.white);
            case 1:
                return SyllabusFragment.newInstance(android.R.color.white);
            case 2:
                return UnitFragment.newInstance(android.R.color.white);
        }
        return null;

    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "My時間割";
            case 1:
                return "シラバス検索";
            case 2:
                return "単位取得状況";
        }
        return null;
    }
}
