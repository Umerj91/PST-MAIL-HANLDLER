package com.example.pstmailhandler;

import static com.example.pstmailhandler.Constants.C_FFID;
import static com.example.pstmailhandler.Constants.C_MID;
import static com.example.pstmailhandler.Constants.C_ParentId;
import static com.example.pstmailhandler.Constants.TABLE_ACCOUNT;
import static com.example.pstmailhandler.Constants.TABLE_ACTION;
import static com.example.pstmailhandler.Constants.TABLE_CONTACTS;
import static com.example.pstmailhandler.Constants.TABLE_FOLDER;
import static com.example.pstmailhandler.Constants.TABLE_MAIL;
import static com.example.pstmailhandler.Constants.TABLE_RULE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super( context.getApplicationContext(), Constants.DB_NAME , null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.createaccounttable);
        db.execSQL(Constants.createfoldertable);
        db.execSQL(Constants.createmailtable);
        db.execSQL(Constants.createcontactstable);

        db.execSQL(Constants.createactiontable);
        db.execSQL(Constants.createruletable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOLDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULE);

        onCreate(db);

    }


    //Search data
    public ArrayList<Email> searchRecords(String query) {

        //orderby quer will help us to sort data like  newest/oldest,ascending/decending
        //it will return list or records since we have used arraylist<model> type

        ArrayList<Email> recordsList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_MAIL + " WHERE " + Constants.C_MID + " LIKE '%" + query + "%'" + " OR " + Constants.C_MSUB + " LIKE '%" + query + "%'" + " OR " + Constants.C_MBODY + " LIKE '%" + query + "%'"
                + " OR " + Constants.C_MDATA + " LIKE '%" + query + "%'" + " OR " + Constants.C_MDELETED + " LIKE '%" + query + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Email model = new Email(
                        "" + cursor.getInt(cursor.getColumnIndex( Constants.C_MID )),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_FFID)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_SENDER)),
                        "" + cursor.getString(cursor.getColumnIndex( Constants.C_MDATA)),
                        ""+ cursor.getString( cursor.getColumnIndex( Constants.C_MSUB )),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_MBODY )),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_MDELETED ))

                );
            //    add record to list
                recordsList.add(model);

            } while (cursor.moveToNext());
        }

        db.close();
        return recordsList;
    }
    //---------------------------INSERT DATA INTO ACCOUNT----------------------------------------------//
// Insert Data in Account Table
public long insertAccount(String type, String email, String pass, String ins, String inp, String outs, String outp) {

    // on below line we are creating a variable for
    // our sqlite database and calling writable method
    // as we are writing data in our database.
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    // on below line we are passing all values
    // along with its key and value pair.
    values.put(Constants.C_AType, type);
    values.put(Constants.C_AEmail, email);
    values.put(Constants.C_APws, pass);
    values.put(Constants.C_InS, ins);
    values.put(Constants.C_Inport, inp);
    values.put(Constants.C_OutS, outs);
    values.put(Constants.C_Outport, outp);


    // after adding all values we are passing
    // content values to our table.
    long id = db.insert(TABLE_ACCOUNT, null, values);

    // at last we are closing our
    // database after adding database.
    db.close();
    return id;
}



    //---------------------------INSERT DATA INTO FOLDER----------------------------------------------//
// Insert Data in Account Table
    public long insertFolder(String Fname,String accid,String isdeleted,String pid) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(Constants.C_FNAME, Fname);
        values.put(Constants.C_FAID, accid);
        values.put(Constants.C_FDELETD, isdeleted);
        values.put( C_ParentId, pid);



        // after adding all values we are passing
        // content values to our table.
        long id = db.insert(TABLE_FOLDER, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return id;
    }
    //---------------------------Rule RECORDS----------------------------------------------//
    // get all Mails Data
    public ArrayList<Rules> RRecords() {

        //orderby quer will help us to sort data like  newest/oldest,ascending/decending
        //it will return list or records since we have used arraylist<model> type

        ArrayList<Rules> recordsList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_RULE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( selectquery, null );

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Rules model = new Rules(
                        "" + cursor.getString(cursor.getColumnIndex( Constants.C_RID )),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_FNAME)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_RFNAME)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_SEmail))

                );
                //    add record to list
                recordsList.add(model);

            } while (cursor.moveToNext());
        }



        db.close();
        return recordsList;
    }

    ///
    //Rules View
    public ArrayList<Email> Records() {

        //orderby quer will help us to sort data like  newest/oldest,ascending/decending
        //it will return list or records since we have used arraylist<model> type

        ArrayList<Email> recordsList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_MAIL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Email email = new Email(
                        "" + cursor.getInt(cursor.getColumnIndex( Constants.C_MID )),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_FFID)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_SENDER)),
                        "" + cursor.getString(cursor.getColumnIndex( Constants.C_MDATA)),
                        ""+ cursor.getString( cursor.getColumnIndex( Constants.C_MSUB )),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_MBODY )),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_MDELETED ))

                );
                //add record to list
                recordsList.add(email);

            } while (cursor.moveToNext());
        }

        db.close();
        return recordsList;
    }








    ////

    //---------------------------CONTACT RECORDS----------------------------------------------//
    // get all Contacts data
    public ArrayList<Contacts> ContactRecords() {

        //orderby quer will help us to sort data like  newest/oldest,ascending/decending
        //it will return list or records since we have used arraylist<model> type

        ArrayList<Contacts> recordsList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Contacts contacts = new Contacts(
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_Conid ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_Fname ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_lname ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_pic ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_mail ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_num ) ),
                        "" + cursor.getInt( cursor.getColumnIndex( Constants.C_accid ) )


                );
                //add record to list
                recordsList.add(contacts);

            } while (cursor.moveToNext());
        }

        db.close();
        return recordsList;
    }    //---------------------------------FOLDER RECORDS-----------------------------------------------//
    // get all Folders data
    public ArrayList<Folders> FolderRecords() {



        ArrayList<Folders> foldersArrayList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_FOLDER + " where " + C_ParentId + "='" + -1 + "'";;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Folders folders = new Folders(
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FID ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FNAME ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FDELETD ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FAID ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FDELETD ) )
                );

                //add record to list
                foldersArrayList.add(folders);

            } while (cursor.moveToNext());
        }

        db.close();
        return foldersArrayList;
    }



    //---------------------------------FOLDER RECORDS-----------------------------------------------//
    // get all Folders data
    public ArrayList<Folders> FolderRecords(int id) {



        ArrayList<Folders> foldersArrayList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_FOLDER + " where " + C_ParentId + "='" + id + "'";;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Folders folders = new Folders(
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FID ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FNAME ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FDELETD ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FAID ) ),
                        "" + cursor.getString( cursor.getColumnIndex( C_ParentId ) )



                );
                //add record to list
                foldersArrayList.add(folders);

            } while (cursor.moveToNext());
        }

        db.close();
        return foldersArrayList;
    }

    ///////////////////////////------------Insert Into Action Table-------------////////////////
    public long insertAction(int Aid, String AType, String Aval, String Source, String Destination, int ObjectId,LocalDateTime Date){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(Constants.C_ActId, Integer.valueOf(  Aid));
        values.put(Constants.C_AcType, AType);
        values.put(Constants.C_AcValue, Aval);
        values.put(Constants.C_Source, Source);
        values.put(Constants.C_Dest,Destination);
        values.put( Constants.C_ObjectId ,ObjectId);
        values.put(Constants.C_Datetime, String.valueOf( Date ) );



        // after adding all values we are passing
        // content values to our table.
        long id = db.insert(TABLE_ACTION, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return id;
    }



    //---------------------------INSERT DATA INTO RULES----------------------------------------------//
// Insert Data in Account Table
    public long InsertRule(String Rname,String Folname,String Sender) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(Constants.C_RNAME, Rname);
        values.put(Constants.C_RFNAME, Folname);
        values.put(Constants.C_SEmail, Sender);




        // after adding all values we are passing
        // content values to our table.
        long id = db.insert(TABLE_RULE, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return id;
    }

    //---------------------------------FOLDER RECORDS BY ACC ID-----------------------------------------------//
    public ArrayList<Folders> FolderRecordsById(int accid) {



        ArrayList<Folders> foldersArrayList = new ArrayList<>();

        //query to select records
      //  String selectquery = "SELECT * FROM " + TABLE_FOLDER + " WHERE " + Constants.C_FAID + " = '" + accid  + " AND " + C_ParentId + " = '" + pid ;

        String selectquery = "SELECT * FROM " + TABLE_FOLDER + " WHERE " + Constants.C_FAID+ " = '" + accid+ "'"   ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Folders folders = new Folders(
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FID ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FNAME ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FDELETD ) ),
                        "" + cursor.getString( cursor.getColumnIndex( Constants.C_FAID ) ),
                        "" + cursor.getString( cursor.getColumnIndex( C_ParentId ) )



                );
                //add record to list
                foldersArrayList.add(folders);

            } while (cursor.moveToNext());
        }

        db.close();
        return foldersArrayList;
    }
    //---------------------------MAIL RECORDS BY ID----------------------------------------------//
    // get all  Mail data by ID
    public ArrayList<Email> RecordsbyId(int id) {



        ArrayList<Email> recordsList = new ArrayList<>();

        //query to select records

      //  String selectquery = "SELECT * FROM " + TABLE_MAIL+"Where"+Constants.C_FFID+"='" + '2' + "'";
        try (SQLiteDatabase db = this.getWritableDatabase()) {
           // Cursor cursor = db.rawQuery( "select * from " + TABLE_MAIL + " WHERE FolderId=13 ",null);
            Cursor cursor =  db.rawQuery("select * from " + TABLE_MAIL + " where " + C_FFID + "='" + id + "'" , null);
            //looping through records and add to list
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Email email = new Email(
                            "" + cursor.getInt( cursor.getColumnIndex( Constants.C_MID ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_FFID ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_SENDER ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_MDATA ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_MSUB ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_MBODY ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_MDELETED ) )

                    );
                    //add record to list
                    recordsList.add( email );

                } while (cursor.moveToNext());
            }

            db.close();
        }
        return recordsList;
    }
    //---------------------------CONTACT RECORDS BY ID----------------------------------------------//
   //Get all Contacts Data from Id
   public ArrayList<Contacts> ContactsRecordsbyId(String name) {



       ArrayList<Contacts> recordsList = new ArrayList<>();

       //query to select records

       //  String selectquery = "SELECT * FROM " + TABLE_MAIL+"Where"+Constants.C_FFID+"='" + '2' + "'";
       String selectquery = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + Constants.C_Fname + " LIKE '%" + name + "%'" + " OR " + Constants.C_num + " LIKE '%" + name + "%'" ;
       try (SQLiteDatabase db = this.getWritableDatabase()) {
           // Cursor cursor = db.rawQuery( "select * from " + TABLE_MAIL + " WHERE FolderId=13 ",null);
           Cursor cursor =  db.rawQuery( selectquery,null);
           //looping through records and add to list
           if (cursor.moveToFirst()) {
               do {
                   @SuppressLint("Range") Contacts contacts = new Contacts(
                           "" + cursor.getString( cursor.getColumnIndex( Constants.C_Conid ) ),
                           "" + cursor.getString( cursor.getColumnIndex( Constants.C_FNAME ) ),
                           "" + cursor.getString( cursor.getColumnIndex( Constants.C_lname ) ),
                           "" + cursor.getString( cursor.getColumnIndex( Constants.C_pic ) ),
                           "" + cursor.getString( cursor.getColumnIndex( Constants.C_mail ) ),
                           "" + cursor.getInt( cursor.getColumnIndex( Constants.C_num ) ),
                           "" + cursor.getInt( cursor.getColumnIndex( Constants.C_accid ) )

                   );
                   //add record to list
                   recordsList.add( contacts );

               } while (cursor.moveToNext());
           }

           db.close();
       }
       return recordsList;
   }


//
//---------------------------SEARCH MAILS BY SUB,BODY----------------------------------------------//
//    // Get All Mails Search data
    public ArrayList<Email> searchEmail(String query) {

        //orderby quer will help us to sort data like  newest/oldest,ascending/decending
        //it will return list or records since we have used arraylist<model> type
        ArrayList<Email> recordsList = new ArrayList<>();
        //query to select records

        //String selectquery = "SELECT * FROM " + TABLE_MAIL + " WHERE " + C_MSUB + " Like '" + query + "'" ;

        String selectquery = "SELECT * FROM " + TABLE_MAIL + " WHERE " + Constants.C_MSUB + " LIKE '%" + query + "%'" + " OR " + Constants.C_MBODY + " LIKE '%" + query + "%'" ;
        SQLiteDatabase db = this.getWritableDatabase();
     //   Cursor cursor = db.rawQuery("select * from " + TABLE_MAIL + " where " +Constants.C_MSUB + " LIKE '%" +query + "%'", null);
        Cursor cursor = db.rawQuery(selectquery,null);

        //looping through records and add to list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Email model = new Email(
                        "" + cursor.getInt(cursor.getColumnIndex(Constants.C_MID)),
                        "" + cursor.getString(cursor.getColumnIndex( C_FFID)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_SENDER)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_MDATA)),
                        "" + cursor.getString(cursor.getColumnIndex( Constants.C_MSUB)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_MBODY)),
                        "" + cursor.getString(cursor.getColumnIndex(Constants.C_MDELETED))
                );
                //add record to list
                recordsList.add(model);

            } while (cursor.moveToNext());
        }

        db.close();
        return recordsList;
    }
    //---------------------------DELETE MAIL----------------------------------------------//
    //Delete data
//    public void deleteMail(String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_MAIL, Constants.C_MID + " = ?", new String[]{id});
//        db.close();
//    }

    public boolean deletemail(String  s, String s1) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_MAIL+" SET FolderId = "+"'"+s+"' "+ "WHERE MailId = "+"'"+s1+"'");
        return true;
    }

    public boolean updatemail(int  s2, String s3) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+TABLE_MAIL+" SET FolderId = "+"'"+s2+"' "+ "WHERE Sender = "+"'"+s3+"'");
        return true;
    }

    public ArrayList<Accounts> AccountRecord() {



        ArrayList<Accounts> recordsList = new ArrayList<>();

        //query to select records

        String selectquery = "SELECT * FROM " + TABLE_ACCOUNT;
        try (SQLiteDatabase db = this.getWritableDatabase()) {

            Cursor cursor =  db.rawQuery(selectquery, null);
            //looping through records and add to list
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") Accounts accounts = new Accounts(
                            "" + cursor.getInt( cursor.getColumnIndex( Constants.C_AID ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_AType ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_AEmail ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_APws ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_InS) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_Inport ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_OutS ) ),
                            "" + cursor.getString( cursor.getColumnIndex( Constants.C_Outport ) )

                    );
                    //add record to list
                    recordsList.add( accounts );

                } while (cursor.moveToNext());
            }

            db.close();
        }
        return recordsList;
    }

public  boolean checkcredentials(String Aemail,String Apw)
{
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery("select * from  Account where ACCEmail= ? and AccPw=? ",new String[]{Aemail,Apw});
    if(cursor.getCount()>0)
    return  true;
    else
        return  false;

}



}
