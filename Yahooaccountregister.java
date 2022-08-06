package com.example.pstmailhandler;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Yahooaccountregister extends AppCompatActivity {
    EditText emailadd, password, confirmpass;

    private Button btnregister;
    DBHelper dbh;
    Context context;
   // String SMTPS, SMTPp, IMAPS, IMAPp, domain;
String sname1;
TextView txt1;
    private String type, email, pass, ins, inp, outs, outp, Confirm,domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_account );
        emailadd = findViewById( R.id.txtemailadd );
        password = findViewById( R.id.txtpassword );
        confirmpass = findViewById( R.id.txtconfirmpass );
        btnregister = findViewById( R.id.btnreg );
        txt1=findViewById( R.id.login_title1 );

        dbh = new DBHelper( Yahooaccountregister.this );


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
        if(!domain.contains( "Yahoo" ))
        {
            Toast.makeText( this, "Enter Yahoo Mail Only", Toast.LENGTH_SHORT ).show();
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
            outp = "Smtp";
            outs = "465";

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
            Toast.makeText( Yahooaccountregister.this, "Registration Successful: ", Toast.LENGTH_SHORT ).show();

            emailadd.setText( "" );
            password.setText( "" );
            confirmpass.setText( "" );


        }


    }
}