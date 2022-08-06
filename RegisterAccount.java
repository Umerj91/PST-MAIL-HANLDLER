package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterAccount extends AppCompatActivity {
    EditText emailadd,password,confirmpass;

    private Button btnregister;
    DBHelper dbh;
    String sname;
    TextView txt1;

    // String SMTPS,SMTPp,IMAPS,IMAPp,domain;

    private String type,email,pass,ins,inp,outs,outp,Confirm,domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_account );
        emailadd=findViewById(R.id.txtemailadd);
        password=findViewById(R.id.txtpassword);
        confirmpass=findViewById(R.id.txtconfirmpass);
        btnregister=findViewById( R.id.btnreg );
        txt1=findViewById( R.id.login_title );

        dbh = new DBHelper(RegisterAccount.this);


        btnregister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputData();
                Intent intent=new Intent(RegisterAccount.this,MainScreen.class);

                startActivity(intent);
            }
        } );


    }

    private void inputData() {

        email = "" + emailadd.getText().toString();
        String domain = email.substring( email.indexOf( "@" ) + 1 );
        if(!domain.contains( "Gmail" ))
        {
            Toast.makeText( this, "Enter Gmail Mail Only", Toast.LENGTH_SHORT ).show();
        }
        type = "" + domain;
        pass = "" + password.getText().toString();
        Confirm = "" + confirmpass.getText().toString().trim();

       if(email.length()==0) {
           emailadd.requestFocus();
           emailadd.setError( "Email is Required" );

           if (pass.length() == 0) {
               password.requestFocus();
               password.setError( "Password is Required" );
           }
           if (Confirm.length() == 0) {
               confirmpass.requestFocus();
               confirmpass.setError( "Confirm Password is Required" );
           }
       }
       else {


           domain = domain;
           ins = "IMAP";
           inp = "993";
           outs = "Smtp";
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


               Toast.makeText( RegisterAccount.this, "Registration Successful", Toast.LENGTH_SHORT ).show();


       }





    }



}