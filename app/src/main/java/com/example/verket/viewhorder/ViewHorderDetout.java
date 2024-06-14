package com.example.verket.viewhorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.R;

public class ViewHorderDetout extends RecyclerView.ViewHolder {
    public ImageView image ;
    public TextView namedetous ;
    public TextView descdetout ;
    public ViewHorderDetout(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.primageee);
        namedetous = itemView.findViewById(R.id.namedetous);
        descdetout = itemView.findViewById(R.id.descdetout);
    }
}
