package com.nikdemo.e_bookbookreadingapp.activity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.CommentModel;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomTextView;

import java.util.ArrayList;

class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<CommentModel> list;
    public CommentAdapter(Context context, ArrayList<CommentModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_comment_layout, viewGroup, false);
        return new CommentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
//        CommentModel model = list.get(position);

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView row_book_title, row_book_description;
        ImageView row_book;

        MyViewHolder(View itemView) {
            super(itemView);
            row_book_title = itemView.findViewById(R.id.row_book_title);
            row_book_description = itemView.findViewById(R.id.row_book_description);
            row_book = itemView.findViewById(R.id.row_book);
        }
    }
}
