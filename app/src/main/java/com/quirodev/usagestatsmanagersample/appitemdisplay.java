


package com.quirodev.usagestatsmanagersample;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.quirodev.data.dbcontract;
import com.quirodev.data.dbhelper;
import com.quirodev.data.dbprovider;

import java.util.ArrayList;
import java.util.List;


public class appitemdisplay extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String abc="appname";
    public static String appname1;
   // public static String pkname1;
    public static String usagetime1;
    public String pkname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //obj=new dbprovider(getApplicationContext());
        Intent intent=getIntent();
        appname1=intent.getStringExtra(abc);
        Log.v("appname",appname1);

        setContentView(R.layout.activity_main2);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


    }
    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Intent intent=getIntent();
                    if (intent!=null){
                       // pkname=intent.getStringExtra(pkname1);
                        String usage = intent.getStringExtra(usagetime1);
                        Bundle bundle = new Bundle();
                        bundle.putString("usage", usage );
                        bundle.putString("appname1", appname1 );
                       // bundle.putString("pkname", pkname );
                        Bundle extras=intent.getExtras();
                        bundle.putBundle("extras",extras);
                        OrangeFragment F1 = new OrangeFragment();
                        F1.setArguments(bundle);
                        return F1;
                    }
                case 1:
                    return new AppleFragment();
                case 2:
                    return new GrapesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Graphs";
                case 2:
                    return "Alarm";
                default:
                    return null;
            }
        }
    }



}
