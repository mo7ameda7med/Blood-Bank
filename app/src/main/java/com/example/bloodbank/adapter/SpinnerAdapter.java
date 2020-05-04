package com.example.bloodbank.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.generalResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;


public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    public List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    public int selectItem = 0;

    public SpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GeneralResponseData> generalResponseDataList, String hint) {
        this.generalResponseDataList = new ArrayList<>();
        this.generalResponseDataList.add(new GeneralResponseData(0, hint));
        this.generalResponseDataList.addAll(generalResponseDataList);
    }

    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View ConvertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.items_custom_spinner, null);
        TextView names = view.findViewById(R.id.item_custom_spinner_Tv);

        names.setText(generalResponseDataList.get(position).getName());
        selectItem = generalResponseDataList.get(position).getId();
        return view;
    }
}

