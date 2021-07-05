package com.example.databazeencryption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;

public class PatientInfo extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    DatabaseReference myref;
    DatabaseReference myref2;
    DatabaseReference myref3;
    DatabaseReference myref4;
    private FirebaseRecyclerAdapter<Model3,Viewholder2> adapter;
    FirebaseRecyclerOptions<Model3> options ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        recyclerView = findViewById(R.id.users);
        recyclerView.setHasFixedSize(true);
        firebaseAuth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PatientInfo.this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        fetch();
    }
    private void fetch() {
        Encryption e = new Encryption();
        myref= FirebaseDatabase.getInstance().getReference().child("users").child("application");
        myref4 = FirebaseDatabase.getInstance().getReference().child("users");
        options = new FirebaseRecyclerOptions.Builder<Model3>().setQuery(myref,Model3.class).build();
        adapter = new FirebaseRecyclerAdapter<Model3, Viewholder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull Viewholder2 holder, int position, @NonNull @NotNull Model3 model) {

               // holder.status.setText("Status" +":"+model.getStatus());
                try {
                   holder.status.setText("Status"+ ":" +e.AESDecryptionMethod(model.getStatus()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                   unsupportedEncodingException.printStackTrace();
               }
                try {
                    holder.email5.setText("Email to send Report"+ ":" +e.AESDecryptionMethod(model.getEmail5()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.gender.setText("Gender" +":" +e.AESDecryptionMethod(model.getGender()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.age.setText("Age" +":" +e.AESDecryptionMethod(model.getAge()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.all5.setText("Allergies" +":" +e.AESDecryptionMethod(model.getAll5()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.symtoms.setText("Symtoms Showed"+ ":" +e.AESDecryptionMethod(model.getSymptoms()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.medicine.setText("Medicine Given"+ ":" +e.AESDecryptionMethod(model.getMedicine()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.disease.setText("Suspected Disease"+ ":" +e.AESDecryptionMethod(model.getDisease()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                holder.app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String message = holder.report.getText().toString();
                        if (holder.report.getText().toString().isEmpty()) {
                            holder.report.setError("Please fill the report");
                            return;
                        } else {
                            Model3 m = new Model3(
                                    model.getId(),
                                    model.getGender(),
                                    model.getAge(),
                                    model.getAll5(),
                                    model.getDisease(),
                                    model.getSymptoms(),
                                    model.getMedicine(),
                                    //en.AESEncryptionMethod(all5.getText().toString().trim()),
                                  // e.AESEncryptionMethod(model.getReport()),
                                    e.AESEncryptionMethod(message),
                                  //  message,
                                    model.getEmail5(),
                                    model.getUid());
                            myref4.child(model.getUid()).child(model.getUid()).setValue(m);
                            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                            myref.child(model.getUid()).setValue(m);
                        }
                    }
                });



            }

            @NonNull
            @NotNull
            @Override
            public Viewholder2 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sampledoctorpanel,parent,false);
                return new Viewholder2(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

}