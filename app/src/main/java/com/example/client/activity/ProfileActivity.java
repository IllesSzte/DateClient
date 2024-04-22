package com.example.client.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.api.UserApi;
import com.example.client.model.User;
import com.example.client.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView usernameTextView;
    private TextView dateCountTextView;
    private Button partnerButton;
    private Button myDatesButton;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeComponents();
    }

    private void initializeComponents() {
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", 0);
        Call<User> call = userApi.getUserById(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    usernameTextView.setText(user.getUserName());

                } else {
                    Log.e(TAG, "Hiba a kérésben: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Hálózati hiba: " + t.getMessage());
            }
        });


        profileImageView = findViewById(R.id.profileImageView);
        usernameTextView = findViewById(R.id.usernameTextView);
        dateCountTextView = findViewById(R.id.dateCountTextView);
        partnerButton = findViewById(R.id.partnerButton);
        myDatesButton = findViewById(R.id.myDatesButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationViewProfile);

        profileImageView.setImageResource(R.drawable.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_bottom_menu) {
                Intent home = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_bottom_menu) {
                Intent profile = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
                return true;
            } else if (item.getItemId() == R.id.logout_botton_menu) {
                Intent logout = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            }
            return false;
        });

        partnerButton.setOnClickListener(this::partner);
        myDatesButton.setOnClickListener(this::myDates);
    }

    private void myDates(View view) {
        Intent home = new Intent(ProfileActivity.this, MyDatesActivity.class);
        startActivity(home);
        finish();
    }
    private void partner(View view) {
        Intent home = new Intent(ProfileActivity.this, PartnerActivity.class);
        startActivity(home);
        finish();
    }
}

