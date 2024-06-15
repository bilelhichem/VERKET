package com.example.verket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.Model.ProduitModel;
import com.example.verket.Model.commande;
import com.example.verket.R;
import com.example.verket.viewhorder.PnierViewHorder;
import com.example.verket.viewhorder.ViewHorderDetout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class panieradapter extends RecyclerView.Adapter<PnierViewHorder> {
    public Context context;
    public ArrayList<commande> productList;

    public panieradapter(Context context, ArrayList<commande> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public PnierViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.commandepannier, parent, false);
        return new PnierViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PnierViewHorder holder, int position) {
        holder.textView1.setText(productList.get(position).getNamedecommadne());
        holder.textView2.setText(productList.get(position).getDescrdecommande());
        holder.textView3.setText(productList.get(position).getQuantite());
        holder.textView4.setText(productList.get(position).getPricetotal());
        Picasso.get().load(productList.get(position).getImagedeprod()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
