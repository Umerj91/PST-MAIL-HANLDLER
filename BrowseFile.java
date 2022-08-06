package com.example.pstmailhandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class BrowseFile extends AppCompatActivity {


     MaterialButton browse;
//
//    {
//        browse = findViewById( R.id.btn_browse );
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_file);
        browse=findViewById( R.id.btn_browse );
       browse.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (checkPermission()) {
                   Intent in = new Intent( BrowseFile.this, Foldersview.class );
                   String path = Environment.getExternalStorageDirectory().getPath();
                   in.putExtra( "path", path );
                   startActivity( in );

               } else {
                   req_permission();
               }

           }
       } );
    }


    private  boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission( BrowseFile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE );
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    private void req_permission()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale( BrowseFile.this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
            Toast.makeText(BrowseFile.this, "Permission is Required ,Allow From Settings", Toast.LENGTH_SHORT).show();
        else
            ActivityCompat.requestPermissions(BrowseFile.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    111);


    }

}