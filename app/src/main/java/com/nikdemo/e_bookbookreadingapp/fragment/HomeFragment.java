package com.nikdemo.e_bookbookreadingapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.adapter.BookAdapter;
import com.nikdemo.e_bookbookreadingapp.adapter.SliderAdapter;
import com.nikdemo.e_bookbookreadingapp.model.BookModel;
import com.nikdemo.e_bookbookreadingapp.utils.Constants;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    ViewPager vp_slider;
    LinearLayout rl_point;
    ArrayList<String> bannerList = new ArrayList<>();
    ArrayList<BookModel> bookList = new ArrayList<>();
    int currentPosition = 0;
    private View view;
    private RecyclerView rl_book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        bannerList.add("Biographies");
        bannerList.add("Economics");
        bannerList.add("Historical");
        bannerList.add("Europe");
        bannerList.add("Business");
        bannerList.add("Computers & Tech");
        bannerList.add("Cooking");

        bookList = Constants.getBookData();
        initUI();
        setupPoint();

        return view;
    }

    private void initUI() {

        rl_book = view.findViewById(R.id.rl_book);
        rl_point = view.findViewById(R.id.rl_point);
        vp_slider = view.findViewById(R.id.vp_slider);


        rl_book.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        rl_book.setAdapter(new BookAdapter(getContext(), bookList));
        vp_slider.setAdapter(new SliderAdapter(getContext(), bannerList));

        vp_slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.e(TAG, "onPageScrolled: " + i1);
            }

            @Override
            public void onPageSelected(int i) {
                currentPosition = i;
                setupPoint();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setupPoint() {
        rl_point.removeAllViews();
        for (int i = 0; i < bannerList.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(25, 25);
            layoutParams.setMargins(5, 5, 5, 5);
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(layoutParams);
            imageView.requestLayout();
            if (currentPosition == i) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.deactive_dot));
            }
            rl_point.addView(imageView);
        }
    }

}
