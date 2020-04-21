package com.nikdemo.e_bookbookreadingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;

public class RegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        findViewById(R.id.ll_login).setOnClickListener(v->{
            finish();
        });
    }
}
