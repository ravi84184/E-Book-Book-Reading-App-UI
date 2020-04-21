package com.nikdemo.e_bookbookreadingapp.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.activity.BookDetailsActivity;
import com.nikdemo.e_bookbookreadingapp.model.BookModel;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomTextView;
import com.varunest.sparkbutton.SparkButton;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    Context context;
    private List<BookModel> addressList;

    public BookAdapter(Context context, List<BookModel> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_main_book_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookModel model = addressList.get(position);

        holder.row_book.setImageDrawable(ContextCompat.getDrawable(context, model.getBook_cover()));

        holder.row_book_title.setText(model.getBook_title());

        holder.row_rating.setRating((float) model.getBook_rating());

        holder.row_book_like.setChecked(model.isLike());

        holder.root_ly.setOnClickListener(v -> {
            context.startActivity(new Intent(context, BookDetailsActivity.class));
        });
    }


    @Override
    public int getItemCount() {
        return addressList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CustomTextView row_book_title, row_book_description;
        ImageView row_book;
        RatingBar row_rating;
        SparkButton row_book_like;
        CardView root_ly;

        MyViewHolder(View itemView) {
            super(itemView);
            row_book_title = itemView.findViewById(R.id.row_book_title);
            row_book_description = itemView.findViewById(R.id.row_book_description);
            row_book = itemView.findViewById(R.id.row_book);
            row_rating = itemView.findViewById(R.id.row_rating);
            row_book_like = itemView.findViewById(R.id.row_book_like);
            root_ly = itemView.findViewById(R.id.root_ly);
        }
    }
}
