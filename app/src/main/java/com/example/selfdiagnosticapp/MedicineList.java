package com.example.selfdiagnosticapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MedicineList extends AppCompatActivity {
    DatabaseVariables databaseVariables;
    DatabaseReference medicinesRef;
    ImageButton back_button;

    RecyclerView medList_recyclerView;
    AdapterMedicineListRecyclerView adapterMedicineListRecyclerView;

    List<String> medAdultUsage, medChildUsage, medDisease, medImage, medName, medPrecautions, medSideEffects;
    List<String> _medAdultUsage, _medChildUsage, _medDisease, _medImage, _medName, _medPrecautions, _medSideEffects;

    EditText search_bar_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        medAdultUsage = new ArrayList<>();
        medChildUsage = new ArrayList<>();
        medDisease = new ArrayList<>();
        medImage = new ArrayList<>();
        medName = new ArrayList<>();
        medPrecautions = new ArrayList<>();
        medSideEffects = new ArrayList<>();

        _medAdultUsage = new ArrayList<>();
        _medChildUsage = new ArrayList<>();
        _medDisease = new ArrayList<>();
        _medImage = new ArrayList<>();
        _medName = new ArrayList<>();
        _medPrecautions = new ArrayList<>();
        _medSideEffects = new ArrayList<>();

        search_bar_editText = findViewById(R.id.search_bar_editText);

        medList_recyclerView = findViewById(R.id.medList_recyclerView);
        medList_recyclerView.setLayoutManager(new GridLayoutManager(MedicineList.this, 1));

        databaseVariables = new DatabaseVariables();
        medicinesRef = FirebaseDatabase.getInstance().getReference(databaseVariables.getMedicinesRef());

        medicinesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    medAdultUsage.add(snapshot1.child(databaseVariables.getMedicinesAdultUsageVar()).getValue().toString());
                    medChildUsage.add(snapshot1.child(databaseVariables.getMedicinesChildrenUsageVar()).getValue().toString());
                    medDisease.add(snapshot1.child(databaseVariables.getMedicinesDiseaseVar()).getValue().toString());
                    medImage.add(snapshot1.child(databaseVariables.getMedicinesImageVar()).getValue().toString());
                    medName.add(snapshot1.child(databaseVariables.getMedicinesNameVar()).getValue().toString());
                    medPrecautions.add(snapshot1.child(databaseVariables.getMedicinesPrecautionsVar()).getValue().toString());
                    medSideEffects.add(snapshot1.child(databaseVariables.getMedicinesSideEffectsVar()).getValue().toString());

                    _medAdultUsage.add(snapshot1.child(databaseVariables.getMedicinesAdultUsageVar()).getValue().toString());
                    _medChildUsage.add(snapshot1.child(databaseVariables.getMedicinesChildrenUsageVar()).getValue().toString());
                    _medDisease.add(snapshot1.child(databaseVariables.getMedicinesDiseaseVar()).getValue().toString());
                    _medImage.add(snapshot1.child(databaseVariables.getMedicinesImageVar()).getValue().toString());
                    _medName.add(snapshot1.child(databaseVariables.getMedicinesNameVar()).getValue().toString());
                    _medPrecautions.add(snapshot1.child(databaseVariables.getMedicinesPrecautionsVar()).getValue().toString());
                    _medSideEffects.add(snapshot1.child(databaseVariables.getMedicinesSideEffectsVar()).getValue().toString());
                }
                adapterMedicineListRecyclerView = new AdapterMedicineListRecyclerView(MedicineList.this, medAdultUsage, medChildUsage, medDisease, medImage, medName, medPrecautions, medSideEffects);
                medList_recyclerView.setAdapter(adapterMedicineListRecyclerView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton();
        searchBar();

    }

    private void searchBar() {
        search_bar_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                medAdultUsage.clear();
                medChildUsage.clear();
                medDisease.clear();
                medImage.clear();
                medName.clear();
                medPrecautions.clear();
                medSideEffects.clear();

                if (search_bar_editText.getText().toString().isEmpty()) {
                    for (int i = 0; i < _medName.size(); i++) {
                        medAdultUsage.add(_medAdultUsage.get(i));
                        medChildUsage.add(_medChildUsage.get(i));
                        medDisease.add(_medDisease.get(i));
                        medImage.add(_medImage.get(i));
                        medName.add(_medName.get(i));
                        medPrecautions.add(_medPrecautions.get(i));
                        medSideEffects.add(_medSideEffects.get(i));

                    }
                    adapterMedicineListRecyclerView.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < _medName.size(); i++) {
                        if (StringUtils.containsIgnoreCase(_medName.get(i), search_bar_editText.getText().toString()) || StringUtils.containsIgnoreCase(_medDisease.get(i), search_bar_editText.getText().toString())) {
                            medAdultUsage.add(_medAdultUsage.get(i));
                            medChildUsage.add(_medChildUsage.get(i));
                            medDisease.add(_medDisease.get(i));
                            medImage.add(_medImage.get(i));
                            medName.add(_medName.get(i));
                            medPrecautions.add(_medPrecautions.get(i));
                            medSideEffects.add(_medSideEffects.get(i));
                        }
                    }
                    adapterMedicineListRecyclerView.notifyDataSetChanged();
                }
            }
        });
    }

    private void backButton() {
        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}