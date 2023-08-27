package com.example.client.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.example.client.holder.DateHolder;
import com.example.client.model.Date;


import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateHolder> {

    private List<Date> dates;

    public DateAdapter(List<Date> dates) {
        this.dates = dates;
    }

    @NonNull
    @Override
    public DateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_item, parent, false);
        return new DateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateHolder holder, int position) {
        Date date = dates.get(position);
        holder.title.setText(date.getName());
        holder.description.setText(date.getDescription());
        holder.price.setText(String.valueOf(date.getPrice())); // Ár formázása
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}