package com.example.verket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verket.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
TextView signup ;
Button login ;
    FirebaseAuth mauth ;

    ProgressDialog loading;
EditText namesignuser , passsignuser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        init();


        loading = new ProgressDialog(SignIn.this);
        loading.setTitle("Welcome In our app");
        loading.setMessage("Please wait minute");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,SignUp.class));
                finish();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email  = namesignuser.getText().toString();
                String password  = passsignuser.getText().toString();

               if (email.isEmpty()) {
                    namesignuser.setError("Email cannot be empty");
                }else if (password.isEmpty()) {
                    passsignuser.setError("Password cannot be empty");
                }else if (!isValidEmail(email)){
                    namesignuser.setError("email invalid");
                }else{
                   loading.show();
                   creataccount(email,password);

                }
            }
        });
    }

    public void init(){
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        namesignuser = findViewById(R.id.namesignuser);
        passsignuser = findViewById(R.id.passsignuser);
        mauth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
    }

    public static boolean isValidEmail(String email) {
        // Expression régulière pour vérifier le format d'e-mail
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void creataccount(String email , String passowrd) {

        mauth.signInWithEmailAndPassword(email, passowrd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loading.dismiss();
                           startActivity(new Intent(SignIn.this,HomeActivity.class));
                           finish();
                        }else{
                            loading.dismiss();
                            Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }



                });}

}