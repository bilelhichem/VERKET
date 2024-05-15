package com.example.verket.Paretenaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListPopupWindow;

import com.example.verket.R;
import com.google.android.material.textfield.TextInputLayout;

public class addProduct extends AppCompatActivity {
    private TextInputLayout textInputLayout;
    private AutoCompleteTextView editTextCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textInputLayout = findViewById(R.id.textInputLayout);
        editTextCategory = findViewById(R.id.editTextCategory);

        editTextCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryOptions();
            }
        });
    }

    private void showCategoryOptions() {
        String[] categories = {"Category 1", "Category 2", "Category 3"};

        ListPopupWindow listPopupWindow = new ListPopupWindow(addProduct.this);
        listPopupWindow.setAnchorView(textInputLayout);
        listPopupWindow.setAdapter(new ArrayAdapter<>(addProduct.this, android.R.layout.simple_list_item_1, categories));
        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            editTextCategory.setText(categories[position]);
            listPopupWindow.dismiss();
        });
        listPopupWindow.show();
    }
}