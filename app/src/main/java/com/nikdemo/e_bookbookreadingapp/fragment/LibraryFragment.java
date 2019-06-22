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
import com.nikdemo.e_bookbookreadingapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment {
    private static final String TAG = "LibraryFragment";

    private View view;

    private RecyclerView rl_book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_library, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        rl_book = view.findViewById(R.id.rl_book);
        rl_book.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        rl_book.setAdapter(new BookAdapter(getContext(), Constants.getBookData()));
    }

}
