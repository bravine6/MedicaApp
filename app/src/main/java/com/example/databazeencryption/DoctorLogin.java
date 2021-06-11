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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class DoctorLogin extends AppCompatActivity {

    EditText emailaddressdoctor,passworddoctor;
    private ProgressDialog md;
    Button logindoctor;
    TextView registeraccountdoctor;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    // private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        emailaddressdoctor = findViewById(R.id.emailaddressdoctor);
        passworddoctor = findViewById(R.id.paswworddoctor);
        logindoctor = findViewById(R.id.logindoctor);
        registeraccountdoctor = findViewById(R.id.registeraccountdoctor);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        md = new ProgressDialog(this);

        logindoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailaddressdoctor.getText().toString().isEmpty()) {
                    emailaddressdoctor.setError("Required");
                    return;
                }
                if (passworddoctor.getText().toString().isEmpty()) {
                    passworddoctor.setError("Required");
                    return;

                }
                md.setMessage("PROCESSING");
                md.show();
                firebaseAuth.signInWithEmailAndPassword(emailaddressdoctor.getText().toString().trim(), passworddoctor.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference myref = firebaseDatabase.getReference("users").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid()).child("usertype");
                            myref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    if(snapshot.getValue(String.class).equals("users"))
                                    {
                                        emailaddressdoctor.setText("");
                                        passworddoctor.setText("");
                                        startActivity(new Intent(getApplicationContext(),PatientInfo.class));
                                        md.dismiss();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Incorrect Type Of User",Toast.LENGTH_SHORT).show();
                                        md.dismiss();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                    Toast.makeText(getApplicationContext(),"Connection timed out",Toast.LENGTH_SHORT).show();
                                    md.dismiss();
                                }


                            });


                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Incorrect Details ", Toast.LENGTH_SHORT).show();
                            md.dismiss();


                        }

                    }
                });


            }
        });

        registeraccountdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterDoctor.class));
            }
        });
    }
}