package com.example.pstmailhandler;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class Foldersview extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foldersview);
        RecyclerView recyclerView=findViewById( R.id.recycler );
        TextView txt=findViewById( R.id.txt1 );
        String path=getIntent().getStringExtra( "path" );
        File root =new File(path);
        File[] fileandFolders=root.listFiles();
        if(fileandFolders==null || fileandFolders.length==0)
        {
            txt.setVisibility( View.VISIBLE );
            return;
        }
        txt.setVisibility( View.INVISIBLE );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setAdapter( new FileAdapter(getApplicationContext(),fileandFolders));

    }



}