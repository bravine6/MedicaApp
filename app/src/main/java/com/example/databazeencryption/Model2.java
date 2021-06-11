package com.example.databazeencryption;


public class Model2 {
    String uid;
    String usertype;
    public Model2()
    {

    }

    public Model2(String uid, String usertype) {
        this.uid = uid;
        this.usertype = usertype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
