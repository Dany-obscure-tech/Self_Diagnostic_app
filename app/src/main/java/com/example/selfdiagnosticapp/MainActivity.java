package com.example.selfdiagnosticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView menu_imageView;
    RelativeLayout menu;
    RelativeLayout menu_bg;
    RelativeLayout medicine_relativeLayout,disease_relativeLayout;

    TextView aboutUs_textView;
    TextView contactUs_textView;
    TextView blogs_textView;
    TextView doctors_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        menu_imageView = findViewById(R.id.menu_imageView);
        menu = findViewById(R.id.menu);
        menu_bg = findViewById(R.id.menu_bg);
        aboutUs_textView = findViewById(R.id.aboutUs_textView);
        contactUs_textView = findViewById(R.id.contactUs_textView);
        blogs_textView = findViewById(R.id.blogs_textView);
        doctors_textView = findViewById(R.id.doctors_textView);

        medicine_relativeLayout = findViewById(R.id.medicine_relativeLayout);
        disease_relativeLayout = findViewById(R.id.disease_relativeLayout);

        SliderView sliderView = findViewById(R.id.imageSlider);
        List<String> sliderimage = new ArrayList<>();

        sliderimage.add("https://miro.medium.com/max/1400/1*R8xPe4JHCtjxxPaUMA1MAw.png");
        sliderimage.add("https://miro.medium.com/max/1400/1*R8xPe4JHCtjxxPaUMA1MAw.png");

        SliderAdapter adapter = new SliderAdapter(this, sliderimage);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        menu_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menu_bg.setVisibility(View.VISIBLE);
                menu.setVisibility(View.VISIBLE);

            }
        });

        menu_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_bg.setVisibility(View.GONE);
                menu.setVisibility(View.GONE);
            }
        });


        doctors_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoctorsList.class);
                startActivity(intent);
            }
        });

        aboutUs_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        contactUs_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });

        blogs_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Blog.class);
                startActivity(intent);
            }
        });

        medicine_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormActivity.class);
                intent.putExtra("ACTIVITY","MED");
                startActivity(intent);
            }
        });

        disease_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormActivity.class);
                intent.putExtra("ACTIVITY","DIS");
                startActivity(intent);
            }
        });


    }
}