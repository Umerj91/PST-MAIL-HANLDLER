package com.example.pstmailhandler;

public class Rules {
    public  String Rid;
    public  String Rname;
    public  String Fname;
    public  String SEmail;

    public Rules(String rid, String rname, String fname, String SEmail) {
        Rid = rid;
        Rname = rname;
        Fname = fname;
        this.SEmail = SEmail;
    }





    public String getRid() {
        return Rid;
    }

    public void setRid(String rid) {
        Rid = rid;
    }

    public String getRname() {
        return Rname;
    }

    public void setRname(String rname) {
        Rname = rname;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }
}
