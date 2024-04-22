package com.example.client.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.adapter.DateAdapter;
import com.example.client.api.DateApi;
import com.example.client.api.UserApi;
import com.example.client.model.Date;
import com.example.client.model.User;
import com.example.client.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnerActivity extends AppCompatActivity {
    private TextView usernameTextView;
    private TextView partnernameTextView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);
        recyclerView = findViewById(R.id.recyclerViewPartner);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadDate();
        initializeComponents();
    }

    private void loadDate() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        RetrofitService retrofitService = new RetrofitService();
        DateApi dateApi = retrofitService.getRetrofit().create(DateApi.class);
        dateApi.getUserAndPartnerDates(id)
                .enqueue(new Callback<List<Date>>() {
                    @Override
                    public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {

                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Date>> call, Throwable t) {
                        Toast.makeText(PartnerActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        userApi.getUserById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                usernameTextView.setText(user.getUserName());
                userApi.getUserById(user.getPartnerId()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        partnernameTextView.setText(user.getUserName());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void populateListView(List<Date> dates) {
        DateAdapter dateAdapter = new DateAdapter(dates);
        recyclerView.setAdapter(dateAdapter);
    }

    private void initializeComponents() {
        usernameTextView = findViewById(R.id.textViewUserName);
        partnernameTextView = findViewById(R.id.textViewPartnerName);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewPartner);

        bottomNavigationView.setOnNavigationItemSelectedListener(item ->

        {
            if (item.getItemId() == R.id.home_bottom_menu) {
                Intent home = new Intent(PartnerActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_bottom_menu) {
                Intent profile = new Intent(PartnerActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
                return true;
            } else if (item.getItemId() == R.id.logout_botton_menu) {
                Intent logout = new Intent(PartnerActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            }
            return false;
        });
    }
}