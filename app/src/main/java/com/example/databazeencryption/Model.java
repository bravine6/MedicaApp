package com.example.databazeencryption;


public class Model {


    String id,disease,symptoms,medicine,status,email5,uid;

    public  Model()
    {}

    public Model(String id, String disease, String symptoms, String medicine, String status, String email5, String uid) {
        this.id = id;
        this.disease = disease;
        this.symptoms = symptoms;
        this.medicine = medicine;
        this.status = status;
        this.email5 = email5;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail5() {
        return email5;
    }

    public void setEmail5(String email5) {
        this.email5 = email5;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
