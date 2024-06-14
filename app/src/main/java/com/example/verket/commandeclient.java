package com.example.verket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verket.Model.ProduitModel;
import com.example.verket.Model.commande;
import com.example.verket.adapter.Pourcentageadapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class commandeclient extends AppCompatActivity {
    String item ;
    ImageView coomandeimage ;
    TextView namecommande , desccommande , priccommande , numberdequantitedecommande , pricetotal , moins , plus ;
    DatabaseReference datadb , db;
    EditText codepromoclient ;
    StorageReference imgref ;
    String qnt , codepromo,poucentagedecodepromo , ImageFile,numerodetele;
    Integer pric  ;
    Integer nvprice = 0;
    Button shop ;
    FirebaseAuth mauth ;

    ArrayList<ProduitModel> prood ;
    Integer comteur = 0 ;
    Boolean cmp  = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandeclient);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        item = getIntent().getStringExtra("item");
        UplaodToDb();

         codepromoclient.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 pricetotal.setText(String.valueOf(nvprice));
             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 if (charSequence.toString().equals(codepromo)) {
                     Integer y = Integer.valueOf(poucentagedecodepromo);
                     Integer  bb = nvprice ;
                     bb = (nvprice * y) / 100 ;
                     pricetotal.setText(String.valueOf(bb));

                 }else {
                     pricetotal.setText(String.valueOf(nvprice));
                 }

             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });






        moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comteur > 0){
                    comteur-=1;
                    nvprice -=pric;


                    pricetotal.setText(String.valueOf(nvprice));
                }
                numberdequantitedecommande.setText(String.valueOf(comteur));

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer y = Integer.valueOf(qnt);

                if (comteur< y){
                    comteur+=1;

                    nvprice +=pric;

                    pricetotal.setText(String.valueOf(nvprice));
                }




                numberdequantitedecommande.setText(String.valueOf(comteur));


            }
        });


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhoneInputDialog();

            }
        });

    }


    public void init() {
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("ProduitPartenaire");
        coomandeimage = findViewById(R.id.coomandeimage);
        mauth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("CommandeClient");
        imgref = FirebaseStorage.getInstance().getReference().child("CommandeClient");
        namecommande = findViewById(R.id.namecommande);
        desccommande = findViewById(R.id.desccommande);
        priccommande = findViewById(R.id.priccommande);
        numberdequantitedecommande = findViewById(R.id.numberdequantitedecommande);
        pricetotal = findViewById(R.id.pricetotal);
        moins = findViewById(R.id.moins);
        plus = findViewById(R.id.plus);
        shop = findViewById(R.id.shop);
        codepromoclient = findViewById(R.id.codepromoclient);

    }

    public void UplaodToDb(){
        prood = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prood.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot da : dataSnapshot.getChildren()){
                        if (da.child("id").getValue().toString().equals(item)){
                            if (!da.child("pourcentage").getValue().toString().isEmpty()){
                                int prix = Integer.parseInt(da.child("prixproduit").getValue().toString());
                                int percentage = Integer.parseInt(da.child("pourcentage").getValue().toString());

                                int discountedPrice = (prix * percentage) / 100;
                                pric = Integer.valueOf(discountedPrice);
                                priccommande.setText(String.valueOf(discountedPrice));

                            }else {
                                priccommande.setText(da.child("prixproduit").getValue().toString());
                                pric = Integer.valueOf(da.child("prixproduit").getValue().toString());
                            }
                            qnt = da.child("quantiteproduit").getValue().toString();
                            Picasso.get().load(da.child("imageproduit").getValue().toString()).into(coomandeimage);
                            namecommande.setText(da.child("nameproduit").getValue().toString());
                            desccommande.setText(da.child( "descriptionproduit").getValue().toString());

                            ImageFile = da.child("imageproduit").getValue().toString();

                            codepromo = da.child("codepromo").getValue().toString();
                            poucentagedecodepromo = da.child("pourcentagedecodepromo").getValue().toString();

                            cmp = true ;
                            break;
                        }
                    }
                    if (cmp == true){
                        break;
                    }
                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }









    public void saveuserindatabase(commande user){

        db.child(mauth.getCurrentUser().getUid())
                .push()
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(commandeclient.this, "Produit ajouter", Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(commandeclient.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    private void showPhoneInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set title and padding
        builder.setTitle("Enter Phone Number");
        int padding = getResources().getDimensionPixelSize(R.dimen.dialog_padding);
        EditText input = getPaddedEditText(padding); // Get padded EditText view
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Commander", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 numerodetele = input.getText().toString().trim();
                saveuserindatabase(
                        new commande(
                                mauth.getCurrentUser().getEmail().toString(),
                                numerodetele,
                               ImageFile, // Use the download URL
                             String.valueOf(comteur),
                              String.valueOf(nvprice)
                        )
                );
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Set dialog window background drawable

    }

    // Method to create and return padded EditText view
    private EditText getPaddedEditText(int padding) {
        EditText input = new EditText(this);
        input.setBackground(ContextCompat.getDrawable(this, R.drawable.edit_text_background)); // Ensure background drawable is set correctly
        input.setHint("Phone Number");
        input.setPadding(padding, padding, padding, padding); // Apply padding to EditText
        return input;
    }

}