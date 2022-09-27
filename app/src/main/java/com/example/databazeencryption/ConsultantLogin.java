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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ConsultantLogin extends AppCompatActivity {
    EditText emailaddresscon,passwordcon;
    private ProgressDialog md;
    Button logincon;
    TextView registeraccountcon;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_login);

        emailaddresscon = findViewById(R.id.emailaddresscon);
        passwordcon = findViewById(R.id.paswwordcon);
        logincon = findViewById(R.id.logincon);
        registeraccountcon = findViewById(R.id.registeraccountcon);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        md = new ProgressDialog(this);

        logincon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailaddresscon.getText().toString().isEmpty()) {
                    emailaddresscon.setError("Required");
                    return;
                }
                if (passwordcon.getText().toString().isEmpty()) {
                    passwordcon.setError("Required");
                    return;

                }
                md.setMessage("PROCESSING");
                md.show();
                firebaseAuth.signInWithEmailAndPassword(emailaddresscon.getText().toString().trim(), passwordcon.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference myref = firebaseDatabase.getReference("categories").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid()).child("usertype");
                            myref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    if(snapshot.getValue(String.class).equals("consultant"))
                                    {
                                        emailaddresscon.setText("");
                                        passwordcon.setText("");
                                        startActivity(new Intent(getApplicationContext(), ConsultantPage.class));
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



        registeraccountcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterConsultant.class));
            }
        });
    }
}