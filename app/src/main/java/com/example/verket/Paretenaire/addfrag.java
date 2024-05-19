package com.example.verket.Paretenaire;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListPopupWindow;

import com.example.verket.R;
import com.google.android.material.textfield.TextInputLayout;

public class addfrag extends Fragment {

    private TextInputLayout textInputLayout;
    private AutoCompleteTextView editTextCategory;
    View view ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addfrag, container, false) ;
        textInputLayout = view.findViewById(R.id.textInputLayout);
        editTextCategory =view.findViewById(R.id.editTextCategory);

        editTextCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryOptions();
            }
        });
        return view;
    }


    private void showCategoryOptions() {
        String[] categories = {"Category 1", "Category 2", "Category 3"};

        ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
        listPopupWindow.setAnchorView(textInputLayout);
        listPopupWindow.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categories));
        listPopupWindow.setOnItemClickListener((parent, view, position, id) -> {
            editTextCategory.setText(categories[position]);
            listPopupWindow.dismiss();
        });
        listPopupWindow.show();
    }
}