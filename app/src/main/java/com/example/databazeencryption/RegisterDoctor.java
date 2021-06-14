package com.example.databazeencryption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterDoctor extends AppCompatActivity {

    EditText firstnamedoctor,secondnamedoctor,emailregisterdoctor,passwordregisterdoctor;
    Button registerdoctor;
    TextView loginpagedoctor;
    FirebaseAuth firebaseAuth;
    private ProgressDialog md;
    FirebaseDatabase mydatabase;
    private static String usertype = "doctor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_doctor);

        firstnamedoctor = findViewById(R.id.firstnamedoctor);
        secondnamedoctor = findViewById(R.id.secondnamedoctor);
        emailregisterdoctor= findViewById(R.id.emailregisterdoctor);
        passwordregisterdoctor= findViewById(R.id.passwordregisterdoctor);
        registerdoctor= findViewById(R.id.registerdoctor);
        loginpagedoctor= findViewById(R.id.loginpagedoctor);
        md = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mydatabase = FirebaseDatabase.getInstance();

        registerdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstnamedoctor.getText().toString().isEmpty()){
                    firstnamedoctor.setError("Required");
                    return;
                }
                if (secondnamedoctor.getText().toString().isEmpty()){
                    secondnamedoctor.setError("Required");
                    return;
                }
                if(emailregisterdoctor.getText().toString().isEmpty()) {
                    emailregisterdoctor.setError("Required");
                    return;
                }
                if (passwordregisterdoctor.getText().toString().isEmpty()){
                    passwordregisterdoctor.setError("Required");
                    return;
                }
                md.setMessage("PROCESSING");
                md.show();
                firebaseAuth.createUserWithEmailAndPassword(emailregisterdoctor.getText().toString().trim(),passwordregisterdoctor.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            DatabaseReference myRef = mydatabase.getReference("categories").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid());
                            Model2 model = new Model2(firebaseAuth.getCurrentUser().getUid(),usertype);
                            myRef.setValue(model);
                            startActivity(new Intent(getApplicationContext(), VerificationActivity2.class));
                            md.dismiss();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                        md.dismiss();

                    }
                });
            }
        });

        loginpagedoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DoctorLogin.class));
            }
        });

    }
}