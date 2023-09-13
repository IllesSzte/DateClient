package com.example.client.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;
import com.example.client.api.UserApi;
import com.example.client.model.User;
import com.example.client.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void initializeComponents() {
        EditText inputEditTextName = findViewById(R.id.userName);
        EditText inputEditTextPassword = findViewById(R.id.password);
        Button buttonLogin = findViewById(R.id.logInUserLogin);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        buttonLogin.setOnClickListener(view -> {
            String name = String.valueOf(inputEditTextName.getText());
            String password = String.valueOf(inputEditTextPassword.getText());

            Call<User> call = userApi.getUserByNameAndPassword(name, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        if (user != null) {
                            // Bejelentkezési adatok mentése
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", name);
                            editor.putString("password", password);
                            editor.putInt("id", user.getId());
                            editor.apply();

                            home(view);
                        } else {
                            System.out.println("Hibas jelszo vagy felhasz nev");
                        }
                    } else {
                        System.out.println("API hiba: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("Hálózati hiba: " + t.getMessage());
                }
            });
        });
    }

    public void home(View view) {
        Intent intent = new Intent(this, DateActivity.class);
        startActivity(intent);
    }
}
