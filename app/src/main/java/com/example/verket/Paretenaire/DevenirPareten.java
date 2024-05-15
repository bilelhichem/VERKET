package com.example.verket.Paretenaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.verket.R;

public class DevenirPareten extends AppCompatActivity {
LinearLayout liprod ;

Button producteur , affiliateur,addpr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devenir_pareten);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        Drawable drawable1 = getResources().getDrawable(R.drawable.bakbtn);
        Drawable drawable2 = getResources().getDrawable(R.drawable.bakbtn2);


        producteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                producteur.setBackground(drawable1);
                affiliateur.setBackground(drawable2);

                liprod.setVisibility(View.VISIBLE);

            }
        });

        affiliateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producteur.setBackground(drawable2);
                affiliateur.setBackground(drawable1);
                liprod.setVisibility(View.INVISIBLE);

            }
        });
        addpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DevenirPareten.this,addProduct.class));
            }
        });
    }


    public void init(){
        liprod = findViewById(R.id.liprod);
        producteur  = findViewById(R.id.producteur);
        affiliateur = findViewById(R.id.affiliateur);
        addpr = findViewById(R.id.addpr);
    }

}