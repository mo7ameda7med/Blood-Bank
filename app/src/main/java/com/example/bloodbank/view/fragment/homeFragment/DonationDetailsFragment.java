package com.example.bloodbank.view.fragment.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.donation.DonationData;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.google.android.gms.maps.GoogleMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationDetailsFragment extends BaseFragment{

    @BindView(R.id.donation_details_fragment_toolbar_iv)
    ImageView donationDetailsFragmentToolbarIv;
    @BindView(R.id.donation_details_fragment_tv_name)
    TextView donationDetailsFragmentTvName;
    @BindView(R.id.donation_details_fragment_tv_age)
    TextView donationDetailsFragmentTvAge;
    @BindView(R.id.donation_details_fragment_tv_blood_type)
    TextView donationDetailsFragmentTvBloodType;
    @BindView(R.id.donation_details_fragment_tv_number)
    TextView donationDetailsFragmentTvNumber;
    @BindView(R.id.donation_details_fragment_tv_hospital)
    TextView donationDetailsFragmentTvHospital;
    @BindView(R.id.donation_details_fragment_tv_address)
    TextView donationDetailsFragmentTvAddress;
    @BindView(R.id.donation_details_fragment_tv_phone)
    TextView donationDetailsFragmentTvPhone;
    @BindView(R.id.donation_details_fragment_tv_details)
    TextView donationDetailsFragmentTvDetails;
    @BindView(R.id.donation_details_fragment_toolbar_tv)
    TextView donationDetailsFragmentToolbarTv;
    public DonationData donation;
    private GoogleMap mMap;


    public DonationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_details, container, false);
        initFragment();
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        donationDetailsFragmentToolbarTv.setText(donation.getPatientName());
        donationDetailsFragmentTvName.setText(donation.getPatientName());
        donationDetailsFragmentTvAge.setText(donation.getPatientAge());
        donationDetailsFragmentTvBloodType.setText(donation.getBloodType().getName());
        donationDetailsFragmentTvNumber.setText(donation.getBagsNum());
        donationDetailsFragmentTvHospital.setText(donation.getHospitalName());
        donationDetailsFragmentTvAddress.setText(donation.getCity().getName());
        donationDetailsFragmentTvPhone.setText(donation.getPhone());
        donationDetailsFragmentTvDetails.setText(donation.getNotes());
    }



    public void onBack() {
        assert getFragmentManager() != null;
        HelperMethod.replaceFragment(getFragmentManager(), R.id.user_cycle_activity, new HomeFragment());
    }
}
