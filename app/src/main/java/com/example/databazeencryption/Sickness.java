package com.example.databazeencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sickness extends AppCompatActivity {

    EditText disease, symtoms, medicine, email5, all5, gender, age;
    Button diagnosis;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sickness);


        all5 = findViewById(R.id.all5);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        disease = findViewById(R.id.disease);
        symtoms = findViewById(R.id.symtoms);
        medicine = findViewById(R.id.medicine);
        email5 = findViewById(R.id.email5);
        diagnosis = findViewById(R.id.diagnosis);
        firebaseAuth = FirebaseAuth.getInstance();

        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FirebaseDatabase database = FirebaseDatabase.getInstance();
               FirebaseDatabase database2 = FirebaseDatabase.getInstance();
               Encryption en = new Encryption();


               DatabaseReference myRef = database.getReference("users").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid());
                String id = myRef.push().getKey();
               DatabaseReference myRef2 = database2.getReference("users").child("application").child(firebaseAuth.getCurrentUser().getUid());
               // push key to admin side also
              String status = "Pending. Wait for Results";
              String uid = firebaseAuth.getCurrentUser().getUid();
               Model model = new Model(id,
                       en.AESEncryptionMethod(gender.getText().toString().trim()),
                       en.AESEncryptionMethod(age.getText().toString().trim()),
                       en.AESEncryptionMethod(all5.getText().toString().trim()),
                       en.AESEncryptionMethod(disease.getText().toString().trim()),
                      // disease.getText().toString().trim(),
                       en.AESEncryptionMethod(symtoms.getText().toString().trim()),
                       en.AESEncryptionMethod(medicine.getText().toString().trim()),
                       en.AESEncryptionMethod(status),
                       //status,
                        en.AESEncryptionMethod(email5.getText().toString()),
                       uid);
               myRef2.setValue(model);
               myRef.setValue(model);
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Panel.class));
                finish();
            }
        });
    }
}