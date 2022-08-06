package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MailView extends AppCompatActivity {
    TextView sub;
    TextView from;
    TextView to;
    TextView date;
    TextView body;
    DBHelper dbHelper;
    String subject,From,To,Date,Body;
    Button btnrep,repall,btnforward;
    ArrayList<Email>arrayList;

    int mid;
    public final ArrayList<Integer> selections = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mail_view );
        sub = findViewById( R.id.subject );
        from = findViewById( R.id.fromdetail );
        to = findViewById( R.id.todetail );
        date = findViewById( R.id.datedetail );
        body = findViewById( R.id.txtbody );
        btnrep=findViewById( R.id.btnreply );
        repall=findViewById( R.id.btnreplyall );
        btnforward=findViewById( R.id.btnforward );

        dbHelper = new DBHelper(this);
        arrayList=new ArrayList<Email>();


        ViewData();

        btnrep.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MailView.this,Reply.class);

                    intent.putExtra( "sender",From);

                startActivity(intent);


            }
        } );

        repall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MailView.this,Reply.class);

                intent.putExtra( "sender",From);

                startActivity(intent);

            }
        } );

        btnforward.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MailView.this,Reply.class);

                intent.putExtra( "sender",From);

                startActivity(intent);

            }
        } );

    }

    private void ViewData() {
        if (getIntent().getBundleExtra( "MailData" ) != null) {
            Bundle bundle = getIntent().getBundleExtra("MailData");
            mid = bundle.getInt("mid");
            subject = bundle.getString("subject");
            sub.setText( subject );
            From = bundle.getString("sender");
            from.setText( From );
            Body = bundle.getString("body");
            body.setText( Body );



        }
    }
}