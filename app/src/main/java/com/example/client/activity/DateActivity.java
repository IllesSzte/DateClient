package com.example.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.client.R;
import com.example.client.adapter.ImageAdapter;
import com.example.client.model.Date;
import java.util.List;

public class DateActivity extends AppCompatActivity {

    private TextView titleTextView, descriptionTextView, priceTextView, placeTextView,
            crowdedTextView, activityTextView, seasonTextView, durationTextView, daytimeTextView;
    private RecyclerView imageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        // Kiválasztott dátum átadása az Intent-tel
        Intent intent = getIntent();
        Date selectedDate = (Date) intent.getSerializableExtra("selectedDate");

        if (selectedDate != null) {
            // A Date objektum használata
            titleTextView = findViewById(R.id.dateDetails_title);
            descriptionTextView = findViewById(R.id.dateDetails_description);
            priceTextView = findViewById(R.id.dateDetails_price);
            placeTextView = findViewById(R.id.dateDetails_place);
            crowdedTextView = findViewById(R.id.dateDetails_crowded);
            activityTextView = findViewById(R.id.dateDetails_activity);
            seasonTextView = findViewById(R.id.dateDetails_season);
            durationTextView = findViewById(R.id.dateDetails_duration);
            daytimeTextView = findViewById(R.id.dateDetails_daytime);
            imageRecyclerView = findViewById(R.id.dateDetails_images);

            // Megjelenítés
            titleTextView.setText(selectedDate.getTitle());
            descriptionTextView.setText(selectedDate.getDescription());
            priceTextView.setText("Price: " + selectedDate.getPrice());
            placeTextView.setText("Place: " + selectedDate.getPlace());
            crowdedTextView.setText("Crowded: " + selectedDate.getCrowded());
            activityTextView.setText("Activity: " + selectedDate.getActivity());
            seasonTextView.setText("Season: " + selectedDate.getSeason());
            durationTextView.setText("Duration: " + selectedDate.getDuration());
            daytimeTextView.setText("Daytime: " + selectedDate.getDaytime());

            // RecyclerView beállítása
            imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Két oszlopos elrendezés

            if (selectedDate != null) {
                // Adapter hozzárendelése
                ImageAdapter adapter = new ImageAdapter(selectedDate.getPicture());
                imageRecyclerView.setAdapter(adapter);
            }
        }
    }
}
