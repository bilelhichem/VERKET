package com.example.verket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.verket.Model.ProduitModel;
import com.example.verket.adapter.AdapterDetous;
import com.example.verket.adapter.Pourcentageadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeCategorie extends AppCompatActivity {
    String item ;
RecyclerView catrecycle ;
    DatabaseReference datadb;
    ArrayList<ProduitModel> proodd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_categorie);
        init();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
     item = getIntent().getStringExtra("item");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeCategorie.this,LinearLayoutManager.HORIZONTAL,false);
        catrecycle.setLayoutManager(linearLayoutManager);
        UplaodToDbdetout();
    }
    public void init(){
        catrecycle = findViewById(R.id.catrecycle);
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("ProduitPartenaire");

    }
    public void UplaodToDbdetout(){
        proodd = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                proodd.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot da : dataSnapshot.getChildren()){
                        if (da.child("categorieproduit").getValue().equals(item)){
                            ProduitModel produitModel = da.getValue(com.example.verket.Model.ProduitModel.class);
                            proodd.add(produitModel);
                        }




                    }
                }
                AdapterDetous pr = new AdapterDetous(HomeCategorie.this,proodd);
                catrecycle.setAdapter(pr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}