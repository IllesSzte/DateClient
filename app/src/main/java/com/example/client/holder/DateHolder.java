package com.example.client.holder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;

public class DateHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView description;
    public EditText price;
    public TextView place;
    public TextView crowded;
    public TextView activity;
    public TextView season;
    public TextView duration;
    public TextView daytime;

    public DateHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.dateListItem_title);
        description = itemView.findViewById(R.id.dateListItem_description);
        price = itemView.findViewById(R.id.dateListItem_price);
        place = itemView.findViewById(R.id.dateListItem_place);
        crowded = itemView.findViewById(R.id.dateListItem_crowded);
        activity = itemView.findViewById(R.id.dateListItem_activity);
        season = itemView.findViewById(R.id.dateListItem_season);
        duration = itemView.findViewById(R.id.dateListItem_duration);
        daytime = itemView.findViewById(R.id.dateListItem_daytime);
    }

}
