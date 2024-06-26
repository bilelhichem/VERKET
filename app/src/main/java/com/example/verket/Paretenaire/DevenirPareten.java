package com.example.verket.Paretenaire;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import  com.example.verket.Model.partenaire;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.verket.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.UUID;

public class DevenirPareten extends AppCompatActivity {


Button  addpr ;
ImageView uplimage , imagemarque , upcartnat ;
EditText namepart ,prenpart  , telepart , marquepart , descmarque ;

DatabaseReference datadb ;
FirebaseAuth mauth ;

Uri ImageFIle , ImageFileCart , ImageFileMaruqe ;
ProgressDialog loading;
StorageReference imgref ;
    String imagephotopartenaire ,imagecartnational , imagemarquepartrnaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devenir_pareten);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        loading = new ProgressDialog(DevenirPareten.this);
        loading.setTitle("Welcome In our app");
        loading.setMessage("Please wait minute");

        Drawable drawable1 = getResources().getDrawable(R.drawable.bakbtn);
        Drawable drawable2 = getResources().getDrawable(R.drawable.bakbtn2);

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

        ActivityResultLauncher<Intent> openGalleryImagecartnat = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Here, no request code
                            Intent data = result.getData();
                            ImageFileCart = data.getData();
                            upcartnat.setImageURI(ImageFileCart);
                        }
                    }
                });

        ActivityResultLauncher<Intent> openGalleryImagemaruqe = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Here, no request code
                            Intent data = result.getData();
                            ImageFileMaruqe = data.getData();
                            imagemarque.setImageURI(ImageFileMaruqe);
                        }
                    }
                });


        uplimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryImagePr.launch(opengallery());
            }
        });

        upcartnat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryImagecartnat.launch(opengallery());
            }
        });

        imagemarque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryImagemaruqe.launch(opengallery());
            }
        });








        addpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                saveproductindb();
            }
        });
    }

    public Intent opengallery(){
        Intent i  = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        return i;
    }


    public void init(){
        namepart = findViewById(R.id.namepart);
        prenpart = findViewById(R.id.prenparet);

        telepart = findViewById(R.id.telepart);
        marquepart = findViewById(R.id.marqpart);
        descmarque = findViewById(R.id.descrmarque);
        uplimage = findViewById(R.id.uplimage);
        upcartnat = findViewById(R.id.upcartnat);
        imagemarque = findViewById(R.id.imagemarque);
        addpr = findViewById(R.id.addpr);
        mauth = FirebaseAuth.getInstance();
        imgref = FirebaseStorage.getInstance().getReference().child("Partenaire");
        String y = mauth.getCurrentUser().getUid();

        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("partenaire").child(y);

    }

    public static String generateRandomID() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert the UUID to a string
        return uuid.toString();
    }

    public void saveproductindb() {
        String basePath = "Partenaire_" + namepart.getText().toString();
        StorageReference profileImageRef = imgref.child(basePath + "_imageprofil");
        StorageReference brandImageRef = imgref.child(basePath + "_imagemaruqe");
        StorageReference cartImageRef = imgref.child(basePath + "_imagecart");

        Task<Uri> profileImageTask = uploadImage(profileImageRef, ImageFIle);
        Task<Uri> brandImageTask = uploadImage(brandImageRef, ImageFileMaruqe);
        Task<Uri> cartImageTask = uploadImage(cartImageRef, ImageFileCart);

        Tasks.whenAllSuccess(profileImageTask, brandImageTask, cartImageTask)
                .addOnSuccessListener(new OnSuccessListener<List<Object>>() {
                    @Override
                    public void onSuccess(List<Object> results) {
                        String imageProfileUrl = ((Uri) results.get(0)).toString();
                        String imageBrandUrl = ((Uri) results.get(1)).toString();
                        String imageCartUrl = ((Uri) results.get(2)).toString();
                        String id = generateRandomID();

                        save(new partenaire(
                                namepart.getText().toString(),
                                prenpart.getText().toString(),
                                telepart.getText().toString(),
                                marquepart.getText().toString(),
                                descmarque.getText().toString(),
                                imageProfileUrl,
                                imageCartUrl,
                                imageBrandUrl,
                                "0",
                                id
                        ));
                    }
                });
    }

    private Task<Uri> uploadImage(StorageReference ref, Uri file) {
        return ref.putFile(file)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return ref.getDownloadUrl();
                    }
                });
    }



    public void save ( partenaire product){

        datadb.setValue(product)
                .addOnSuccessListener(aVoid -> {
                    loading.dismiss();
                    Toast.makeText(DevenirPareten.this, "Succes", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    loading.dismiss();
                    Toast.makeText(DevenirPareten.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });



    }









}