package com.example.pstmailhandler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.navigation.NavigationView;

public class MoveDialog extends AppCompatDialogFragment {

    private NavigationView nv;
    private RadioGroup rdg;


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater=getActivity().getLayoutInflater();


        View view =inflater.inflate( R.layout.movedialog,null );
        builder.setView( view );

        builder.setTitle( "Move to Folder" );
        builder.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText( getContext(), "Cancelled", Toast.LENGTH_SHORT ).show();
            }
        } );
        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//


                Toast.makeText( getContext(), "Mail Moved Successfully", Toast.LENGTH_SHORT ).show();


                try {
                    Menu menu = nv.getMenu();
                   // String text1 = txt1.getText().toString();
                  //  menu.add( text1 ).toString();
                    Toast.makeText( getContext(), "Folder Added", Toast.LENGTH_SHORT ).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } );
    //    txt1=view.findViewById( R.id.txtname );
        return builder.create();
    }


}
