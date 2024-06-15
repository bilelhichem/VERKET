package com.example.verket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verket.Model.ProduitModel;

import com.example.verket.adapter.AdapterDetous;
import com.example.verket.adapter.Pourcentageadapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

     View view;
     RecyclerView recyclerView, listdetout;
     FirebaseAuth mauth;
     DatabaseReference datadb;
     ImageView panieract ;
    ArrayList<ProduitModel> prood ;
    ArrayList<ProduitModel> proodd ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        UplaodToDb();
        LinearLayoutManager lin = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listdetout.setLayoutManager(lin);
        UplaodToDbdetout();
        panieract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),panier.class));
            }
        });
        return view;
    }

    public void init() {
        panieract = view.findViewById(R.id.panieract);
        recyclerView = view.findViewById(R.id.listdepource);
        listdetout = view.findViewById(R.id.listdetout);

        mauth = FirebaseAuth.getInstance();

        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("ProduitPartenaire");
    }

    public void UplaodToDb(){
        prood = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prood.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot da : dataSnapshot.getChildren()){
                        if (!da.child("pourcentage").getValue().toString().isEmpty()){
                            ProduitModel produitModel = da.getValue(com.example.verket.Model.ProduitModel.class);
                            prood.add(produitModel);
                        }

                    }
                }
                Pourcentageadapter pr = new Pourcentageadapter(getContext(),prood);
                recyclerView.setAdapter(pr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void UplaodToDbdetout(){
        proodd = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                proodd.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot da : dataSnapshot.getChildren()){

                        ProduitModel produitModel = da.getValue(com.example.verket.Model.ProduitModel.class);
                        proodd.add(produitModel);


                    }
                }
                AdapterDetous pr = new AdapterDetous(getContext(),proodd);
                listdetout.setAdapter(pr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
