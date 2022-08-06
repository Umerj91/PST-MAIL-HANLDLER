package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ServerSelection extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    Handler handler;
    Handler handler1;
    Handler handler2;
    Handler handler3 = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_selection);
        b1=findViewById(R.id.btngmail);
        b2=findViewById(R.id.btnYahoo);
        b3=findViewById(R.id.btnoutlook);
        b4=findViewById(R.id.btnothers);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(ServerSelection.this,RegisterAccount.class);


                        startActivity(intent);
                        //finish();
                    }
                },0);
            }
        });





                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handler1=new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(ServerSelection.this,Yahooaccountregister.class);


                                startActivity(intent);
                                //finish();
                            }
                        },0);
                    }
                });




                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handler2=new Handler();
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(ServerSelection.this,Outlookaccountregister.class);


                                startActivity(intent);
                                //finish();
                            }
                        },0);
                    }
                });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(ServerSelection.this,AccountConfiguration.class);
                        startActivity(intent);
                        //finish();
                    }
                },0);
            }
        });
    }
}