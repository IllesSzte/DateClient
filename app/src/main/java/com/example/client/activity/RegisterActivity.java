package com.example.client.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.client.R;
import com.example.client.model.User;
import com.example.client.retrofit.RetrofitService;
import com.example.client.api.UserApi;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeComponents();
    }

    private void initializeComponents() {
        EditText inputEditTextName = findViewById(R.id.userNameRegister);
        EditText inputEditTextPassword = findViewById(R.id.passwordRegister);
        Button buttonSave = findViewById(R.id.registerUserRegister);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditTextName.getText());
            String password = String.valueOf(inputEditTextPassword.getText());
            User user = new User(name, password);
            userApi.save(user);
            home(view);
        });
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}