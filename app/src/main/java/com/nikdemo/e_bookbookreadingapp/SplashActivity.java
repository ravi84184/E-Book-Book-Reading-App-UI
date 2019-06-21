package com.nikdemo.e_bookbookreadingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nikdemo.e_bookbookreadingapp.activity.LoginActivity;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }, 300);
    }
}
