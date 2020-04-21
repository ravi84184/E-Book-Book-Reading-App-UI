package com.nikdemo.e_bookbookreadingapp.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.nikdemo.e_bookbookreadingapp.retrofit.APIConst;
import com.nikdemo.e_bookbookreadingapp.retrofit.IPassinglist;
import com.nikdemo.e_bookbookreadingapp.retrofit.RetroFitLogic;
import com.nikdemo.e_bookbookreadingapp.utils.Constants;
import com.nikdemo.e_bookbookreadingapp.utils.GKProgrssDialog;
import com.nikdemo.e_bookbookreadingapp.utils.TinyDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.nikdemo.e_bookbookreadingapp.retrofit.APIConst.LOGIN_API_REQ;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IPassinglist {
    private static final String TAG = "HomeFragment";
    ViewPager vp_slider;
    LinearLayout rl_point;
    ArrayList<String> bannerList = new ArrayList<>();
    ArrayList<BookModel> bookList = new ArrayList<>();
    int currentPosition = 0;
    private View view;
    private RecyclerView rl_book;

    private ProgressDialog progressDialog;
    private TinyDB tinyDB;

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
    void getCategoryData(){
        if (progressDialog != null) {
            progressDialog.show();
        }
        Map<String, Object> map = new HashMap<>();
//        map.put("user_email", edt_email.getText().toString().trim());
//        map.put("user_password", edtPassword.getText().toString().trim());
//            map.put("device_token", tinyDB.getString("fcmToken"));
//            map.put("device_id", deviceId);
        map.put("device_type", "android");
        RetroFitLogic retroFitLogic = new RetroFitLogic(getContext(), this);
        retroFitLogic.loadResult(APIConst.LOGIN_API_NAME, map, LOGIN_API_REQ);
    }

    private void initUI() {
        progressDialog = new GKProgrssDialog(getContext(), R.style.MyTheme);
        progressDialog.setTitle("Please wait..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        tinyDB = new TinyDB(getContext());

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

    @Override
    public void passingList(String response, int number) {

    }
}
