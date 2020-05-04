package com.example.bloodbank.view.fragment.notificationFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.view.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment {

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
    public void onBack()
    {
        baseActivity.superOnBackPressed();
    }

}
