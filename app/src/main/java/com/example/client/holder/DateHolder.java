package com.example.client.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import com.example.client.R;

public class DateHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView description;
    public TextView price;

    public DateHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.dateListItem_title);
        description = itemView.findViewById(R.id.dateListItem_description);
        price = itemView.findViewById(R.id.dateListItem_price);
    }
}
