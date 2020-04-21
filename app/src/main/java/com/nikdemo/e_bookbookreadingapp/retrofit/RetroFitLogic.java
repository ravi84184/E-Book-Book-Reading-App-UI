package com.nikdemo.e_bookbookreadingapp.retrofit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nikdemo.e_bookbookreadingapp.SplashActivity;
import com.nikdemo.e_bookbookreadingapp.utils.Constants;
import com.nikdemo.e_bookbookreadingapp.utils.TinyDB;
import com.nikdemo.e_bookbookreadingapp.utils.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitLogic {

    private String info;
    private String sentence;
    private Context context;
    private IPassinglist iPassinglist;
    private TinyDB tinyDB;

    public RetroFitLogic(Context context, Fragment fragment) {
        this.context = context;
        tinyDB = new TinyDB(context);
        if (fragment == null) {
            this.iPassinglist = (IPassinglist) context;
        } else {
            this.iPassinglist = (IPassinglist) fragment;
        }
    }

    public void loadResult(String url, Map<String, Object> jsonObject, int number) {
        if (Utilities.isNetworkAvaliable(context)) {
            tinyDB = new TinyDB(context);
            String token = tinyDB.getString(Constants.USER_TOKEN);
            final String BASE_URL = APIConst.MAIN_URL;
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            /* creating object of interface and getting response from api */
            IRequestInterface requestInterface = retrofit.create(IRequestInterface.class);
            Call<JsonObject> call = requestInterface.createRequest(token, url, jsonObject);
            callRetrofit(call, number);
        } else {
            Utilities.toastShow(context, "No internet connection");
            iPassinglist.passingList("No internet connection", -1);
        }

    }

    public String uploadImage(String url, File mediaPath, JsonObject jsonObject, int number) {
        final String BASE_URL = "http://www.gallerymosaic.com/demo/api/";
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        /* creating object of interface and getting response from api */
        // Map is used to multipart the file using okhttp3.RequestBody

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), mediaPath);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("photoidfile", mediaPath.getName(), requestBody);

        IRequestInterface requestInterface = retrofit.create(IRequestInterface.class);
        Call<JsonObject> call = requestInterface.uploadFile(url, fileToUpload, jsonObject);
        callRetrofit(call, number);

        return sentence;
    }

    private void callRetrofit(Call<JsonObject> call, final int number) {
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {

                try {
                    info = String.valueOf(response.body());
                    Log.e("RESPONSE", info);
                    Gson gson = new Gson();
                    if (info != null && !info.equals("null")) {
                        JSONObject jsonObject = new JSONObject(info);
                        if (jsonObject.get("message").toString().equalsIgnoreCase("Invalid Token")) {
                            Utilities.toastShow(context, "Something went wrong!");
                            tinyDB.clear();
                            Intent i = new Intent(context, SplashActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(i);
                        } else {
                            iPassinglist.passingList(info, number);
                        }
                    } else {
                        iPassinglist.passingList("Something went wrong!", -1);
                        Utilities.toastShow(context, "Something went wrong!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Log.e("ERROR", t.getMessage());
                iPassinglist.passingList(t.getMessage(), -1);
            }
        });
    }
}
