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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.api.DateApi;
import com.example.client.model.Date;
import com.example.client.model.User;
import com.example.client.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_date);
        initializeComponents();
    }

    private void initializeComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        EditText inputEditTextTitle = findViewById(R.id.editTitle);
        EditText inputEditTextDescription = findViewById(R.id.editDescription);
        EditText inputEditTextPrice = findViewById(R.id.editPrice);
        Spinner spinnerPlace = findViewById(R.id.spinnerPlace);
        Spinner spinnerCrowded = findViewById(R.id.spinnerCrowded);
        Spinner spinnerActivity = findViewById(R.id.spinnerActivity);
        Spinner spinnerSeason = findViewById(R.id.spinnerSeason);
        Spinner spinnerDuration = findViewById(R.id.spinnerDuration);
        Spinner spinnerDaytime = findViewById(R.id.spinnerDaytime);
        Button buttonCreate = findViewById(R.id.createNewDateSecond);
        Button buttonCancel = findViewById(R.id.cancelSecond);
        buttonCancel.setOnClickListener(this::profile);

        RetrofitService retrofitService = new RetrofitService();
        DateApi dateApi = retrofitService.getRetrofit().create(DateApi.class);

        buttonCreate.setOnClickListener(view -> {
            String title = String.valueOf(inputEditTextTitle.getText());
            int price = Integer.parseInt(inputEditTextPrice.getText().toString());
            String description = String.valueOf(inputEditTextDescription.getText());
            String selectedPlace = spinnerPlace.getSelectedItem().toString();
            String selectedCrowded = spinnerCrowded.getSelectedItem().toString();
            String selectedActivity = spinnerActivity.getSelectedItem().toString();
            String selectedSeason = spinnerSeason.getSelectedItem().toString();
            String selectedDuration = spinnerDuration.getSelectedItem().toString();
            String selectedDaytime = spinnerDaytime.getSelectedItem().toString();
            Date date = new Date(
                    title,
                    description,
                    price,
                    selectedPlace,
                    selectedCrowded,
                    selectedActivity,
                    selectedSeason,
                    selectedDuration,
                    selectedDaytime
            );
            Call<User> call = dateApi.save(date, id);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        // Sikeres válasz esetén itt lehet kezelni a választ
                        // Megjelenítjük a sikeres üzenetet
                        Toast.makeText(CreateDateActivity.this, "Sikeresen létrehoztad a randit: " + title, Toast.LENGTH_SHORT).show();
                        // Visszaállítjuk az input mezők alapértelmezett értékeit
                        inputEditTextTitle.setText("");
                        inputEditTextDescription.setText("");
                        inputEditTextPrice.setText("");
                        spinnerPlace.setSelection(0);
                        spinnerCrowded.setSelection(0);
                        spinnerActivity.setSelection(0);
                        spinnerSeason.setSelection(0);
                        spinnerDuration.setSelection(0);
                        spinnerDaytime.setSelection(0);
                    } else {
                        // Hiba esetén itt lehet kezelni a hibát
                        Log.e(TAG, "Hiba a kérésben: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Hálózati hiba esetén itt lehet kezelni a hibát
                    Log.e(TAG, "Hálózati hiba: " + t.getMessage());
                }
            });
        });
    }
    private void initializeBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_bottom_menu) {
                Intent home = new Intent(CreateDateActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile_bottom_menu) {
                Intent profile = new Intent(CreateDateActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
                return true;
            } else if (item.getItemId() == R.id.logout_botton_menu) {
                Intent logout = new Intent(CreateDateActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
            }
            return false;
        });
    }
    private void profile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}