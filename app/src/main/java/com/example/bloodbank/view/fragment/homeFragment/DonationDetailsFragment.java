package com.example.bloodbank.view.fragment.homeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationDetailsFragment extends Fragment {


    public DonationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation_details, container, false);
    }
}
