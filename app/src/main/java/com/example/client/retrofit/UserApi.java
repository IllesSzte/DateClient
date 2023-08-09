package com.example.client.retrofit;

import com.example.client.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @GET("/user/get-all-user")
    Call<List<User>> getAllEmployees();

    @POST("/user/create")
    Call<User> save(@Body User employee);
}
