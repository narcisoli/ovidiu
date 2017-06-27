package com.example.intern06.lifereminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.intern06.lifereminder.fragmente.notes;
import com.example.intern06.lifereminder.fragmente.profil;
import com.example.intern06.lifereminder.fragmente.recomand;
import com.example.intern06.lifereminder.fragmente.reminderfragment;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;


public class MainActivity extends AppCompatActivity {


    ViewPager.OnPageChangeListener p;
    private ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,MyService.class)) ;
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOnPageChangeListener(p);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

                bottom();



    }

    private void bottom() {
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.alarm), R.color.black

                ).title("Home")
                        .badgeTitle("NTB")
                        .build()
        );


        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.user),
                        R.color.black
                ).title("Profil")
                        .badgeTitle("icon")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(pager, 0);
    navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1)
                getSupportFragmentManager().popBackStack();
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    });

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 2;

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
                case 0:
                    return reminderfragment.newInstance();

                case 1:
                    return profil.newInstance();
                default:
                    return null;
            }


        }
    }
}
