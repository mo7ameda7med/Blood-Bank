package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.donation.DonationData;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.homeFragment.DonationDetailsFragment;
import com.example.bloodbank.view.fragment.homeFragment.PostsDetailsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {


    private Activity activity;
    private BaseActivity baseActivity;
    private List<DonationData> donationDataList;

    public DonationAdapter( Activity activity, List<DonationData> donationDataList) {
        this.activity = activity;
        baseActivity = (BaseActivity) activity;
        this.donationDataList = donationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_donation,
                parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return donationDataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.position = position;
        holder.itemDonationTvBloodType.setText(donationDataList.get(position).getBloodType().getName());
        holder.itemDonationTvName.setText(donationDataList.get(position).getPatientName());
        holder.itemDonationTvHospital.setText(donationDataList.get(position).getHospitalName());
        holder.itemDonationTvGovernorates.setText(donationDataList.get(position).getCity().getGovernorate().getName());
        holder.itemDonationIBtnContact.setImageResource(R.drawable.ic_phone_black_24dp);
        holder.itemDonationIBtnInfo.setImageResource(R.drawable.ic_check_white_24dp);
    }

    private void setAction(ViewHolder holder, int position) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public int position;
        @BindView(R.id.item_donation_I_btn_contact)
        ImageButton itemDonationIBtnContact;
        @BindView(R.id.item_donation_I_btn_info)
        ImageButton itemDonationIBtnInfo;
        @BindView(R.id.item_donation_tv_name)
        TextView itemDonationTvName;
        @BindView(R.id.item_donation_tv_hospital)
        TextView itemDonationTvHospital;
        @BindView(R.id.item_donation_tv_Governorates)
        TextView itemDonationTvGovernorates;
        @BindView(R.id.item_donation_tv_blood_type)
        TextView itemDonationTvBloodType;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DonationDetailsFragment donationDetailsFragment = new DonationDetailsFragment();
                    donationDetailsFragment.donation = donationDataList.get(position);
                    HelperMethod.replaceFragment(baseActivity.getSupportFragmentManager(), R.id.user_cycle_activity, donationDetailsFragment);
                }
            });
        }


        @OnClick({R.id.item_donation_I_btn_contact, R.id.item_donation_I_btn_info})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.item_donation_I_btn_contact:
                    break;
                case R.id.item_donation_I_btn_info:
                    break;
            }
        }

    }
}
