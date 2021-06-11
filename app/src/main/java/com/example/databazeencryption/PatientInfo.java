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

public class PatientInfo extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    DatabaseReference myref;
    DatabaseReference myref2;
    DatabaseReference myref3;
    private FirebaseRecyclerAdapter<Model,Viewholder2> adapter;
    FirebaseRecyclerOptions<Model> options ;

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

        myref= FirebaseDatabase.getInstance().getReference().child("users").child("application");
        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(myref,Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Model, Viewholder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Viewholder2 holder, int position, @NonNull Model model) {
                holder.status.setText("Status"+ ":" +model.getStatus());
                holder.email5.setText("Email to send Report"+ ":" +model.getEmail5());
                holder.symtoms.setText("Symtoms Showed"+ ":" +model.getSymptoms());
                holder.medicine.setText("Medicine Given"+ ":" +model.getMedicine());
                holder.disease.setText("Suspected Disease"+ ":" +model.getDisease());
                holder.app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String message = holder.report.getText().toString();
                        if(holder.report.getText().toString().isEmpty())
                        {
                            holder.report.setError("Please fill the report");
                            return;
                        }
                        Model m = new Model(
                                model.getId(),
                                model.getDisease(),
                                model.getSymptoms(),
                                model.getMedicine(),
                                message,
                                model.getEmail5(),
                                model.getUid());
                        myref.child(model.getUid()).setValue(m);
                    }
                });


                holder.app.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onClick(View v) {
                        myref2 = FirebaseDatabase.getInstance().getReference().child("users").child(model.getUid()).child(model.getUid());
                        myref3 = FirebaseDatabase.getInstance().getReference().child("users").child("application").child(model.getUid());
                        Model model2 = new Model("id",
                                model.getDisease(),
                                model.getSymptoms(),
                                model.getMedicine(),
                                "APPROVED !",
                                model.getEmail5(),
                                model.getUid()
                        );
                        myref2.setValue(model2);
                        Model model3 = new Model("id",
                                model.getDisease(),
                                model.getSymptoms(),
                                model.getMedicine(),
                                "You diagnosis report has been sent to the patient. Thank you!",
                                model.getEmail5(),
                                model.getUid()

                        );
                        myref3.setValue(model3);
                        Toast.makeText(getApplicationContext(),"Diagnosis sent",Toast.LENGTH_SHORT).show();

                        //  myref2= FirebaseDatabase.getInstance().getReference().child("users").child("application").child(editid);
                        String[] TO = {model.getEmail5()};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Congratulations Course Approved");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Thankyou for Applying your courses" +model.getDisease()+ "" + "has been approved");
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }




                    }
                });
                holder.dis.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onClick(View v) {
                        myref2 = FirebaseDatabase.getInstance().getReference().child("users").child(model.getUid()).child(model.getUid());
                        myref3 = FirebaseDatabase.getInstance().getReference().child("users").child("application").child(model.getUid());
                        Model model2 = new Model("id",
                                model.getDisease(),
                                model.getSymptoms(),
                                model.getMedicine(),
                                "REJECTED !",
                                model.getEmail5(),
                                model.getUid()
                        );
                        myref2.setValue(model2);
                        Model model3 = new Model("id",
                                model.getDisease(),
                                model.getSymptoms(),
                                model.getMedicine(),
                                "I cannot Handle This. Please seek further  help!",
                                model.getEmail5(),
                                model.getUid()

                        );
                        myref3.setValue(model3);
                        Toast.makeText(getApplicationContext(),"Sorry, I cannot handle this. ",Toast.LENGTH_SHORT).show();
                        String[] TO = {model.getEmail5()};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We are sorry");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "We appologize we cannot help you " +model.getDisease()+ "" + "Could not be approved");
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }

            @NonNull
            @Override
            public Viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sampledoctorpanel,parent,false);
                return new Viewholder2(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

}