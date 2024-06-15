package com.example.verket.viewhorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.R;

public class PnierViewHorder extends RecyclerView.ViewHolder {
  public   ImageView img ;
   public TextView textView1 , textView2, textView3 , textView4 ;

    public PnierViewHorder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.panierimage);
        textView1 = itemView.findViewById(R.id.namepanier);
        textView2 = itemView.findViewById(R.id.descpanier);
         textView3= itemView.findViewById(R.id.quantitepanier);
        textView4 = itemView.findViewById(R.id.pricretotalpanier);


    }
}
