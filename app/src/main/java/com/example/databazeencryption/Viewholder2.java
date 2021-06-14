package com.example.databazeencryption;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder2 extends RecyclerView.ViewHolder{

    TextView disease,symtoms,email5,medicine,status, fname5, sname5, all5,surname, gender,age;
    EditText report;
    Button app,dis;
    public Viewholder2(@NonNull View itemView) {
        super(itemView);
        disease = itemView.findViewById(R.id.admindisease);
        report = itemView.findViewById(R.id.report);
        symtoms = itemView.findViewById(R.id.adminsymtoms);
        email5= itemView.findViewById(R.id.adminemail);
        medicine = itemView.findViewById(R.id.adminmedicine);
        status = itemView.findViewById(R.id.adminstatus);
        fname5 = itemView.findViewById(R.id.samplefname5);
        sname5 = itemView.findViewById(R.id.samplesname5);
        all5 = itemView.findViewById(R.id.sampleall5);
        gender = itemView.findViewById(R.id.samplegender);
        age = itemView.findViewById(R.id.sampleage);
        surname = itemView.findViewById(R.id.samplesurname);
        app = itemView.findViewById(R.id.app);
        dis = itemView.findViewById(R.id.dis);
    }
}
