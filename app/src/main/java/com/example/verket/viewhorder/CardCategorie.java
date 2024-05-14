package com.example.verket.viewhorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.R;

public class CardCategorie extends RecyclerView.ViewHolder {
   public TextView text ;
   public ImageView image ;

   public ConstraintLayout bil ;
    public CardCategorie(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
        image = itemView.findViewById(R.id.image);
        bil = itemView.findViewById(R.id.bil);
    }
}
