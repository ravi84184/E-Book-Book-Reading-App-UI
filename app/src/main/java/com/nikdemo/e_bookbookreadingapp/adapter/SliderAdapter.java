package com.nikdemo.e_bookbookreadingapp.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomTextView;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {
    private ArrayList<String> bannerList;
    Context mContext;

    public SliderAdapter(Context context, ArrayList<String> bannerList) {
        this.bannerList = bannerList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View itemView = mLayoutInflater.inflate(R.layout.row_slider_layout, container, false);

        CustomTextView row_text = itemView.findViewById(R.id.row_text);

        row_text.setText(bannerList.get(position));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
