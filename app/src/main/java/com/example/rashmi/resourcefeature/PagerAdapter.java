package com.example.rashmi.resourcefeature;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by RASHMI on 28-02-2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle b;

        switch (position) {
            case 0:
                b = new Bundle();
                b.putInt("key",0);
                Fragment tab1 = new Sections();
                tab1.setArguments(b);
                return tab1;
            case 1:
                b = new Bundle();
                b.putInt("key",1);
                Fragment tab2 = new Sections();
                tab2.setArguments(b);
                return tab2;
            case 2:
                b = new Bundle();
                b.putInt("key",2);
                Fragment tab3 = new Sections();
                tab3.setArguments(b);
                return tab3;
            case 3:
                b = new Bundle();
                b.putInt("key",3);
                Fragment tab4 = new Sections();
                tab4.setArguments(b);
                return tab4;
            case 4:
                b = new Bundle();
                b.putInt("key",4);
                Fragment tab5 = new Sections();
                tab5.setArguments(b);
                return tab5;
            case 5:
                b = new Bundle();
                b.putInt("key",5);
                Fragment tab6 = new Sections();
                tab6.setArguments(b);
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}

