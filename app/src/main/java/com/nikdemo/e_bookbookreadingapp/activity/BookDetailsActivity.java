package com.nikdemo.e_bookbookreadingapp.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.CommentModel;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;
import com.nikdemo.e_bookbookreadingapp.utils.Utilities;

import java.util.ArrayList;

public class BookDetailsActivity extends BaseActivity {
    private static final String TAG = "BookDetailsActivity";

    private Toolbar toolbar;
    private RecyclerView rl_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        intiUI();

    }

    private void intiUI() {
        toolbar = findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Utilities.setTitle(this, "Book Details", null);

        rl_comment = findViewById(R.id.rl_comment);
        rl_comment.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        rl_comment.setAdapter(new CommentAdapter(getApplicationContext(),new ArrayList<CommentModel>()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
