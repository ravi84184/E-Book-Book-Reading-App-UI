package com.nikdemo.e_bookbookreadingapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.PlanModel;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomTextView;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    Context context;
    private List<PlanModel> addressList;

    public PlanAdapter(Context context, List<PlanModel> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_plan_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlanModel model = addressList.get(position);

        holder.row_plan_time.setText(String.valueOf(model.getPlan_time()));
        holder.row_plan.setText(model.getPlan());
        holder.row_price.setText(model.getPlan_price());

        holder.text_description.setText("Enjoy "+String.valueOf(model.getPlan_time())+" "+model.getPlan()+" all books reading");

    }


    @Override
    public int getItemCount() {
        return addressList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView row_plan, row_plan_time,row_price,text_description;

        MyViewHolder(View itemView) {
            super(itemView);
            row_plan = itemView.findViewById(R.id.row_plan);
            row_plan_time = itemView.findViewById(R.id.row_plan_time);
            row_price = itemView.findViewById(R.id.row_price);
            text_description = itemView.findViewById(R.id.text_description);
        }
    }
}
