package com.dim.retrofitloginexample.ui;

import com.dim.retrofitloginexample.model.LoginResponse;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface APIInterface {
    @FormUrlEncoded
    @POST("retrofit/register.php")
    public void registrar(@Field("nombre") String nombre,
                          @Field("password") String password,
                          @Field("loginType") String loginType,
                          Callback<LoginResponse> callback);
}
