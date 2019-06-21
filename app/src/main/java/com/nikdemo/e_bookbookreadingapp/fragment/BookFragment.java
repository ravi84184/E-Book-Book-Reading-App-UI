package com.nikdemo.e_bookbookreadingapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.adapter.BookAdapter;
import com.nikdemo.e_bookbookreadingapp.adapter.CarShortAdapter;
import com.nikdemo.e_bookbookreadingapp.utils.Constants;
import com.nikdemo.e_bookbookreadingapp.utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {
    private static final String TAG = "BookFragment";

    private View view;

    private RecyclerView rl_bool_list;
    private RecyclerView rl_cat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_book, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        Utilities.setTitle(getContext(), "Books", BookFragment.this);

        rl_bool_list = view.findViewById(R.id.rl_bool_list);
        rl_cat = view.findViewById(R.id.rl_cat);


        rl_bool_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        rl_bool_list.setAdapter(new BookAdapter(getContext(), Constants.getBookData()));

        rl_cat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false));
        rl_cat.setAdapter(new CarShortAdapter(getContext(), Constants.getCat()));


    }

}
