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
    @GET("/date/get-user-and-partner-dates")
    Call<List<Date>> getUserAndPartnerDates(@Query("userId") int id);

    @GET("/date/get-user-dates")
    Call<List<Date>> getUserDates(@Query("userId") int id);
    @POST("/date/create")
    Call<User> save(@Body Date date, @Query("userId") int userId);

    @GET("/date/get-filtered-dates")
    Call<List<Date>> getFilteredDates(@Query("user_in") Integer user_in,
                                       @Query("price_in") Integer price_in,
                                       @Query("place_in") String place_in,
                                       @Query("crowded_in") String crowded_in,
                                       @Query("activity_in") String activity_in,
                                       @Query("season_in") String season_in,
                                       @Query("duration_in") String duration_in,
                                       @Query("daytime_in") String daytime_in);
}
