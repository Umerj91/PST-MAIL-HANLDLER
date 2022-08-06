package com.example.pstmailhandler;

public class Folders {
    String folderid;
    String foldername;
    String isDeleted;
    String Accid;
    String ParentId;

    public Folders(String folderid, String foldername, String isDeleted, String accid, String parentId) {
        this.folderid = folderid;
        this.foldername = foldername;
        this.isDeleted = isDeleted;
        Accid = accid;
        ParentId = parentId;
    }

    public String getFolderid() {
        return folderid;
    }

    public void setFolderid(String folderid) {
        this.folderid = folderid;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAccid() {
        return Accid;
    }

    public void setAccid(String accid) {
        Accid = accid;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }
}
