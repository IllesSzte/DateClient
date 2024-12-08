package com.example.client.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.adapter.DateAdapter;
import com.example.client.api.DateApi;
import com.example.client.interfaces.OnItemClickListenerInterface;
import com.example.client.model.Date;
import com.example.client.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeComponents();
    }

    private void initializeComponents() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Integer id = sharedPreferences.getInt("id", 0);

        EditText inputEditTextPrice = findViewById(R.id.priceEditText);
        Spinner inputEditTextPlace = findViewById(R.id.placeSpinner);
        Spinner inputEditTextCrowded = findViewById(R.id.crowdedSpinner);
        Spinner inputEditTextActivity = findViewById(R.id.activitySpinner);
        Spinner inputEditTextSeason = findViewById(R.id.seasonSpinner);
        Spinner inputEditTextDuration = findViewById(R.id.durationSpinner);
        Spinner inputEditTextDaytime = findViewById(R.id.daytimeSpinner);
        Button buttonSearch = findViewById(R.id.search_button);

        RetrofitService retrofitService = new RetrofitService();
        DateApi dateApi = retrofitService.getRetrofit().create(DateApi.class);

        buttonSearch.setOnClickListener(view -> {
            if (inputEditTextPrice.getText().toString().isEmpty()) {
                inputEditTextPrice.setText("0");
            }
            Integer price = Integer.parseInt(inputEditTextPrice.getText().toString());
            String place = inputEditTextPlace.getSelectedItem().toString();
            String crowded = inputEditTextCrowded.getSelectedItem().toString();
            String activity = inputEditTextActivity.getSelectedItem().toString();
            String season = inputEditTextSeason.getSelectedItem().toString();
            String duration = inputEditTextDuration.getSelectedItem().toString();
            String dayTime = inputEditTextDaytime.getSelectedItem().toString();

            Call<List<Date>> call = dateApi.getFilteredDates(id, price, place, crowded, activity, season, duration, dayTime);
            call.enqueue(new Callback<List<Date>>() {
                @Override
                public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                    if (response.isSuccessful()) {
                        // Felcsúszás
                        LinearLayout linearLayout = findViewById(R.id.linearLayout);
                        linearLayout.setVisibility(View.GONE); // Felcsúszás előtt elrejtjük
                        linearLayout.animate().translationY(-linearLayout.getHeight()).setDuration(500);

                        // RecyclerView frissítése az új adatokkal
                        List<Date> dates = response.body();
                        DateAdapter adapter = new DateAdapter(dates, HomeActivity.this, new OnItemClickListenerInterface() {
                            @Override
                            public void onItemClick(Date date) {
                                // Az Intent-ben átadjuk a Date objektumot
                                Intent intent = new Intent(HomeActivity.this, DateActivity.class);
                                intent.putExtra("selectedDate", date); // A Date objektumot átadjuk
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e(TAG, "User id not found: " + id);
                        Log.e(TAG, "Hiba a kérésben: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Date>> call, Throwable t) {
                    Log.e(TAG, "Hálózati hiba: " + t.getMessage());
                }
            });
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_bottom_menu) {
                Intent home = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_bottom_menu) {
                Intent profile = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
                return true;
            } else if (item.getItemId() == R.id.logout_botton_menu) {
                Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            }
            return false;
        });
    }
}