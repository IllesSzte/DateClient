package com.example.client.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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

public class CreateDate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createdate);
        initializeComponents();
    }

    private void initializeComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        TextInputEditText inputEditTextTitle = findViewById(R.id.dateTitle);
        TextInputEditText inputEditTextPrice = findViewById(R.id.datePrice);
        TextInputEditText inputEditTextDescription = findViewById(R.id.dateDescription);
        Button buttonCreate = findViewById(R.id.createNewDate);

        RetrofitService retrofitService = new RetrofitService();
        DateApi dateApi = retrofitService.getRetrofit().create(DateApi.class);

        buttonCreate.setOnClickListener(view -> {
            String title = String.valueOf(inputEditTextTitle.getText());
            int price = Integer.parseInt(inputEditTextPrice.getText().toString());
            String description = String.valueOf(inputEditTextDescription.getText());
            Date date = new Date(title, description, price);
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