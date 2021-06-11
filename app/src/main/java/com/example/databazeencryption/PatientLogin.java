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

public class PatientLogin extends AppCompatActivity {


    EditText emailaddresspatient,passwordpatient;
    private ProgressDialog md;
    Button loginpatient;
    TextView registeraccountpatient;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    // private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        emailaddresspatient = findViewById(R.id.emailaddresspatient);
        passwordpatient = findViewById(R.id.paswwordpatient);
        loginpatient = findViewById(R.id.loginpatient);
        registeraccountpatient = findViewById(R.id.registeraccountpatient);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        md = new ProgressDialog(this);

        loginpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailaddresspatient.getText().toString().isEmpty()) {
                    emailaddresspatient.setError("Required");
                    return;
                }
                if (passwordpatient.getText().toString().isEmpty()) {
                    passwordpatient.setError("Required");
                    return;

                }
                md.setMessage("PROCESSING");
                md.show();
                firebaseAuth.signInWithEmailAndPassword(emailaddresspatient.getText().toString().trim(), passwordpatient.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference myref = firebaseDatabase.getReference("users").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid()).child("usertype");
                            myref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    if(snapshot.getValue(String.class).equals("operator"))
                                    {
                                        emailaddresspatient.setText("");
                                        passwordpatient.setText("");
                                        startActivity(new Intent(getApplicationContext(),Sickness.class));
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
                            Toast.makeText(getApplicationContext(), "Incorrect Details", Toast.LENGTH_SHORT).show();
                            md.dismiss();


                        }

                    }
                });


            }
        });

        registeraccountpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterPatient.class));
            }
        });
    }
}
