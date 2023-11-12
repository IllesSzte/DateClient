package com.example.client.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.client.R;
import com.example.client.api.DateApi;
import com.example.client.api.UserApi;
import com.example.client.model.Date;
import com.example.client.model.User;
import com.example.client.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
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
        Button buttonCreate = findViewById(R.id.createNewDate);

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

}