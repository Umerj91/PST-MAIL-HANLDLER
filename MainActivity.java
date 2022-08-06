package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Handler handler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        handler=new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                Intent intent=new Intent(MainActivity.this,AddNewAccount.class);
                startActivity(intent);
                finish();

            }
        },3000);


    }
}