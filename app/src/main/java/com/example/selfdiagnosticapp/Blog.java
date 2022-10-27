package com.example.selfdiagnosticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Blog extends AppCompatActivity {
    ImageButton back_button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        imageView = findViewById(R.id.imageView);
        Picasso.get().load("https://thumbs.dreamstime.com/z/vector-illustration-template-healthcare-poster-abstract-background-icons-medical-health-strategy-care-medicine-global-148971185.jpg").into(imageView);
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