package com.example.bloodbank.view.activity;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.RegisterFragment;

import static com.example.bloodbank.util.HelperMethod.replaceFragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        RegisterFragment loginFragment=new RegisterFragment();
        replaceFragment(getSupportFragmentManager(),R.id.user_cycle_activity, loginFragment);

    }
}
