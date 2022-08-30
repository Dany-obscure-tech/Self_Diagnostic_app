package com.example.selfdiagnosticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MedicineDetails extends AppCompatActivity {
    TextView topbar_textView;
    TextView adultUsage_textView;
    TextView childUsage_textView;
    TextView sideEffects_textView;
    TextView precautions_textView;
    ImageButton back_button;

    ImageView med_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        topbar_textView = findViewById(R.id.topbar_textView);
        med_imageView = findViewById(R.id.med_imageView);
        adultUsage_textView = findViewById(R.id.adultUsage_textView);
        childUsage_textView = findViewById(R.id.childUsage_textView);
        sideEffects_textView = findViewById(R.id.sideEffects_textView);
        precautions_textView = findViewById(R.id.precautions_textView);

        topbar_textView.setText(getIntent().getStringExtra("MEDICINE_NAME").toString());
        Picasso.get().load(getIntent().getStringExtra("MEDICINE_IMAGE").toString()).into(med_imageView);
        adultUsage_textView.setText(getIntent().getStringExtra("MEDICINE_ADULT_USAGE").toString());
        childUsage_textView.setText(getIntent().getStringExtra("MEDICINE_CHILD_USAGE").toString());
        sideEffects_textView.setText(getIntent().getStringExtra("MEDICINE_SIDE_EFFECTS").toString());
        precautions_textView.setText(getIntent().getStringExtra("MEDICINE_PRECAUTIONS").toString());
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