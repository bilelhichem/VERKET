package com.example.verket.Paretenaire;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verket.Model.ProduitModel;
import com.example.verket.R;
import com.example.verket.adapter.produitadapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homefragpart extends Fragment {
    View view ;
    FirebaseAuth mauth ;
    RecyclerView recli ;
    DatabaseReference datadb ;
    ProgressDialog loading;

    ArrayList<ProduitModel> prood ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_homefragpart, container, false) ;

        init();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recli.setLayoutManager(linearLayoutManager);
        UplaodToDb();


        return view;
    }

    public  void init(){
        recli = view.findViewById(R.id.re);
        mauth = FirebaseAuth.getInstance();
        String userId = mauth.getCurrentUser().getUid();
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("ProduitPartenaire").child(userId);
    }


    public void UplaodToDb(){
        prood = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prood.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProduitModel produitModel = dataSnapshot.getValue(com.example.verket.Model.ProduitModel.class);
                    prood.add(produitModel);
                }
                produitadapter pr = new produitadapter(getContext(),prood);
                recli.setAdapter(pr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}