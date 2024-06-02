package com.example.verket.Paretenaire;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.example.verket.Model.ProduitModel;
import com.example.verket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
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

import java.util.HashMap;
import java.util.Map;

public class UpdateProduct extends AppCompatActivity {

    EditText name, description, prix, quantite, codepromo, pourcentage, dateprimier;
    ImageView uplimage;
    String item;

    String yes;
    Uri ImageFIle;
    private TextInputLayout textInputLayout;
    private AutoCompleteTextView editTextCategory;
    Button registervend;

    ProgressDialog loading;
    DatabaseReference datadb;
    StorageReference imgref;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        item = getIntent().getStringExtra("itemId");
        init();

        loading = new ProgressDialog(UpdateProduct.this);
        loading.setTitle("Welcome In our app");
        loading.setMessage("Please wait a minute");
        ActivityResultLauncher<Intent> openGalleryImagePr = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Here, no request code
                            Intent data = result.getData();
                            ImageFIle = data.getData();
                            uplimage.setImageURI(ImageFIle);
                        }
                    }
                });
        uplimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryImagePr.launch(opengallery());
            }
        });
        textInputLayout = findViewById(R.id.textInputLayout);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryOptions();
            }
        });
        lire();

        registervend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveproductindb();
            }
        });
    }

    public void init() {
        mauth = FirebaseAuth.getInstance();
        name = findViewById(R.id.upnmpr);
        uplimage = findViewById(R.id.updateimage);
        description = findViewById(R.id.updcpr);
        prix = findViewById(R.id.upprpr);
        quantite = findViewById(R.id.upqntpr);
        codepromo = findViewById(R.id.upcodepr);
        pourcentage = findViewById(R.id.uppourcentagepr);
        dateprimier = findViewById(R.id.updatepr);
        registervend = findViewById(R.id.confirmeupdate);
        imgref = FirebaseStorage.getInstance().getReference().child("ProduitPartenaire");
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("ProduitPartenaire").child(userid);
    }

    public Intent opengallery() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        return i;
    }

    public void lire() {
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.child("id").exists() && dataSnapshot.child("id").getValue(String.class).equals(item)) {
                        yes = dataSnapshot.getKey().toString();

                        name.setText(dataSnapshot.child("nameproduit").getValue().toString());
                        Picasso.get().load(dataSnapshot.child("imageproduit").getValue().toString()).into(uplimage);
                        description.setText(dataSnapshot.child("descriptionproduit").getValue().toString());
                        prix.setText(dataSnapshot.child("prixproduit").getValue().toString());
                        quantite.setText(dataSnapshot.child("quantiteproduit").getValue().toString());
                        codepromo.setText(dataSnapshot.child("codepromo").getValue().toString());
                        pourcentage.setText(dataSnapshot.child("pourcentage").getValue().toString());
                        dateprimier.setText(dataSnapshot.child("date_pirime").getValue().toString());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    private void showCategoryOptions() {
        String[] categories = {"Bio", "COSMETIC", "DETERGENT", "ARTISANAT"};
        ListPopupWindow listPopupWindow = new ListPopupWindow(UpdateProduct.this);
        listPopupWindow.setAnchorView(textInputLayout);
        listPopupWindow.setAdapter(new ArrayAdapter<>(UpdateProduct.this, android.R.layout.simple_list_item_1, categories));
        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            editTextCategory.setText(categories[position]);
            listPopupWindow.dismiss();
        });
        listPopupWindow.show();
    }

    public void saveproductindb() {
        if (ImageFIle != null) {
            loading.show();
            imgref.child(mauth.getCurrentUser().getUid() + name.getText().toString())
                    .putFile(ImageFIle)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                imgref.child(mauth.getCurrentUser().getUid() + name.getText().toString())
                                        .getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                String y = uri.toString();
                                                String id = item;
                                                save(new ProduitModel(
                                                        id,
                                                        y,
                                                        name.getText().toString(),
                                                        description.getText().toString(),
                                                        prix.getText().toString(),
                                                        quantite.getText().toString(),
                                                        editTextCategory.getText().toString(),
                                                        codepromo.getText().toString(),
                                                        pourcentage.getText().toString(),
                                                        dateprimier.getText().toString()
                                                ));
                                            }
                                        });
                            } else {
                                loading.dismiss();
                                Toast.makeText(UpdateProduct.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(UpdateProduct.this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }

    public void save(ProduitModel user) {
        Map<String, Object> productUpdates = new HashMap<>();
        productUpdates.put("id", user.getId());
        productUpdates.put("imageproduit", user.getImageproduit());
        productUpdates.put("nameproduit", user.getNameproduit());
        productUpdates.put("descriptionproduit", user.getDescriptionproduit());
        productUpdates.put("prixproduit", user.getPrixproduit());
        productUpdates.put("quantiteproduit", user.getQuantiteproduit());
        productUpdates.put("category", user.getCategorieproduit());
        productUpdates.put("codepromo", user.getCodepromo());
        productUpdates.put("pourcentage", user.getPourcentage());
        productUpdates.put("date_pirime", user.getDate_pirime());

        datadb.child(yes).updateChildren(productUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    loading.dismiss();
                    Toast.makeText(UpdateProduct.this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    loading.dismiss();
                    Toast.makeText(UpdateProduct.this, "Failed to update product", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
