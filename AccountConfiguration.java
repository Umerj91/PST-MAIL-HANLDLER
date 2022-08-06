package com.example.pstmailhandler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountConfiguration extends AppCompatActivity {

    EditText txtemail, txtpass, confirm, txtinS, txtinP, txtOutS, txtOutP;
    Button btnreg;


    public DBHelper dbh;
    public String email;
    public String pass;
    public String Confirm;
    public String atype;
    public String domain;
    public String IMAPS;
    public String SMTPS;
    public String IMAPp;
    public String SMTPp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_account_configuration );
        txtemail = findViewById( R.id.txtEmailadd );
        txtpass = findViewById( R.id.txtPassword );
        confirm = findViewById( R.id.txtConfirmpassword );
        txtinS = findViewById( R.id.txtIncG );
        txtinP = findViewById( R.id.txtIncP );
        txtOutS = findViewById( R.id.txtOutg );
        txtOutP = findViewById( R.id.txtOutP );
        btnreg = findViewById( R.id.btnReg );

        dbh = new DBHelper( this );


        btnreg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputData();
            }
        } );


    }


    private void inputData() {

        email = "" + txtemail.getText().toString();
        String domain = email.substring( email.indexOf( "@" ) + 1 );

        atype = "" + domain;
        pass = "" + txtpass.getText().toString();
        Confirm = "" + confirm.getText().toString();
        IMAPS = txtinS.getText().toString();
        IMAPp = txtinP.getText().toString();
        SMTPS = txtOutS.getText().toString();
        SMTPp = txtOutP.getText().toString();

        if (email.length() == 0) {
            txtemail.requestFocus();
            txtemail.setError( "Email is Required" );
        }
        if (pass.length() == 0) {
            txtpass.requestFocus();
            txtpass.setError( "Password is Required" );
        }
        if (Confirm.length() == 0) {
            confirm.requestFocus();
            confirm.setError( "Confirm Password is Required" );
        }
            if (Confirm.length() == 0) {
                confirm.requestFocus();
                confirm.setError( "Confirm Password is Required" );
            }
            if(IMAPS.length()==0)
            {
                txtinS.requestFocus();
                txtinS.setError( "Incoming Server is Required" );
            }
            if(IMAPp.length()==0)
            {
                txtinP.requestFocus();
                txtinP.setError( "Incoming Port is Required" );
            }
            if(SMTPS.length()==0)
            {
                txtOutS.requestFocus();
                txtOutS.setError( "Outgoing Server is Required" );
            }
            if(SMTPp.length()==0)
            {
                txtOutP.requestFocus();
                txtOutP.setError( "Outgoing Port is Required" );

            }

         else {



            long id = dbh.insertAccount(
                    "" + atype,
                    "" + email,
                    "" + pass,
                    "" + IMAPS,
                    "" + IMAPp,
                    "" + SMTPS,
                    "" + SMTPp

            );


            Toast.makeText( AccountConfiguration.this, "Registration Successful: " , Toast.LENGTH_SHORT ).show();


        }
    }

}