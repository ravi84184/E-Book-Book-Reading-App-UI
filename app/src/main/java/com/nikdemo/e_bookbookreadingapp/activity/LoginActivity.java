package com.nikdemo.e_bookbookreadingapp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findViewById(R.id.ll_create).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        });


        findViewById(R.id.card_login).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

    }
}
