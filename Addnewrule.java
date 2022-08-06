package com.example.pstmailhandler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Addnewrule extends AppCompatActivity {
    EditText Rname,Fname,Semail;
    Button btnAdd;
    String txtrname,txtfname,txtsmail;
    DBHelper dbHelper;
    int id;
    int fid;
    ArrayList<String >arrayList;
    ArrayList<Folders>fdetail;
    Email obj;
    List<Email> sender;

  

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_addnewrule );
        Intent i=getIntent();
        Bundle b=i.getExtras();
        id=b.getInt( "fid" );
        Rname=findViewById( R.id.edtrulename );
        Fname=findViewById( R.id.edtfoldername );
        Semail=findViewById( R.id.edteSemail );
        btnAdd=findViewById( R.id.btnadd );

        dbHelper=new DBHelper( this );


        btnAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtrname=Rname.getText().toString();
                txtfname=Fname.getText().toString();
                txtsmail=Semail.getText().toString();

                dbHelper.updatemail(id,txtsmail );
            dbHelper.InsertRule(txtrname,txtfname,txtsmail);

                Toast.makeText( Addnewrule.this, "New Rule Added", Toast.LENGTH_SHORT ).show();

//              fdetail=dbHelper.FolderRecordsById( Integer.parseInt(  id) );
//              arrayList=new ArrayList<>();
//
//                for (int in = 0; in < fdetail.size(); in++) {
//                    arrayList.add( fdetail.get( in ).foldername );
//                }
//                if(arrayList.e)
//                {

//                }
//                Toast.makeText( Addnewrule.this, "List"+arrayList, Toast.LENGTH_SHORT ).show();
                finish();

            }
        } );



    }
}