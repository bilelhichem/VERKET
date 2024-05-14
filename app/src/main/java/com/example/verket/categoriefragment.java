package com.example.verket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.verket.Model.CardModel;
import com.example.verket.adapter.CardCtegorieAdapter;

import java.util.ArrayList;


public class categoriefragment extends Fragment {
RecyclerView recycleview ;
ArrayList<CardModel> arrayList ;
View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_categoriefragment, container, false) ;
        init();

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
        recycleview = view.findViewById(R.id.recycleview);

    }

}