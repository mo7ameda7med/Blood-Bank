package com.example.bloodbank.view.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.bloodbank.R;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.homeFragment.HomeFragment;
import com.example.bloodbank.view.fragment.moreFragment.MoreFragment;
import com.example.bloodbank.view.fragment.notificationFragment.NotificationFragment;
import com.example.bloodbank.view.fragment.profileFragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                HelperMethod.replaceFragment(getSupportFragmentManager(),R.id.frameLayout,new HomeFragment());
                break;
            case R.id.nav_profile:
                HelperMethod.replaceFragment(getSupportFragmentManager(),R.id.frameLayout,new ProfileFragment());
                break;
            case R.id.nav_notification:
                HelperMethod.replaceFragment(getSupportFragmentManager(),R.id.frameLayout,new NotificationFragment());
            break;
            case R.id.nav_more:
                HelperMethod.replaceFragment(getSupportFragmentManager(),R.id.frameLayout,new MoreFragment());
                break;
        }

        return true;
    }

}
