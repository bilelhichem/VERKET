package com.example.verket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.verket.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
ImageView back ;
EditText nameuser , prenomuser , emailuser , passuser ;

Button Register ;

FirebaseAuth mauth ;
DatabaseReference datadb ;
ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();

        loading = new ProgressDialog(SignUp.this);
        loading.setTitle("Welcome In our app");
        loading.setMessage("Please wait minute");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,SignIn.class));
                finish();
            }
        });



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name  = nameuser.getText().toString();
                String prenome  = prenomuser.getText().toString();
                String email  = emailuser.getText().toString();
                String password  = passuser.getText().toString();

                if (name.isEmpty()) {
                    nameuser.setError("Name cannot be empty");
                }else if (prenome.isEmpty()) {
                    prenomuser.setError("Surname cannot be empty");
                }else if (email.isEmpty()) {
                    emailuser.setError("Email cannot be empty");
                }else if (password.isEmpty()) {
                    passuser.setError("Password cannot be empty");
                }else if (!isValidEmail(email)){
                    emailuser.setError("email invalid");
                }else{
                    loading.show();
                    creataccount(email,password,name,password);
                }





            }
        });



    }
    public void init(){
        back = findViewById(R.id.back);
        nameuser = findViewById(R.id.nameuser);
        prenomuser = findViewById(R.id.prenomuser);
        emailuser = findViewById(R.id.emailuser);
        passuser = findViewById(R.id.passuser);
        Register = findViewById(R.id.Register);
        mauth = FirebaseAuth.getInstance();
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("users");


    }


    public static boolean isValidEmail(String email) {
        // Expression régulière pour vérifier le format d'e-mail
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public void creataccount(String email , String passowrd,String name , String pren) {

        mauth.createUserWithEmailAndPassword(email, passowrd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveuserindatabase(new UserModel(name , pren , email));
                        }else{
                            loading.dismiss();
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }



                });}


    public void saveuserindatabase(UserModel user){
        datadb.child(mauth.getCurrentUser().getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            loading.dismiss();
                            Intent i = new Intent(SignUp.this,HomeActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            loading.dismiss();
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


}