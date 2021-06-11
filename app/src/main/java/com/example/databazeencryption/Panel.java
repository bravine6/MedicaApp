package com.example.databazeencryption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        recyclerView = findViewById(R.id.people);
        firebaseAuth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Panel.this);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        fetch();

    }

    private void fetch() {

        myref= FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid());
        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(myref,Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Model, Viewholder1>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Viewholder1 holder, int position, @NonNull Model model) {
                holder.status.setText("Status"+ ":" +model.getStatus());
                holder.disease.setText("Disease"+ ":" +model.getDisease());
                holder.symtoms.setText("Symptoms"+ ":" +model.getSymptoms());
                holder.medicine.setText("Medicine"+ ":" +model.getMedicine());
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