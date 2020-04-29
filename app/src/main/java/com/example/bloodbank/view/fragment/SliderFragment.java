package com.example.bloodbank.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SliderAdapter;
import com.example.bloodbank.view.activity.UserCycleActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SliderFragment extends BaseFragment {


    @BindView(R.id.fragment_slider_i_btn_slider_btn)
    ImageButton fragmentSliderIBtnSliderBtn;
    @BindView(R.id.fragment_slider_vp_slider)
    ViewPager fragmentSliderVpSlider;
    @BindView(R.id.fragment_slider_tl_slider_tabs)
    TabLayout fragmentSliderTlSliderTabs;
    private SliderAdapter sliderAdapter;
    private int position = 0;

    public SliderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ButterKnife.bind(this,view);
        initFragment();
        setupSlider(view);
        return view;
    }

    private void setupSlider(View view)
    {
        sliderAdapter = new SliderAdapter(Objects.requireNonNull(getActivity()));
        sliderAdapter.addPage(R.drawable.ic_imageslider, "اول فراجمنت يمكنك وضع التوجيهات التي تريدها او معلةمات عن كيفية استخدام التطبيق");
        sliderAdapter.addPage(R.drawable.ic_imageslider2, "ثانى فراجمنت يمكنك وضع التوجيهات التي تريدها او معلةمات عن كيفية استخدام التطبيق");
        sliderAdapter.addPage(R.drawable.ic_imageslider, "ثالث فراجمنت يمكنك وضع التوجيهات التي تريدها او معلةمات عن كيفية استخدام التطبيق");
        fragmentSliderVpSlider.setAdapter(sliderAdapter);
        fragmentSliderTlSliderTabs.setupWithViewPager(fragmentSliderVpSlider);

    }

    @OnClick(R.id.fragment_slider_i_btn_slider_btn)
    public void onClick() {

        fragmentSliderIBtnSliderBtn.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
        if (fragmentSliderVpSlider.getCurrentItem() == sliderAdapter.getCount() - 1) {
            startActivity(new Intent(getActivity(), UserCycleActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        } else {
            position = fragmentSliderVpSlider.getCurrentItem();
            changePosition(++position);
        }


        if(fragmentSliderVpSlider.getCurrentItem()==sliderAdapter.getCount()-1) {
        fragmentSliderIBtnSliderBtn.setImageResource(R.drawable.ic_check_white_24dp);
        } else {
        fragmentSliderIBtnSliderBtn.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
        }
}

    private void changePosition(int position) {

        if (position < 0) {
            position = sliderAdapter.getCount() - 1;
        }
        fragmentSliderVpSlider.setCurrentItem(position, true);
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }


}
