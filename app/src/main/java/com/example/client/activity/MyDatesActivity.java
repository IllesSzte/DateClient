package com.example.client.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class MyDatesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private View menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dates);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDate();
        initializeComponents();
    }
    private void initializeComponents() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewDate);
        bottomNavigationView.setOnNavigationItemSelectedListener(item ->

        {
            if (item.getItemId() == R.id.home_bottom_menu) {
                Intent home = new Intent(MyDatesActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_bottom_menu) {
                Intent profile = new Intent(MyDatesActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
                return true;
            } else if (item.getItemId() == R.id.logout_botton_menu) {
                Intent logout = new Intent(MyDatesActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            }
            return false;
        });
    }

    private void loadDate() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        RetrofitService retrofitService = new RetrofitService();
        DateApi dateApi = retrofitService.getRetrofit().create(DateApi.class);
        dateApi.getUserDates(id)
                .enqueue(new Callback<List<Date>>() {
                    @Override
                    public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Date>> call, Throwable t) {
                        Toast.makeText(MyDatesActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Date> dates) {
        // Az adapter módosítása, hogy közvetlenül átadja a dátumot az Intent-en keresztül
        DateAdapter dateAdapter = new DateAdapter(dates, this, new OnItemClickListenerInterface() {
            @Override
            public void onItemClick(Date date) {
                // Az Intent-ben átadjuk a Date objektumot
                Intent intent = new Intent(MyDatesActivity.this, DateActivity.class);
                intent.putExtra("selectedDate", date); // A Date objektumot átadjuk
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(dateAdapter);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadDate();
    }

    public void createNewDateFab(View view) {
        Intent intent = new Intent(this, CreateDateActivity.class);
        startActivity(intent);
    }
}