package com.example.databazeencryption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

public class Panel extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference myref;
    FirebaseAuth firebaseAuth;
    private FirebaseRecyclerAdapter<Model,Viewholder1> adapter;
    FirebaseRecyclerOptions<Model> options ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        recyclerView = findViewById(R.id.people2);
        firebaseAuth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Panel.this);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        fetch();

    }

    private void fetch() {
        Encryption e = new Encryption();
        myref= FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid());
        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(myref,Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Model, Viewholder1>(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull Viewholder1 holder, int position, @NonNull Model model) {
                
                try {
                    holder.gender.setText("GENDER"+ ":" +e.AESDecryptionMethod(model.getGender()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.age.setText("AGE"+ ":"+ e.AESDecryptionMethod(model.getAge()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.all5.setText("ALLERGIES"+ ":"+e.AESDecryptionMethod(model.getAll5()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                   holder.status.setText("Status"+ ":" +e.AESDecryptionMethod(model.getStatus()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                   unsupportedEncodingException.printStackTrace();
               }
              //  holder.status.setText("Status"+":" +model.getStatus());
                try {
                    holder.disease.setText("Disease"+ ":" +e.AESDecryptionMethod(model.getDisease()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.symtoms.setText("Symptoms"+ ":" +e.AESDecryptionMethod(model.getSymptoms()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }
                try {
                    holder.medicine.setText("Medicine"+ ":" +e.AESDecryptionMethod(model.getMedicine()));
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                }

                // holder.medicine.setText("Medicine"+ ":" +model.getSymptoms());

                holder.back2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(), Decider.class);
                        startActivity(intent);


                    }
                });
            }

            @NonNull
            @Override
            public Viewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samplepatient,parent,false);
                return new Viewholder1(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        
    }


}