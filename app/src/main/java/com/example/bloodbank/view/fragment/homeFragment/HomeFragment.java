package com.example.bloodbank.view.fragment.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.library.StatusBarUtil;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    ViewPagerWithFragmentAdapter viewPagerWithFragmentAdapter;
    @BindView(R.id.fragment_home_TL_slider_tabs)
    TabLayout fragmentHomeTLSliderTabs;
    @BindView(R.id.fragment_home_Vp_Slider)
    ViewPager fragmentHomeVpSlider;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setupSlider();
        return view;
    }

    private void setupSlider() {
        viewPagerWithFragmentAdapter = new ViewPagerWithFragmentAdapter(getFragmentManager());
        viewPagerWithFragmentAdapter.addPager(new DonationFragment(), "طلبات التبرع");
        viewPagerWithFragmentAdapter.addPager(new PostFragment(), "المقالات");
        fragmentHomeVpSlider.setAdapter(viewPagerWithFragmentAdapter);
        fragmentHomeTLSliderTabs.setupWithViewPager(fragmentHomeVpSlider);

    }

    public void onBack() {
        (getActivity()).finish();
    }

}
