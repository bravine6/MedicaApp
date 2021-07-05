package com.example.databazeencryption;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder1 extends RecyclerView.ViewHolder {
    TextView disease, symtoms, medicine, status,  all5, gender,age;
    Button back2;

    public Viewholder1(@NonNull View itemView) {
        super(itemView);
        disease = itemView.findViewById(R.id.sampledisease);
        symtoms = itemView.findViewById(R.id.samplesymtoms);
        medicine = itemView.findViewById(R.id.samplemedicine);
        status = itemView.findViewById(R.id.samplestatus);
        back2 = itemView.findViewById(R.id.back2);
        all5 = itemView.findViewById(R.id.sampleall5);
        gender = itemView.findViewById(R.id.samplegender);
        age = itemView.findViewById(R.id.sampleage);

    }
}
