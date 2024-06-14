package com.example.verket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.Model.ProduitModel;
import com.example.verket.R;
import com.example.verket.commandeclient;
import com.example.verket.viewhorder.PoucentageViewHorder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Pourcentageadapter extends RecyclerView.Adapter<PoucentageViewHorder> {

    public Context context;
    public ArrayList<ProduitModel> productList;

    public Pourcentageadapter(Context context, ArrayList<ProduitModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public PoucentageViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.consumercard, parent, false);
        return new PoucentageViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoucentageViewHorder holder, int position) {
        Picasso.get().load(productList.get(position).getImageproduit()).into(holder.primage);

        String prixProduit = productList.get(position).getPrixproduit();
        holder.pr1.setText(productList.get(position).getPrixproduit());
        String pourcentage = productList.get(position).getPourcentage();

        holder.primage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, commandeclient.class);
                intent.putExtra("item",productList.get(position).getId());
                context.startActivity(intent);
            }
        });

        // Check if the strings are empty or null
        if (!prixProduit.isEmpty() && !pourcentage.isEmpty()) {
            int prix = Integer.parseInt(prixProduit);
            int percentage = Integer.parseInt(pourcentage);

            int discountedPrice = (prix * percentage) / 100;
            holder.pr2.setText(String.valueOf(discountedPrice)); // Convert int to String
        } else {

            holder.pr2.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
