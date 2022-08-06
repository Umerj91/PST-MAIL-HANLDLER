package com.example.pstmailhandler;

public class Accounts {
    String accid;
    String acctype;
    String accemail;
    String accpassword;
    String Incomingserver;
    String Inport;
    String Outgpoingserver;
    String Outport;

    public Accounts(String accid, String acctype, String accemail, String accpassword, String incomingserver, String inport, String outgpoingserver, String outport) {
        this.accid = accid;
        this.acctype = acctype;
        this.accemail = accemail;
        this.accpassword = accpassword;
        Incomingserver = incomingserver;
        Inport = inport;
        Outgpoingserver = outgpoingserver;
        Outport = outport;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public String getAccemail() {
        return accemail;
    }

    public void setAccemail(String accemail) {
        this.accemail = accemail;
    }

    public String getAccpassword() {
        return accpassword;
    }

    public void setAccpassword(String accpassword) {
        this.accpassword = accpassword;
    }

    public String getIncomingserver() {
        return Incomingserver;
    }

    public void setIncomingserver(String incomingserver) {
        Incomingserver = incomingserver;
    }

    public String getInport() {
        return Inport;
    }

    public void setInport(String inport) {
        Inport = inport;
    }

    public String getOutgpoingserver() {
        return Outgpoingserver;
    }

    public void setOutgpoingserver(String outgpoingserver) {
        Outgpoingserver = outgpoingserver;
    }

    public String getOutport() {
        return Outport;
    }

    public void setOutport(String outport) {
        Outport = outport;
    }
}
