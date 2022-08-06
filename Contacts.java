package com.example.pstmailhandler;

public class Contacts {
    String conid;
    String fnanme;
    String lname;
    String picture;
    String email;
    String number;
    String accid;
//    boolean isSelected = false;

    public Contacts(String conid, String fnanme, String lname, String picture, String email, String number, String accid) {
        this.conid = conid;
        this.fnanme = fnanme;
        this.lname = lname;
        this.picture = picture;
        this.email = email;
        this.number = number;
        this.accid = accid;
    }

    public String getConid() {
        return conid;
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

    public String getFnanme() {
        return fnanme;
    }

    public void setFnanme(String fnanme) {
        this.fnanme = fnanme;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }
}


