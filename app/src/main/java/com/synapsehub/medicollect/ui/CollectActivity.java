package com.synapsehub.medicollect.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.synapsehub.medicollect.Adapter.mViewPagerAdapter;
import com.synapsehub.medicollect.R;
import com.synapsehub.medicollect.fragment.DataFragment;
import com.synapsehub.medicollect.fragment.PatientFragment;


/**
 * Created by Michelo on 19/11/2016.
 */

public class CollectActivity extends AppCompatActivity {

    ViewPager viewPager;
    private TabLayout tabLayout;
    mViewPagerAdapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        PatientFragment pFrag = new PatientFragment();
        DataFragment dFrag = new DataFragment();

        mViewPagerAdapter adapter = new mViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(pFrag, "Patient");
        adapter.addFrag(dFrag, "Data");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this,SettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
