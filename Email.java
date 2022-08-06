package com.example.pstmailhandler;

import androidx.lifecycle.ViewModel;

public class Email extends ViewModel {
    String mailid;
    String folderid;
    String sender;
    String mdata;
    String msubject;
    String mbody;
    String deleted;
    boolean isSelected = false;

    public Email(String mailid, String folderid, String sender, String mdata, String msubject, String mbody, String deleted) {
        this.mailid = mailid;
        this.folderid = folderid;
        this.sender = sender;
        this.mdata = mdata;
        this.msubject = msubject;
        this.mbody = mbody;
        this.deleted = deleted;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getFolderid() {
        return folderid;
    }

    public void setFolderid(String folderid) {
        this.folderid = folderid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMdata() {
        return mdata;
    }

    public void setMdata(String mdata) {
        this.mdata = mdata;
    }

    public String getMsubject() {
        return msubject;
    }

    public void setMsubject(String msubject) {
        this.msubject = msubject;
    }

    public String getMbody() {
        return mbody;
    }

    public void setMbody(String mbody) {
        this.mbody = mbody;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}
