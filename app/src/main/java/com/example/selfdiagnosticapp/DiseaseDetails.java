package com.example.selfdiagnosticapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DiseaseDetails extends AppCompatActivity {
    TextView topbar_textView,symptom_textView,medicineName_textView;
    ImageButton back_button;
    String symptoms,medicine;

    TextView adultUsage_textView;
    TextView childUsage_textView;
    TextView sideEffects_textView;
    TextView precautions_textView;

    ImageView med_imageView;

    DatabaseReference medicinesRef;
    DatabaseVariables databaseVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);
        topbar_textView = findViewById(R.id.topbar_textView);
        adultUsage_textView = findViewById(R.id.adultUsage_textView);
        childUsage_textView = findViewById(R.id.childUsage_textView);
        sideEffects_textView = findViewById(R.id.sideEffects_textView);
        precautions_textView = findViewById(R.id.precautions_textView);

        med_imageView = findViewById(R.id.med_imageView);

        symptom_textView = findViewById(R.id.symptom_textView);
        medicineName_textView = findViewById(R.id.medicineName_textView);

        topbar_textView.setText(getIntent().getStringExtra("DISEASE_NAME").toString());
        medicine =getIntent().getStringExtra("DISEASE_MED").toString();
        symptoms =getIntent().getStringExtra("DISEASE_SYMPTOMS").toString();

        symptom_textView.setText(getIntent().getStringExtra("DISEASE_SYMPTOMS").toString());
        medicineName_textView.setText(medicine);

        databaseVariables = new DatabaseVariables();
        medicinesRef = FirebaseDatabase.getInstance().getReference().child("Medicines").child(medicine);

        medicinesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(String.valueOf(snapshot));
                adultUsage_textView.setText(snapshot.child(databaseVariables.getMedicinesAdultUsageVar()).getValue().toString());
                childUsage_textView.setText(snapshot.child(databaseVariables.getMedicinesChildrenUsageVar()).getValue().toString());
                sideEffects_textView.setText(snapshot.child(databaseVariables.getMedicinesPrecautionsVar()).getValue().toString());
                precautions_textView.setText(snapshot.child(databaseVariables.getMedicinesSideEffectsVar()).getValue().toString());
                Picasso.get().load(snapshot.child(databaseVariables.getMedicinesImageVar()).getValue().toString()).into(med_imageView);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton();
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