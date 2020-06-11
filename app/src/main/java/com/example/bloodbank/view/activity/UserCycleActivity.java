package com.example.bloodbank.view.activity;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.homeFragment.DonationDetailsFragment;
import com.example.bloodbank.view.fragment.homeFragment.HomeFragment;
import com.example.bloodbank.view.fragment.loginFragment.LoginFragment;

import static com.example.bloodbank.util.HelperMethod.replaceFragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);


        DonationDetailsFragment loginFragment=new DonationDetailsFragment();
        replaceFragment(getSupportFragmentManager(),R.id.user_cycle_activity, loginFragment);
    }
}
