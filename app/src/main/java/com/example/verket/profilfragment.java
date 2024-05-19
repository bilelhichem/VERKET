package com.example.verket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.verket.Paretenaire.DevenirPareten;
import com.example.verket.Paretenaire.emailverifparten;


public class profilfragment extends Fragment {
  TextView devenirpart , consuleprofilpartenaire;
  View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profilfragment, container, false);

        init();

        devenirpart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DevenirPareten.class));

            }
        });

        consuleprofilpartenaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), emailverifparten.class));

            }
        });
        return view ;
    }


    public  void init(){
        devenirpart = view.findViewById(R.id.devenirpart);
        consuleprofilpartenaire = view.findViewById(R.id.consuleprofilpartenaire);
    }

}