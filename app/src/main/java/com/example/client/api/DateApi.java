package com.example.client.api;

import com.example.client.model.Date;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DateApi {
    @GET("/date/get-all")
    Call<List<Date>> getAllDate();
}
