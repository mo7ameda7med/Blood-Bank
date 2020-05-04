package com.example.bloodbank.view.fragment.profileFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();


        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    public void onBack()
    {
        baseActivity.superOnBackPressed();
    }

}
