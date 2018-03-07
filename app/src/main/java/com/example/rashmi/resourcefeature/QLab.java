package com.example.rashmi.resourcefeature;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.Window;


public class QLab extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] heading = {"Android App Development", "ioS App Development", "Big Data Engineer", "Big Data Admin",
            "Blockchain","Data Scientist"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_qlab, container, false);
        final Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(heading[0]);

        tabLayout = v.findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_android_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_phone_iphone_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dns_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_timeline_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_monetization_on_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_fingerprint_black_24dp));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        FragmentManager fm = getChildFragmentManager();

        viewPager = v.findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(fm, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                toolbar.setTitle(heading[0]);
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                toolbar.setTitle(heading[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}
