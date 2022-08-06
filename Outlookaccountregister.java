package com.example.pstmailhandler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Outlookaccountregister extends AppCompatActivity {
    EditText emailadd, password, confirmpass;

    private Button btnregister;
    DBHelper dbh;
    TextView txt1;
String sname2;
   // String SMTPS, SMTPp, IMAPS, IMAPp, domain;

    private String type, email, pass, ins, inp, outs, outp, Confirm,domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_account );
        emailadd = findViewById( R.id.txtemailadd );
        password = findViewById( R.id.txtpassword );
        confirmpass = findViewById( R.id.txtconfirmpass );
        btnregister = findViewById( R.id.btnreg );
txt1=findViewById( R.id.login_title2 );

        dbh = new DBHelper( Outlookaccountregister.this );

//        String Email;
//        String Pass;
//        String ConfirmPass;
//        Email=emailadd.getText().toString();
//        Pass=password.getText().toString();
//        ConfirmPass=confirmpass.getText().toString();


        //  String split_first = str.substring(0,str.indexOf("@"));
        //  String split_second = str.substring(str.indexOf("@")+1);


        btnregister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputData();
            }
        } );


    }

    private void inputData() {
        email = "" + emailadd.getText().toString();
        String domain = email.substring( email.indexOf( "@" ) + 1 );
        if(!domain.contains( "Outlook" ))
        {
            Toast.makeText( this, "Enter Outlook Mail Only", Toast.LENGTH_SHORT ).show();
        }
        type = "" + domain;
        pass = "" + password.getText().toString();
        Confirm = "" + confirmpass.getText().toString().trim();

        if (email.length() == 0) {
            emailadd.requestFocus();
            emailadd.setError( "Email is Required" );
        }
        if (pass.length() == 0) {
            password.requestFocus();
            password.setError( "Password is Required" );
        }
        if (Confirm.length() == 0) {
            confirmpass.requestFocus();
            confirmpass.setError( "Confirm Password is Required" );
        } else {
            domain = domain;
            ins = "IMAP";
            inp = "993";
            outs= "Smtp";
            outp = "465";

            long id = dbh.insertAccount(
                    "" + type,
                    "" + email,
                    "" + pass,
                    "" + ins,
                    "" + inp,
                    "" + outs,
                    "" + outp

            );

            // after adding the data we are displaying a toast message.
            Toast.makeText( Outlookaccountregister.this, "Registration Successful ", Toast.LENGTH_SHORT ).show();

            emailadd.setText( "" );
            password.setText( "" );
            confirmpass.setText( "" );


        }


    }
}