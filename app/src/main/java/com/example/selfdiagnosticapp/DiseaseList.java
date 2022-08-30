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

public class DiseaseList extends AppCompatActivity {

    DatabaseVariables databaseVariables;
    DatabaseReference diseaseRef;
    DatabaseReference medicinesRef;
    ImageButton back_button;

    RecyclerView diseaseList_recyclerView;
    AdapterDiseaseListRecyclerView adapterDiseaseListRecyclerView;

    List<String> diseaseName, diseaseMed, diseaseSymptoms;
    List<String> _diseaseName, _diseaseMed, _diseaseSymptoms;

    EditText search_bar_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_list);

        diseaseName = new ArrayList<>();
        diseaseMed = new ArrayList<>();
        diseaseSymptoms = new ArrayList<>();

        _diseaseName = new ArrayList<>();
        _diseaseMed = new ArrayList<>();
        _diseaseSymptoms = new ArrayList<>();

        search_bar_editText = findViewById(R.id.search_bar_editText);

        diseaseList_recyclerView = findViewById(R.id.diseaseList_recyclerView);
        diseaseList_recyclerView.setLayoutManager(new GridLayoutManager(DiseaseList.this, 1));

        databaseVariables = new DatabaseVariables();
        diseaseRef = FirebaseDatabase.getInstance().getReference(databaseVariables.getDiseasesRef());
        medicinesRef = FirebaseDatabase.getInstance().getReference(databaseVariables.getMedicinesRef());

        diseaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    diseaseName.add(snapshot1.child(databaseVariables.getDiseaseNameVar()).getValue().toString());
                    diseaseMed.add(snapshot1.child(databaseVariables.getDiseaseMedicineVar()).getValue().toString());
                    diseaseSymptoms.add(snapshot1.child(databaseVariables.getDiseaseSymptomsVar()).getValue().toString());

                    _diseaseName.add(snapshot1.child(databaseVariables.getDiseaseNameVar()).getValue().toString());
                    _diseaseMed.add(snapshot1.child(databaseVariables.getDiseaseMedicineVar()).getValue().toString());
                    _diseaseSymptoms.add(snapshot1.child(databaseVariables.getDiseaseSymptomsVar()).getValue().toString());

                    adapterDiseaseListRecyclerView = new AdapterDiseaseListRecyclerView(DiseaseList.this, diseaseName, diseaseMed, diseaseSymptoms);
                    diseaseList_recyclerView.setAdapter(adapterDiseaseListRecyclerView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchBar();
        backButton();
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
                diseaseName.clear();
                diseaseMed.clear();
                diseaseSymptoms.clear();


                if (search_bar_editText.getText().toString().isEmpty()) {
                    for (int i = 0; i < _diseaseName.size(); i++) {
                        diseaseName.add(_diseaseName.get(i));
                        diseaseMed.add(_diseaseMed.get(i));
                        diseaseSymptoms.add(_diseaseSymptoms.get(i));
                    }
                    adapterDiseaseListRecyclerView.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < _diseaseName.size(); i++) {
                        if (StringUtils.containsIgnoreCase(_diseaseName.get(i), search_bar_editText.getText().toString()) || StringUtils.containsIgnoreCase(_diseaseMed.get(i), search_bar_editText.getText().toString())) {
                            diseaseName.add(_diseaseName.get(i));
                            diseaseMed.add(_diseaseMed.get(i));
                            diseaseSymptoms.add(_diseaseSymptoms.get(i));

                        }
                    }
                    adapterDiseaseListRecyclerView.notifyDataSetChanged();
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