package com.example.client.api;

import com.example.client.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/user/get-all-user")
    Call<List<User>> getAllUser();

    @POST("/user/create")
    Call<User> save(@Body User user);

    @GET("/get-user-by-id")
    Call<User> getUserById(@Query("id") int id);

    @GET("/get-user-by-name")
    Call<User> getUserByName(@Query("name") String name);
}
