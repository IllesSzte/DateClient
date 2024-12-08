package com.example.client.holder;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        // Az item_image.xml-ben található ImageView keresése
        imageView = itemView.findViewById(R.id.itemImageImage);
    }
}
