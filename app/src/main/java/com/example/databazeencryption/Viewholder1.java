package com.example.databazeencryption;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder1 extends RecyclerView.ViewHolder {
    TextView disease, symtoms, medicine, status, fname5, sname5, all5,surname, gender,age;
    Button back2;

    public Viewholder1(@NonNull View itemView) {
        super(itemView);
        disease = itemView.findViewById(R.id.sampledisease);
        symtoms = itemView.findViewById(R.id.samplesymtoms);
        medicine = itemView.findViewById(R.id.samplemedicine);
        status = itemView.findViewById(R.id.samplestatus);
        back2 = itemView.findViewById(R.id.back2);
        fname5 = itemView.findViewById(R.id.fname5);
        sname5 = itemView.findViewById(R.id.sname5);
        all5 = itemView.findViewById(R.id.all5);
        gender = itemView.findViewById(R.id.gender);
        age = itemView.findViewById(R.id.age);
        surname = itemView.findViewById(R.id.surname);
    }
}
