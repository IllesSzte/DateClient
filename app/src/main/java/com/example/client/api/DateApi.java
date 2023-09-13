package com.example.client.api;

import com.example.client.model.Date;
import com.example.client.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DateApi {
    @GET("/date/get-all")
    Call<List<Date>> getAllDate();

    @POST("/date/create")
    Call<User> save(@Body Date date, @Query("userId") int userId);
}
