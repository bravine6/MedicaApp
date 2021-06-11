package com.example.databazeencryption;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder2 extends RecyclerView.ViewHolder{

    TextView disease,symtoms,email5,medicine,status;
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
        app = itemView.findViewById(R.id.app);
        dis = itemView.findViewById(R.id.dis);
    }
}
