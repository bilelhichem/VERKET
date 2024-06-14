package com.example.verket.Paretenaire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Switch;
import android.widget.Toast;

import com.example.verket.HomeActivity;
import com.example.verket.Model.ProduitModel;
import com.example.verket.Model.UserModel;
import com.example.verket.Model.partenaire;
import com.example.verket.R;
import com.example.verket.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class addfrag extends Fragment {
    ImageView uplimage ;
    EditText  name , description , prix , quantite  , codepromo , pourcentage , dateprimier ,pourcentagedecodepromo;
    private TextInputLayout textInputLayout;
    private AutoCompleteTextView editTextCategory;
    Button registervend ;
    View view ;
    ProgressDialog loading;
    DatabaseReference datadb ;
    StorageReference imgref ;
    FirebaseAuth mauth ;
    Uri ImageFIle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addfrag, container, false) ;
        init();
        Switch switch1 = view.findViewById(R.id.switch1);
        Switch switch2 = view.findViewById(R.id.switch2);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                  codepromo.setVisibility(View.VISIBLE);
                    pourcentagedecodepromo.setVisibility(View.VISIBLE);
                }else if (!isChecked){
                    codepromo.setVisibility(View.GONE);
                    pourcentagedecodepromo.setVisibility(View.GONE);
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pourcentage.setVisibility(View.VISIBLE);
                } else if (!isChecked){
                    pourcentage.setVisibility(View.GONE);
                }
            }
        });
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
        textInputLayout = view.findViewById(R.id.textInputLayout);
        editTextCategory =view.findViewById(R.id.editTextCategory);
        loading = new ProgressDialog(getContext());
        loading.setTitle("Welcome In our app");
        loading.setMessage("Please wait minute");

        editTextCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryOptions();
            }
        });

        uplimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryImagePr.launch(opengallery());
            }
        });

        registervend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pourcentage.getText().toString().isEmpty()) {
                    int pourcentageValue = Integer.valueOf(pourcentage.getText().toString());
                    if (pourcentageValue > 100 || pourcentageValue < 0) {
                        pourcentage.setError("Veuillez entrer un nombre inférieur à 100 et supérieur à 0");
                    }
                } else  if (name.getText().toString().isEmpty()){
                    name.setError("Veuillez entrer name pour produit");
                } else  if (description.getText().toString().isEmpty()){
                    description.setError("Veuillez entrer un description pour le produit");
                } else  if (quantite.getText().toString().isEmpty()){
                    quantite.setError("Veuillez entrer un quantite pour le produit");
                }else  if (dateprimier.getText().toString().isEmpty()){
                    dateprimier.setError("Veuillez entrer un date expiration pour le produit");
                }else{
                    saveproductindb();
                }

            }
        });






        return view;
    }


    private void showCategoryOptions() {
        String[] categories = {"BIO", "COSMETIC", "DETERGENT","ARTISANAT"};
        ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
        listPopupWindow.setAnchorView(textInputLayout);
        listPopupWindow.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categories));
        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            editTextCategory.setText(categories[position]);
            listPopupWindow.dismiss();
        });
        listPopupWindow.show();
    }

    public void init(){
        mauth = FirebaseAuth.getInstance();
        name = view.findViewById(R.id.nmpr);
        uplimage=view.findViewById(R.id.uplimage);
        description = view.findViewById(R.id.dcpr);
        prix = view.findViewById(R.id.prpr);
        quantite = view.findViewById(R.id.qntpr);
        codepromo = view.findViewById(R.id.codepr);
        pourcentage = view.findViewById(R.id.pourcentagepr);
        dateprimier = view.findViewById(R.id.datepr);
        pourcentagedecodepromo = view.findViewById(R.id.pourcentagedecodepromo);
        registervend = view.findViewById(R.id.registervend);
        imgref = FirebaseStorage.getInstance().getReference().child("ProduitPartenaire");
        datadb = FirebaseDatabase.getInstance(getString(R.string.db_url)).getReference().child("ProduitPartenaire");

    }

    public Intent opengallery(){
        Intent i  = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        return i;
    }
    public static String generateRandomID() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert the UUID to a string
        return uuid.toString();
    }

    public  void saveproductindb(){

        imgref.child(mauth.getCurrentUser().toString() + name.getText().toString())
                .putFile(ImageFIle)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){

                            imgref.child(mauth.getCurrentUser().toString()+name.getText().toString())
                                    .getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String y= uri.toString() ;
                                            String id = generateRandomID();
                                            saveuserindatabase(new ProduitModel(
                                            id,
                                            y,
                                           name.getText().toString(),
                                            description.getText().toString(),
                                            prix.getText().toString(),
                                            quantite.getText().toString(),
                                            editTextCategory.getText().toString(),
                                            codepromo.getText().toString(),
                                            pourcentagedecodepromo.getText().toString(),
                                            pourcentage.getText().toString(),
                                            dateprimier.getText().toString()
                                            ));


                                        }
                                    });
                        }

                    }
                });

    }




    public void saveuserindatabase(ProduitModel user){

        datadb.child(mauth.getCurrentUser().getUid())
                .push()
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(getContext(), "Produit ajouter", Toast.LENGTH_SHORT).show();

                        }else {
                            loading.dismiss();
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

}