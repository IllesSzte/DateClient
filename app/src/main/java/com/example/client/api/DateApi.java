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
    Call<List<Date>> getFilteredDates(@Query("userIn") Integer user_in,
                                      @Query("priceIn") Integer price_in,
                                      @Query("placeIn") String place_in,
                                      @Query("crowdedIn") String crowded_in,
                                      @Query("activityIn") String activity_in,
                                      @Query("seasonIn") String season_in,
                                      @Query("durationIn") String duration_in,
                                      @Query("daytimeIn") String daytime_in);
}
