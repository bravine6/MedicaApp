package com.example.databazeencryption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button patientStart,doctorStart,conStart, admin90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientStart=findViewById(R.id.patientStart);
        admin90 = findViewById(R.id.admin90);
        conStart = findViewById(R.id.conStart);
        doctorStart=findViewById(R.id.doctorStart);
        patientStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientIntent=new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(patientIntent);
            }
        });
        doctorStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent=new Intent(getApplicationContext(),DoctorLogin.class);
                startActivity(doctorIntent);
            }
        });

        conStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent=new Intent(getApplicationContext(),ConsultantLogin.class);
                startActivity(doctorIntent);
            }
        });
        admin90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent=new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(doctorIntent);
            }
        });



    }
}