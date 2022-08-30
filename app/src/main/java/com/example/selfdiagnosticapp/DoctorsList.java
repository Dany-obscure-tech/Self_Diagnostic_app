package com.example.selfdiagnosticapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorsList extends AppCompatActivity {
    DatabaseVariables databaseVariables;
    DatabaseReference doctorsRef;
    ImageButton back_button;


    RecyclerView docList_recyclerView;
    AdapterDoctorListRecyclerView adapterDoctorListRecyclerView;

    List<String> docName, docImage, specialist,contactNo,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        address =new ArrayList<>();
        contactNo =new ArrayList<>();
        docImage =new ArrayList<>();
        docName =new ArrayList<>();
        specialist =new ArrayList<>();

        docList_recyclerView = findViewById(R.id.docList_recyclerView);
        docList_recyclerView.setLayoutManager(new GridLayoutManager(DoctorsList.this, 1));

        databaseVariables = new DatabaseVariables();
        doctorsRef = FirebaseDatabase.getInstance().getReference(databaseVariables.getDoctorsRef());

        doctorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    address.add(snapshot1.child(databaseVariables.getDoctorsAddressVar()).getValue().toString());
                    contactNo.add(snapshot1.child(databaseVariables.getDoctorsContactVar()).getValue().toString());
                    docImage.add(snapshot1.child(databaseVariables.getDoctorsImageVar()).getValue().toString());
                    docName.add(snapshot1.child(databaseVariables.getDoctorsNameVar()).getValue().toString());
                    specialist.add(snapshot1.child(databaseVariables.getDoctorsSpecialistVar()).getValue().toString());

                }
                adapterDoctorListRecyclerView = new AdapterDoctorListRecyclerView(DoctorsList.this, docName, docImage, specialist,contactNo,address);
                docList_recyclerView.setAdapter(adapterDoctorListRecyclerView);
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