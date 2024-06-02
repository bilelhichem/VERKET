package com.example.verket.Paretenaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.verket.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class emailverifparten extends AppCompatActivity {

    EditText emailverif ;
    Button confirmmail ;
    DatabaseReference datadb ;
    Integer cmp  ;
    Integer sit  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverifparten);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        confirmmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initFav();
                try {
                    if (cmp>0) {
                        if (sit == 1){
                            Intent i = new Intent(emailverifparten.this,Homepartenaire.class);

                            i.putExtra("email",emailverif.getText().toString());
                            startActivity(i);
                        }else if(sit == -1){
                            Toast.makeText(emailverifparten.this, "Refuse", Toast.LENGTH_SHORT).show();

                        }else if (sit == 0) {
                            startActivity(new Intent(emailverifparten.this,attente.class));

                        }


                    }else {
                        Toast.makeText(emailverifparten.this, "email n'exist pas", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(emailverifparten.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void init(){
        cmp = 0 ;
        sit = 0 ;
        emailverif = findViewById(R.id.emailverif);
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("partenaire");
        confirmmail = findViewById(R.id.confirmmail);
    }


    public void initFav() {

        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.child("emailpart").getValue());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){



                    if (dataSnapshot.child("emailpart").getValue().toString().equals(emailverif.getText().toString())){
                        cmp ++;
                        if (dataSnapshot.child("situation").getValue().toString().equals("Accept")){
                            sit = 1 ;
                        }else   if (dataSnapshot.child("situation").getValue().toString().equals("Refuse")){
                            sit = -1 ;

                        }else {
                            sit = 0 ;
                        }
                    }


                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}