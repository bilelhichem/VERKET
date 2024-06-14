package com.example.verket.viewhorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.R;

public class PoucentageViewHorder extends RecyclerView.ViewHolder {

    public ImageView primage;
    public TextView pr1;
    public TextView pr2;

    public PoucentageViewHorder(@NonNull View itemView) {
        super(itemView);
        primage = itemView.findViewById(R.id.primagee);
        pr1 = itemView.findViewById(R.id.pr11);
        pr2 = itemView.findViewById(R.id.pr22);
    }
}
