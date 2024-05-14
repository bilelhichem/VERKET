package com.example.verket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
LinearLayout li1,li2,li3,li4,li5 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameFragment,new HomeFragment())
                .commit();


        li1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li1.setBackground(getResources().getDrawable(R.drawable.cardbackround));
                li2.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li3.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li4.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li5.setBackground(getResources().getDrawable(R.drawable.cardbackround2));

            }
        });

        li2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li1.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li2.setBackground(getResources().getDrawable(R.drawable.cardbackround));
                li3.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li4.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li5.setBackground(getResources().getDrawable(R.drawable.cardbackround2));

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new categoriefragment())
                        .commit();
            }
        });


        li3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li1.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li2.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li3.setBackground(getResources().getDrawable(R.drawable.cardbackround));
                li4.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li5.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new HomeFragment())
                        .commit();

            }
        });


        li4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li1.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li2.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li3.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li4.setBackground(getResources().getDrawable(R.drawable.cardbackround));
                li5.setBackground(getResources().getDrawable(R.drawable.cardbackround2));

            }
        });

        li5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li1.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li2.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li3.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li4.setBackground(getResources().getDrawable(R.drawable.cardbackround2));
                li5.setBackground(getResources().getDrawable(R.drawable.cardbackround));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new profilfragment())
                        .commit();

            }
        });



    }

    public void init(){
        li1 = findViewById(R.id.li1);
        li2 = findViewById(R.id.li2);
        li3 = findViewById(R.id.li3);
        li4 = findViewById(R.id.li4);
        li5 = findViewById(R.id.li5);

    }

}