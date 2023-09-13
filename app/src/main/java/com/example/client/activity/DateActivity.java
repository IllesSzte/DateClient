package com.example.client.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.adapter.DateAdapter;
import com.example.client.api.DateApi;
import com.example.client.model.Date;
import com.example.client.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private View menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuButton = findViewById(R.id.menuButton);
        loadDate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_popup, menu);
        return true;
    }

    public void onMenuButtonClick(View view) {
        showPopupMenu(view); // A menü gomb kattintásának kezelése
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_popup);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.menu_item_add_new_date) {
                    return true;
                } else if (itemId == R.id.menu_item_profile) {
                    return true;
                } else if (itemId == R.id.menu_item_profile_logout) {
                    Intent intent = new Intent(DateActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }


    private void loadDate() {
        RetrofitService retrofitService = new RetrofitService();
        DateApi dateApi = retrofitService.getRetrofit().create(DateApi.class);
        dateApi.getAllDate()
                .enqueue(new Callback<List<Date>>() {
                    @Override
                    public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Date>> call, Throwable t) {
                        Toast.makeText(DateActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Date> dates) {
        DateAdapter dateAdapter = new DateAdapter(dates);
        recyclerView.setAdapter(dateAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDate();
    }

    public void menu_logout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void createNewDateFab(View view) {
        Intent intent = new Intent(this, CreateDate.class);
        startActivity(intent);
    }
}
