package com.example.pstmailhandler;


public class Constants {

    public static final String DB_NAME = "MAILHANDLER";
    // public static final String DB_PATH = "";
    // below int is our database version
    public static final int DB_VERSION = 1;

    public static final String TABLE_ACCOUNT = "Account";
    public static final String TABLE_FOLDER = "Folder";
    public static final String TABLE_RULE = "Rule";
    public static final String TABLE_MAIL = "MAIL";
    public static final String TABLE_CONTACTS = "Contacts";
    public static final String TABLE_ACTION = "Action";

    //For Account Table
    public static final String C_AID = "AccId";
    public static final String C_AType = "AccType";
    public static final String C_AEmail = "AccEmail";
    public static final String C_APws = "AccPws";
    public static final String C_InS = "Incomingservertype";
    public static final String C_Inport = "Incomingport";
    public static final String C_OutS = "Outcomingservertype";
    public static final String C_Outport = "Outcomingport";


    //For Folder Table
    public static final String C_FID = "FolderId";
    public static final String C_FNAME = "FolderName";
    public static final String C_FAID = "AccId";
    public static final String C_ParentId = "ParentId";
    public static final String C_FDELETD = "Deleted";



    //For Mail Table
    public static final String C_MID = "MailId";
    public static final String C_FFID = "FolderId";
    public static final String C_SENDER = "Sender";
    public static final String C_MDATA = "MData";
    public static final String C_MSUB = "MSub";
    public static final String C_MBODY = "MBody";
    public static final String C_MDELETED = "Deleted";
    public static final String C_FOLDERNAME = "FolderName";


    //For Contacts Table
    public static final String C_Conid = "cid";
    public static final String C_Fname = "cfname";
    public static final String C_lname = "clname";
    public static final String C_pic = "picture";
    public static final String C_mail = "Email";
    public static final String C_num = "Number";
    public static final String C_accid = "AccId";


    //FOR ACTION TABLE
    public static final String C_ActId = "ACId";
    public static final String C_AcType = "ACCType";
    public static final String C_AcValue = "ACCValue";
    public static final String C_Source = "Source";
    public static final String C_Dest = "Destination";
    public static final String C_ObjectId="ObjectId";
    public static final String C_Datetime = "DateTime";

    //For Table RULE
    public static final String C_RID = "Ruleid";
    public static final String C_RNAME = "RuleName";
    public static final String C_RFNAME = "FolderName";
    public static final String C_SEmail = "SenderEmail";










//Table Account
    public static final String createaccounttable = "CREATE TABLE " + TABLE_ACCOUNT + " ("
            + C_AID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_AType + " TEXT,"
            + C_AEmail + " TEXT,"
            + C_APws + " TEXT,"
            + C_InS + " TEXT,"
            + C_Inport + " TEXT,"
            + C_OutS + " TEXT,"
            + C_Outport + " TEXT" + ")";

//Table Folder
    public static final String createfoldertable = "create table " + TABLE_FOLDER + " ("
            + C_FID + " integer primary key autoincrement, "
            + C_FNAME + " text, "
            + C_FDELETD + " text, "
            + C_FAID + " integer,"
            + C_ParentId+ " integer,"
            + " FOREIGN KEY ("+C_FAID+") REFERENCES "+TABLE_ACCOUNT+"("+C_AID+"));";
//Table Mail
    public static final String createmailtable = "create table " + TABLE_MAIL + " ("
            + C_MID + " integer primary key autoincrement, "
            + C_SENDER + " text, "
            + C_MDATA + " text, "
            + C_MSUB + " text, "
            + C_MBODY + " text, "
            + C_MDELETED + " text, "
            + C_FFID + " integer,"
            + C_FOLDERNAME + " text,"
            + " FOREIGN KEY ("+C_FFID+") REFERENCES "+TABLE_FOLDER+"("+C_FID+"));";
//Table Contacts

    public static final String createcontactstable = "create table " + TABLE_CONTACTS + " ("
            + C_Conid + " integer primary key autoincrement, "
            + C_Fname + " text, "
            + C_lname + " text, "
            + C_pic + " text, "
            + C_mail + " text, "
            + C_num + " text, "
            + C_accid + " integer,"
            + " FOREIGN KEY ("+C_accid+") REFERENCES "+TABLE_ACCOUNT+"("+C_AID+"));";







    //Table Action
    public static final String createactiontable = "create table " + TABLE_ACTION + " ("
            + C_ActId + " integer, "
            + C_AcType + " text, "
            + C_AcValue + " text, "
            + C_Source + " text, "
            + C_Dest + " text, "
            + C_ObjectId + " integer, "
            + C_Datetime + " TEXT" + ")";

    //Table Rule

    public static final String createruletable = "create table " + TABLE_RULE + " ("
            + C_RID + " integer primary key autoincrement, "
            + C_RNAME + " text, "
            + C_RFNAME + " text,"
            + C_SEmail+ " text "+ ")";

}
