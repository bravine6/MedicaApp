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

public class RegisterConsultant extends AppCompatActivity {

    EditText firstnamecon,secondnamecon,emailregistercon,passwordregistercon;
    Button registercon;
    TextView loginpagecon;
    FirebaseAuth firebaseAuth;
    private ProgressDialog md;
    FirebaseDatabase mydatabase;
    private static String usertype = "consultant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_consultant);

        firstnamecon = findViewById(R.id.firstnamecon);
        secondnamecon = findViewById(R.id.secondnamecon);
        emailregistercon= findViewById(R.id.emailregistercon);
        passwordregistercon = findViewById(R.id.passwordregistercon);
        registercon= findViewById(R.id.registercon);
        loginpagecon= findViewById(R.id.loginpagecon);
        md = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mydatabase = FirebaseDatabase.getInstance();

        registercon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstnamecon.getText().toString().isEmpty()){
                    firstnamecon.setError("Required");
                    return;
                }
                if (secondnamecon.getText().toString().isEmpty()){
                    secondnamecon.setError("Required");
                    return;
                }
                if(emailregistercon.getText().toString().isEmpty()) {
                    emailregistercon.setError("Required");
                    return;
                }
                if (passwordregistercon.getText().toString().isEmpty()){
                    passwordregistercon.setError("Required");
                    return;
                }
                md.setMessage("PROCESSING");
                md.show();
                firebaseAuth.createUserWithEmailAndPassword(emailregistercon.getText().toString().trim(),passwordregistercon.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            DatabaseReference myRef = mydatabase.getReference("categories").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid());
                            Model2 model = new Model2(firebaseAuth.getCurrentUser().getUid(),usertype);
                            myRef.setValue(model);
                            startActivity(new Intent(getApplicationContext(), ConsultantLogin.class));
                            md.dismiss();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Please Try again",Toast.LENGTH_SHORT).show();
                        md.dismiss();

                    }
                });
            }
        });



        loginpagecon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ConsultantLogin.class));
            }
        });


    }
}