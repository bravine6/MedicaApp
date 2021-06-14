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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPatient extends AppCompatActivity {

    EditText firstnamepatient,secondnamepatient,emailregisterpatient,passwordregisterpatient;
    Button registerpatient;
    TextView loginpagepatient;
    FirebaseAuth firebaseAuth;
    private ProgressDialog md;
    FirebaseDatabase mydatabase;
    private static String usertype = "patient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);


        firstnamepatient = findViewById(R.id.firstnamepatient);
        secondnamepatient = findViewById(R.id.secondnamepatient);
        emailregisterpatient= findViewById(R.id.emailregisterpatient);
        passwordregisterpatient = findViewById(R.id.passwordregisterpatient);
        registerpatient= findViewById(R.id.registerpatient);
        loginpagepatient= findViewById(R.id.loginpagepatient);
        md = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mydatabase = FirebaseDatabase.getInstance();

        registerpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstnamepatient.getText().toString().isEmpty()){
                    firstnamepatient.setError("Required");
                    return;
                }
                if (secondnamepatient.getText().toString().isEmpty()){
                    secondnamepatient.setError("Required");
                    return;
                }
                if(emailregisterpatient.getText().toString().isEmpty()) {
                    emailregisterpatient.setError("Required");
                    return;
                }
                if (passwordregisterpatient.getText().toString().isEmpty()){
                    passwordregisterpatient.setError("Required");
                    return;
                }
                md.setMessage("PROCESSING");
                md.show();
                firebaseAuth.createUserWithEmailAndPassword(emailregisterpatient.getText().toString().trim(),passwordregisterpatient.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            DatabaseReference myRef = mydatabase.getReference("categories").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid());
                            Model2 model = new Model2(firebaseAuth.getCurrentUser().getUid(),usertype);
                            myRef.setValue(model);
                            startActivity(new Intent(getApplicationContext(), VerificationActivity.class));
                            md.dismiss();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                        md.dismiss();

                    }
                });
            }
        });



        loginpagepatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PatientLogin.class));
            }
        });


    }
}