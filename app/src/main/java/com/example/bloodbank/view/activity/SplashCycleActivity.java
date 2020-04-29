package com.example.bloodbank.view.activity;


import android.os.Bundle;




import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.SplashFragment;

import static com.example.bloodbank.util.HelperMethod.replaceFragment;

public class SplashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_cycle);


        SplashFragment splashFragment=new SplashFragment();
        replaceFragment(getSupportFragmentManager(),R.id.frame, splashFragment);





    }
}
