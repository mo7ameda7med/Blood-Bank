package com.example.bloodbank.view.fragment.splashFragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.example.bloodbank.R;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.splashFragment.SliderFragment;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment {

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_splash, container, false);

        initFragment();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SliderFragment sliderFragment=new SliderFragment();
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),R.id.frame,sliderFragment);
            }
        },3000);
        return view;
    }


    @Override
    public void onBack() {
        Objects.requireNonNull(getActivity()).finish();
    }
}
