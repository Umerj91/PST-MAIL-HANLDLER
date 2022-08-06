package com.example.pstmailhandler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllFolders extends AppCompatActivity {


    Folderadapter adapter;
   RecyclerView recyclerView;
    DBHelper dbHelper;

    String Aid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_folders );
     recyclerView=findViewById( R.id.recview2 );
        dbHelper=new DBHelper( this );




        adapter = new Folderadapter(AllFolders.this,dbHelper.RRecords());
        LinearLayoutManager manager = new LinearLayoutManager(AllFolders.this );
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);




    }
}