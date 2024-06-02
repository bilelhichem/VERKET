package com.example.verket.viewhorder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.R;

public class ProductViewHorder extends RecyclerView.ViewHolder {
  public ImageView imageView ;
   public TextView prix1 ;
   public TextView prix2 ;
   public Button modifier ;
   public  Button Suprimier ;
    public ProductViewHorder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.primage);
        prix1 = itemView.findViewById(R.id.pr1);
        prix2 = itemView.findViewById(R.id.pr2);
        modifier = itemView.findViewById(R.id.modifier);
        Suprimier = itemView.findViewById(R.id.suprimier);




    }
}
