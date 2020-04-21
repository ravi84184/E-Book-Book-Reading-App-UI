package com.nikdemo.e_bookbookreadingapp.retrofit;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface IRequestInterface {

    @POST
    @FormUrlEncoded
    Call<JsonObject> createRequest(@Header("Token") String token, @Url String url, @FieldMap Map<String, Object> request);

    @POST
    @FormUrlEncoded
    Call<JsonObject> createRequest(@Header("Token") String token, @Url String url, @Body JsonObject request);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @Multipart
    @POST()
    Call<JsonObject> uploadFile(@Url String url, @Part MultipartBody.Part file, @Body JsonObject request);

}
