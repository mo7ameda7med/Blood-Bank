package com.example.bloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.bloodbank.R;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater mLayoutInflater;

    public List<Integer> images = new ArrayList<>();
    public  List<String> descriptions=new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addPage(Integer image ,String description) {
        images.add(image);
        descriptions.add(description);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            View itemView = mLayoutInflater.inflate(R.layout.item_slide, container, false);

            ImageView sliderAdapterIvSliderImage = itemView.findViewById(R.id.item_slide_iv_image);
            TextView sliderAdapterTvSliderText = itemView.findViewById(R.id.item_slide_tv_text);

            sliderAdapterIvSliderImage.setImageResource(images.get(position));
            sliderAdapterTvSliderText.setText(descriptions.get(position));

            container.addView(itemView);

            return itemView;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
