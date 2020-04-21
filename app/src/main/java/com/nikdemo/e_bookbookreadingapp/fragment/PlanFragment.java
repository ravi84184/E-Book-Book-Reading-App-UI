package com.nikdemo.e_bookbookreadingapp.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.adapter.PlanAdapter;
import com.nikdemo.e_bookbookreadingapp.model.PlanModel;
import com.nikdemo.e_bookbookreadingapp.utils.Constants;
import com.nikdemo.e_bookbookreadingapp.utils.Utilities;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {
    private static final String TAG = "PlanFragment";

    private View view;
    private RecyclerView rl_plane;

    private ArrayList<PlanModel> planList = new ArrayList<PlanModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        planList = Constants.getPlan();
        initUI();
        return view;
    }

    private void initUI() {
        Utilities.setTitle(getContext(),"Choose Plan",PlanFragment.this);

        rl_plane = view.findViewById(R.id.rl_plane);
        rl_plane.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));

        rl_plane.setAdapter(new PlanAdapter(getContext(),planList));

    }

}
