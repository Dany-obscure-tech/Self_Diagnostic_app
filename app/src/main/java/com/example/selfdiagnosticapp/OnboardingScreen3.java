package com.example.selfdiagnosticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OnboardingScreen3 extends AppCompatActivity {

    TextView next_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen3);

        next_textView = findViewById(R.id.next_textView);

        next_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OnboardingScreen3.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}