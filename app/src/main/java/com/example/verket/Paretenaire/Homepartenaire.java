package com.example.verket.Paretenaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.verket.R;

public class Homepartenaire extends AppCompatActivity {
    ImageView home , profil  , list;
    String itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepartenaire);
        init();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameFragment,new homefragpart())
                .commit();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profil.setColorFilter(getResources().getColor(android.R.color.black));
                home.setColorFilter(getResources().getColor(android.R.color.white));
                list.setColorFilter(getResources().getColor(android.R.color.black));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new homefragpart())
                        .commit();
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profil.setColorFilter(getResources().getColor(android.R.color.white));
                home.setColorFilter(getResources().getColor(android.R.color.black));
                list.setColorFilter(getResources().getColor(android.R.color.black));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new addfrag())
                        .commit();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profil.setColorFilter(getResources().getColor(android.R.color.black));
                home.setColorFilter(getResources().getColor(android.R.color.black));
                list.setColorFilter(getResources().getColor(android.R.color.white));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new listpartfrag())
                        .commit();
            }
        });

    }
    public  void init(){
        home = findViewById(R.id.home);
        profil = findViewById(R.id.profil);
        list = findViewById(R.id.list);
    }
}