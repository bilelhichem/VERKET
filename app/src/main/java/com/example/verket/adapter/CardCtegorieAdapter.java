package com.example.verket.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.Model.CardModel;
import com.example.verket.R;
import com.example.verket.viewhorder.CardCategorie;

import java.util.ArrayList;
import java.util.List;

public class CardCtegorieAdapter extends RecyclerView.Adapter<CardCategorie> {

    public     Context context ;
    public ArrayList<CardModel> items;
    public CardCtegorieAdapter(Context context, ArrayList<CardModel> items) {
        this.context = context;
        this.items = items;
    }





    @NonNull
    @Override
    public CardCategorie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardcategorie,parent,false);
        return new CardCategorie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardCategorie holder, int position) {
         CardModel item = items.get(position);
        holder.text.setText(item.getTitle());
        holder.image.setImageResource(item.getImageResource());
        String text = holder.text.getText().toString();

        if (text.equals("BIO")) {
            holder.bil.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.verket))); // Change to your desired color
        } else if (text.equals("COSMETIC")) {
            holder.bil.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.rose)));// Change to your desired color
        } else if (text.equals("DETERGENT")) {
            holder.bil.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.move))); // Change to your desired color
        } else if (text.equals("ARTISANAT")) {
            holder.bil.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.brune))); // Change to your desired color
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
