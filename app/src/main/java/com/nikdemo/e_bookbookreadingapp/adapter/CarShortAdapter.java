package com.nikdemo.e_bookbookreadingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomTextView;

import java.util.List;

public class CarShortAdapter extends RecyclerView.Adapter<CarShortAdapter.MyViewHolder> {
    private static final String TAG = "CarShortAdapter";
    Context context;
    private List<String> addressList;
    int selecteIndex = 0;
    private setOnCategoryListener listener;


    public interface setOnCategoryListener{
        void onCategoryClick(String model,int index);
    }

    public CarShortAdapter(Context context, List<String> addressList) {
        this.context = context;
        this.addressList = addressList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_category_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String model = addressList.get(position);
        Log.e(TAG, "onBindViewHolder: "+model );
        holder.row_cat.setText(model);

        if (position == selecteIndex){
            holder.row_cat.setBackgroundColor(ContextCompat.getColor(context,R.color.yellow));
            holder.row_cat.setTextColor(ContextCompat.getColor(context,android.R.color.white));
        } else {
            holder.row_cat.setBackgroundColor(ContextCompat.getColor(context,R.color.light_gry));
            holder.row_cat.setTextColor(ContextCompat.getColor(context,R.color.yellow));
        }
        holder.row_cat.setOnClickListener(v->{
            selecteIndex = position;
            notifyDataSetChanged();
//            listener.onCategoryClick(model,position);
        });
    }


    @Override
    public int getItemCount() {
        return addressList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView row_cat;

        MyViewHolder(View itemView) {
            super(itemView);
            row_cat = itemView.findViewById(R.id.row_cat);

        }
    }
}
