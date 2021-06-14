package com.example.databazeencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Decider extends AppCompatActivity {

    Button majibu, maswali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider);

        majibu = findViewById(R.id.majibu);
        maswali = findViewById(R.id.maswali);

        maswali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientIntent=new Intent(getApplicationContext(),Sickness.class);
                startActivity(patientIntent);
            }
        });
        majibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent=new Intent(getApplicationContext(),Panel.class);
                startActivity(doctorIntent);
            }
        });

    }
}