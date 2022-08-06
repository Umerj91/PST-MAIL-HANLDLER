package com.example.pstmailhandler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Example_Dialog extends AppCompatDialogFragment {

    private EditText txt1;
    private   NavigationView nv;
    String textn;
    DBHelper dbh;
    ArrayList<Folders>fdetails;
    public int AID=101;
     public String ACTIONVAL;
    public String ATYPE;
    public int OBJECTID;
    public String ASOURCE;
    public String  DESTINATION;
    public  LocalDateTime lc;
    ArrayList<Folders>foldersArrayList;
    String id;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater=getActivity().getLayoutInflater();
        //Adding Folder
        dbh=new DBHelper(getContext() );

        //Intent Code

        Intent i=getActivity().getIntent();
        Bundle b=i.getExtras();
        id=b.getString( "Aid" );
//        Accemail=b.getString( "Accemail" );
//        Acctype=b.getString( "Acctype" );


        /////////



        /////



        //////

        View view =inflater.inflate( R.layout.layout_dialog,null );
        builder.setView( view );
        builder.setTitle( "Create New Folder" );
        builder.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText( getContext(), "Folder Not Created", Toast.LENGTH_SHORT ).show();
            }
        } );
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//


                Toast.makeText( getContext(), "Folder Created Successfully", Toast.LENGTH_SHORT ).show();


                try {
                    foldersArrayList=new ArrayList<Folders>();
                   // foldersArrayList.get(foldersArrayList.size()-1);
                   //  AID=101;
                     ATYPE="Create";
                     ACTIONVAL="Folder";
                     ASOURCE=id;
                     DESTINATION=id;
                     OBJECTID=1;
                            // Integer.parseInt( ( String.valueOf( foldersArrayList.get(foldersArrayList.size()-1) )) );
                     lc=LocalDateTime.now();

                    textn=txt1.getText().toString();
                    dbh.insertFolder( textn,id,"false","-1" );
                    Toast.makeText( getContext(), "Folder Added", Toast.LENGTH_SHORT ).show();
                    dbh.insertAction(AID,ATYPE,ACTIONVAL,ASOURCE,  DESTINATION,OBJECTID,lc);
                   // Toast.makeText( getContext(), "Action data added", Toast.LENGTH_SHORT ).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } );
        txt1=view.findViewById( R.id.txtname );
        return builder.create();
    }



}
