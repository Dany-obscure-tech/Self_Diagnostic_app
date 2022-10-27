package com.example.selfdiagnosticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    DatabaseReference formRef;

    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String answer5;
    String answer6;
    String answer7;
    String answer8;
    String answer9;
    String answer10;

    ImageButton back_button;

    Button submit_button;

    RadioButton q1yes_radioButton;
    RadioButton q2yes_radioButton;
    RadioButton q3yes_radioButton;
    RadioButton q4yes_radioButton;
    RadioButton q5yes_radioButton;
    RadioButton q6yes_radioButton;
    RadioButton q7yes_radioButton;
    RadioButton q8yes_radioButton;
    RadioButton q9yes_radioButton;
    RadioButton q10yes_radioButton;

    RadioButton q1no_radioButton;
    RadioButton q2no_radioButton;
    RadioButton q3no_radioButton;
    RadioButton q4no_radioButton;
    RadioButton q5no_radioButton;
    RadioButton q6no_radioButton;
    RadioButton q7no_radioButton;
    RadioButton q8no_radioButton;
    RadioButton q9no_radioButton;
    RadioButton q10no_radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        formRef = FirebaseDatabase.getInstance().getReference("Form_Response");

        submit_button = findViewById(R.id.submit_button);

        q1yes_radioButton = findViewById(R.id.q1yes_radioButton);
        q2yes_radioButton = findViewById(R.id.q2yes_radioButton);
        q3yes_radioButton = findViewById(R.id.q3yes_radioButton);
        q4yes_radioButton = findViewById(R.id.q4yes_radioButton);
        q5yes_radioButton = findViewById(R.id.q5yes_radioButton);
        q6yes_radioButton = findViewById(R.id.q6yes_radioButton);
        q7yes_radioButton = findViewById(R.id.q7yes_radioButton);
        q8yes_radioButton = findViewById(R.id.q8yes_radioButton);
        q9yes_radioButton = findViewById(R.id.q9yes_radioButton);
        q10yes_radioButton = findViewById(R.id.q10yes_radioButton);

        q1no_radioButton = findViewById(R.id.q1no_radioButton);
        q2no_radioButton = findViewById(R.id.q2no_radioButton);
        q3no_radioButton = findViewById(R.id.q3no_radioButton);
        q4no_radioButton = findViewById(R.id.q4no_radioButton);
        q5no_radioButton = findViewById(R.id.q5no_radioButton);
        q6no_radioButton = findViewById(R.id.q6no_radioButton);
        q7no_radioButton = findViewById(R.id.q7no_radioButton);
        q8no_radioButton = findViewById(R.id.q8no_radioButton);
        q9no_radioButton = findViewById(R.id.q9no_radioButton);
        q10no_radioButton = findViewById(R.id.q10no_radioButton);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){

                    if (q1yes_radioButton.isChecked()){answer1="yes";}
                    if (q2yes_radioButton.isChecked()){answer2="yes";}
                    if (q3yes_radioButton.isChecked()){answer3="yes";}
                    if (q4yes_radioButton.isChecked()){answer4="yes";}
                    if (q5yes_radioButton.isChecked()){answer5="yes";}
                    if (q6yes_radioButton.isChecked()){answer6="yes";}
                    if (q7yes_radioButton.isChecked()){answer7="yes";}
                    if (q8yes_radioButton.isChecked()){answer8="yes";}
                    if (q9yes_radioButton.isChecked()){answer9="yes";}
                    if (q10yes_radioButton.isChecked()){answer10="yes";}

                    if (q1no_radioButton.isChecked()){answer1="no";}
                    if (q2no_radioButton.isChecked()){answer2="no";}
                    if (q3no_radioButton.isChecked()){answer3="no";}
                    if (q4no_radioButton.isChecked()){answer4="no";}
                    if (q5no_radioButton.isChecked()){answer5="no";}
                    if (q6no_radioButton.isChecked()){answer6="no";}
                    if (q7no_radioButton.isChecked()){answer7="no";}
                    if (q8no_radioButton.isChecked()){answer8="no";}
                    if (q9no_radioButton.isChecked()){answer9="no";}
                    if (q10no_radioButton.isChecked()){answer10="no";}

                    String pushId = formRef.push().getKey();
                    formRef.child(pushId).child("Question1").setValue(answer1);
                    formRef.child(pushId).child("Question2").setValue(answer2);
                    formRef.child(pushId).child("Question3").setValue(answer3);
                    formRef.child(pushId).child("Question4").setValue(answer4);
                    formRef.child(pushId).child("Question5").setValue(answer5);
                    formRef.child(pushId).child("Question6").setValue(answer6);
                    formRef.child(pushId).child("Question7").setValue(answer7);
                    formRef.child(pushId).child("Question8").setValue(answer8);
                    formRef.child(pushId).child("Question9").setValue(answer9);
                    formRef.child(pushId).child("Question10").setValue(answer10);

                    String check = getIntent().getStringExtra("ACTIVITY").toString();

                    if (check.equals("DIS")){
                        Intent intent = new Intent(FormActivity.this,DiseaseList.class);
                        finish();
                        startActivity(intent);
                    }
                    else if (check.equals("MED")){
                        Intent intent = new Intent(FormActivity.this,MedicineList.class);
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });

        backButton();
    }

    private Boolean validate() {
        Boolean valid = true;
        if (!q1yes_radioButton.isChecked()&&!q1no_radioButton.isChecked()){
            valid=false;
        }
        if (!q2yes_radioButton.isChecked()&&!q2no_radioButton.isChecked()){
            valid=false;
        }
        if (!q3yes_radioButton.isChecked()&&!q3no_radioButton.isChecked()){
            valid=false;
        }
        if (!q4yes_radioButton.isChecked()&&!q4no_radioButton.isChecked()){
            valid=false;
        }
        if (!q5yes_radioButton.isChecked()&&!q5no_radioButton.isChecked()){
            valid=false;
        }
        if (!q6yes_radioButton.isChecked()&&!q6no_radioButton.isChecked()){
            valid=false;
        }
        if (!q7yes_radioButton.isChecked()&&!q7no_radioButton.isChecked()){
            valid=false;
        }
        if (!q8yes_radioButton.isChecked()&&!q8no_radioButton.isChecked()){
            valid=false;
        }
        if (!q9yes_radioButton.isChecked()&&!q9no_radioButton.isChecked()){
            valid=false;
        }
        if (!q10yes_radioButton.isChecked()&&!q10no_radioButton.isChecked()){
            valid=false;
        }

        if (!valid){
            Toast.makeText(FormActivity.this, "Please answer all Questions", Toast.LENGTH_SHORT).show();
        }

        return valid;
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