package com.nikdemo.e_bookbookreadingapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.nikdemo.e_bookbookreadingapp.R;
import com.nikdemo.e_bookbookreadingapp.model.LoginModel;
import com.nikdemo.e_bookbookreadingapp.retrofit.APIConst;
import com.nikdemo.e_bookbookreadingapp.retrofit.IPassinglist;
import com.nikdemo.e_bookbookreadingapp.retrofit.RetroFitLogic;
import com.nikdemo.e_bookbookreadingapp.utils.BaseActivity;
import com.nikdemo.e_bookbookreadingapp.utils.Constants;
import com.nikdemo.e_bookbookreadingapp.utils.GKProgrssDialog;
import com.nikdemo.e_bookbookreadingapp.utils.TinyDB;
import com.nikdemo.e_bookbookreadingapp.utils.Utilities;
import com.nikdemo.e_bookbookreadingapp.utils.customui.CustomEditText;

import java.util.HashMap;
import java.util.Map;

import static com.nikdemo.e_bookbookreadingapp.retrofit.APIConst.LOGIN_API_REQ;

public class LoginActivity extends BaseActivity  implements IPassinglist {
    private static final String TAG = "LoginActivity";
    private ProgressDialog progressDialog;
    private TinyDB tinyDB;
    private String deviceId = "";
    CustomEditText edt_email;
    CustomEditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edtPassword);

        progressDialog = new GKProgrssDialog(this, R.style.MyTheme);
        progressDialog.setTitle("Please wait..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        tinyDB = new TinyDB(this);



        deviceId = Settings.Secure.getString(
                getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);


        findViewById(R.id.ll_create).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        });


        findViewById(R.id.card_login).setOnClickListener(v -> {
            if (progressDialog != null) {
                progressDialog.show();
            }
            Map<String, Object> map = new HashMap<>();
            map.put("user_email", edt_email.getText().toString().trim());
            map.put("user_password", edtPassword.getText().toString().trim());
//            map.put("device_token", tinyDB.getString("fcmToken"));
//            map.put("device_id", deviceId);
            map.put("device_type", "android");
            RetroFitLogic retroFitLogic = new RetroFitLogic(LoginActivity.this, null);
            retroFitLogic.loadResult(APIConst.LOGIN_API_NAME, map, LOGIN_API_REQ);
        });

    }

    @Override
    public void passingList(String response, int number) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (response != null && number == LOGIN_API_REQ) {
            Log.e(TAG, "passingList: " + response);
            Gson gson = new Gson();
            LoginModel loginMainModel = gson.fromJson(response, LoginModel.class);
            if(!loginMainModel.getError()){
                tinyDB.putString(Constants.USER_ID, loginMainModel.getData().getUserId());
                tinyDB.putString(Constants.USER_NAME, loginMainModel.getData().getUserName());
                tinyDB.putString(Constants.USER_TYPE, loginMainModel.getData().getUserType());
                tinyDB.putString(Constants.USER_MOBILE, loginMainModel.getData().getUserMobile());
                tinyDB.putString(Constants.USER_EMAIL, loginMainModel.getData().getUserEmail());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Utilities.toastShow(this, loginMainModel.getMessage());
            }
        }
    }
}
