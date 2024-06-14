package com.example.verket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.Model.ProduitModel;
import com.example.verket.R;
import com.example.verket.viewhorder.PoucentageViewHorder;
import com.example.verket.viewhorder.ViewHorderDetout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDetous extends RecyclerView.Adapter<ViewHorderDetout> {
    public Context context;
    public ArrayList<ProduitModel> productList;

    public AdapterDetous(Context context, ArrayList<ProduitModel> productList) {
        this.context = context;
        this.productList = productList;
    }
    @NonNull
    @Override
    public ViewHorderDetout onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carddetout, parent, false);
        return new ViewHorderDetout(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHorderDetout holder, int position) {

        holder.namedetous.setText(productList.get(position).getNameproduit());
        holder.descdetout.setText(productList.get(position).getDescriptionproduit());
        Picasso.get().load(productList.get(position).getImageproduit()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
