package com.example.databazeencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText passwordadmin, useradmin;
    Button loginadmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        passwordadmin = findViewById(R.id.paswwordadmin);
        useradmin = findViewById(R.id.useradmin);
        loginadmin = findViewById(R.id.loginadmin);

        loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(useradmin.getText().toString().isEmpty())
                {
                    useradmin.setError("Required");
                    return;
                }
                if(passwordadmin.getText().toString().isEmpty())
                {
                    passwordadmin.setError("Required");
                    return;
                }

                if(useradmin.getText().toString().trim().equals("admin") && passwordadmin.getText().toString().trim().equals("qwerty1234"))
                {
                    startActivity(new Intent(getApplicationContext(),AdminPage.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}