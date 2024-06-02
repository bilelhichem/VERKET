package com.example.verket.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.Model.ProduitModel;
import com.example.verket.Paretenaire.UpdateProduct;
import com.example.verket.R;
import com.example.verket.viewhorder.CardCategorie;
import com.example.verket.viewhorder.ProductViewHorder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class produitadapter extends RecyclerView.Adapter<ProductViewHorder> {

    public Context context ;
    public ArrayList<ProduitModel> prood;

    DatabaseReference datadb ;

    FirebaseAuth mauth  ;


    public produitadapter(Context context, ArrayList<ProduitModel> prood) {
        this.context = context;
        this.prood = prood;
        if (context != null) {

            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();


            datadb = FirebaseDatabase.getInstance(context.getString(R.string.db_url)).getReference().child("ProduitPartenaire").child(userid);
        }
    }

    @NonNull
    @Override
    public ProductViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.costumercard1,parent,false);
        return new ProductViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHorder holder, int position) {


        Picasso.get().load(prood.get(position).getImageproduit()).into(holder.imageView);
        holder.prix2.setText(prood.get(position).getPrixproduit());
           holder.prix1.setText(prood.get(position).getNameproduit());

           String uid = prood.get(position).getId();

           holder.Suprimier.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   datadb.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {

                           for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                               if (dataSnapshot.child("id").exists() && dataSnapshot.child("id").getValue(String.class).equals(uid)) {
                                   dataSnapshot.getRef().removeValue();
                                   break;
                               }
                           }



                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });

               }
           });

           holder.modifier.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent i = new Intent(context, UpdateProduct.class);
                   i.putExtra("itemId",uid);
                   context.startActivity(i);
               }
           });


    }

    @Override
    public int getItemCount() {
        return prood.size();
    }
}
