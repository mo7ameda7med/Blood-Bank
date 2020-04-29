package com.example.bloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodbank.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;


    public void superOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
