package com.example.bloodbank.adapter;

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
    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflater;
    public int selectedId;

    public SpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GeneralResponseData> generalResponseDataList, String hint) {
        this.generalResponseDataList = generalResponseDataList;
        generalResponseDataList.add( new GeneralResponseData(0,hint));
    }

    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.items_custom_spinner, null);

        TextView names = (TextView) view.findViewById(R.id.item_custom_spinner_Tv);
        names.setText(generalResponseDataList.get(i).getName());

        selectedId = generalResponseDataList.get(i).getId();

        return view;
    }
}