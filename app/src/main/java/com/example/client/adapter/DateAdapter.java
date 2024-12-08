package com.example.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.client.R;
import com.example.client.activity.DateActivity;
import com.example.client.holder.DateHolder;
import com.example.client.interfaces.OnItemClickListenerInterface;
import com.example.client.model.Date;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateHolder> {

    private List<Date> dates;
    private OnItemClickListenerInterface onItemClickListenerInterface;
    private Context context;

    public DateAdapter(List<Date> dates, Context context, OnItemClickListenerInterface onItemClickListenerInterface) {
        this.dates = dates;
        this.context = context;
        this.onItemClickListenerInterface = onItemClickListenerInterface;
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
        holder.title.setText("Title: " + date.getTitle());
        holder.description.setText("Description: " + date.getDescription());
        holder.price.setText("Price: " + String.valueOf(date.getPrice()));
        holder.place.setText("Place: " + date.getPlace());
        holder.crowded.setText("Crowded: " + date.getCrowded());
        holder.activity.setText("Activity: " + date.getActivity());
        holder.duration.setText("Duration: " + date.getDuration());

        Glide.with(holder.itemView.getContext())
                .load(date.getPicture().get(0))
                .placeholder(R.drawable.heart)
                .error(R.drawable.ic_button)
                .into(holder.picture);

        // Handling item click
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListenerInterface != null) {
                onItemClickListenerInterface.onItemClick(date);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}

