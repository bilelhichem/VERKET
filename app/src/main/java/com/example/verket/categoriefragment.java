package com.example.verket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.verket.Model.CardModel;
import com.example.verket.Model.ProduitModel;
import com.example.verket.adapter.AdapterDetous;
import com.example.verket.adapter.CardCtegorieAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class categoriefragment extends Fragment {
RecyclerView recycleview ;
ArrayList<CardModel> arrayList ;
    SearchView searchView;
    DatabaseReference datadb;
    ArrayList<ProduitModel> proodd ;
View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categoriefragment, container, false) ;
        init();
        UplaodToDbdetout();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    arrayList.add(new CardModel("BIO", R.drawable.khodra));
                    arrayList.add(new CardModel("COSMETIC", R.drawable.riha));
                    arrayList.add(new CardModel("DETERGENT", R.drawable.sabon));
                    arrayList.add(new CardModel("ARTISANAT", R.drawable.artisana));


                    recycleview.setLayoutManager(new GridLayoutManager(getContext(), 2));

                    CardCtegorieAdapter adapter = new CardCtegorieAdapter(getContext(),arrayList);
                    recycleview.setAdapter(adapter);
                } else {
                    filterDataList(newText);

                }
                return true;
            }
        });

        arrayList = new ArrayList<>();

        try {

            arrayList.add(new CardModel("BIO", R.drawable.khodra));
            arrayList.add(new CardModel("COSMETIC", R.drawable.riha));
            arrayList.add(new CardModel("DETERGENT", R.drawable.sabon));
            arrayList.add(new CardModel("ARTISANAT", R.drawable.artisana));


            recycleview.setLayoutManager(new GridLayoutManager(getContext(), 2));

            CardCtegorieAdapter adapter = new CardCtegorieAdapter(getContext(),arrayList);
            recycleview.setAdapter(adapter);
        }catch (Exception e){
            System.out.println(e.toString());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();

        }
        return view;
    }

    public void init(){
        searchView = view.findViewById(R.id.searchview);
        recycleview = view.findViewById(R.id.recycleview);
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

                            ProduitModel produitModel = da.getValue(com.example.verket.Model.ProduitModel.class);
                            proodd.add(produitModel);





                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void filterDataList(String query) {

        ArrayList<ProduitModel> filteredProod = new ArrayList<>();
        for (ProduitModel product : proodd) {
            if (product.getNameproduit().toLowerCase().contains(query.toLowerCase())) {
                filteredProod.add(product);
            }
        }

        AdapterDetous pr = new AdapterDetous(getContext(),proodd);
        recycleview.setAdapter(pr);
    }

}