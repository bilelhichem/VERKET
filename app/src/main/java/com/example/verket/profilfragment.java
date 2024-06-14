package com.example.verket;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verket.Paretenaire.DevenirPareten;
import com.example.verket.Paretenaire.Homepartenaire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profilfragment extends Fragment {
    TextView devenirpart, consuleprofilpartenaire, logout;
    DatabaseReference datadb;
    ImageView yad ;
    FirebaseAuth mauth;
    View view;
    Boolean ver = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profilfragment, container, false);

        init();
        String y = mauth.getCurrentUser().getUid();

        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals(y) && dataSnapshot.child("situation").getValue().equals("Accept")) {
                        ver = true;
                        break;
                    }
                }
                if (ver) {
                    yad.setVisibility(View.VISIBLE);
                    consuleprofilpartenaire.setVisibility(View.VISIBLE);
                } else {
                    consuleprofilpartenaire.setVisibility(View.GONE);
                    yad.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(getContext(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        devenirpart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DevenirPareten.class));
            }
        });

        consuleprofilpartenaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Homepartenaire.class));


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(),SignIn.class));
            }
        });

        return view;
    }

    public void init() {
        logout = view.findViewById(R.id.logout);
        devenirpart = view.findViewById(R.id.devenirpart);
        consuleprofilpartenaire = view.findViewById(R.id.consuleprofilpartenaire);
        mauth = FirebaseAuth.getInstance();
        yad = view.findViewById(R.id.yad);
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("partenaire");
    }
}
