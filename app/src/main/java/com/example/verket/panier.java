package com.example.verket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.verket.Model.ProduitModel;
import com.example.verket.Model.commande;
import com.example.verket.adapter.Pourcentageadapter;
import com.example.verket.adapter.panieradapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class panier extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth mauth;
    DatabaseReference datadb;
    ArrayList<commande> prood ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(panier.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        UplaodToDb();
    }

    public void init() {
        recyclerView = findViewById(R.id.listcommande);



        mauth = FirebaseAuth.getInstance();

        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("CommandeClient").child(mauth.getCurrentUser().getUid());
    }


    public void UplaodToDb(){
        prood = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prood.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    commande produitModel = dataSnapshot.getValue(com.example.verket.Model.commande.class);
                    prood.add(produitModel);
                }
                panieradapter pr = new panieradapter(panier.this,prood);
                recyclerView.setAdapter(pr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}