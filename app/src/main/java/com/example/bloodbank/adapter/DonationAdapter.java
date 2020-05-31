package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.donation.DonationData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<DonationData> donationDataList=new ArrayList<>();

    public DonationAdapter(Context context, Activity activity, List<DonationData> donationDataList) {
        this.context = context;
        this.activity = activity;
        this.donationDataList = donationDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donation,
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
        }
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
