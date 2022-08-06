package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mails extends AppCompatActivity {
    RecyclerView recyclerView;
    private Button b1;
    private  DBHelper dbHelper;
    private Button btnnew;
    private  Button search;
    Button btnselect;
    MyAdapter adapter;
    private CardView cardView;
    Toolbar toolbar;
    ArrayList<Email> emailArrayList;
    ArrayList<Folders>foldersArrayList;
    private  int id;
    private  String fname;
    public  Mails(int fid)
    {
        id=fid;
    }
    public Mails() {
        // Required empty public constructor
    }

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_mails );

        Intent i=getIntent();
        Bundle b=i.getExtras();
        id=b.getInt( "fid" );
        fname=b.getString("foldername");
       // setHasOptionsMenu(true);






        b1=findViewById( R.id.btncompose );
        btnnew=findViewById( R.id.btnnew );
        search=findViewById( R.id.btnsearch );
        cardView=findViewById( R.id.discarview );
        btnselect=findViewById( R.id.btnselect );
        // c1=(CheckBox )  view.findViewById( R.id.chkbox );
        recyclerView=findViewById( R.id.recview );
        dbHelper = new DBHelper(Mails.this);



         emailArrayList=dbHelper.RecordsbyId(id);



        adapter = new MyAdapter(Mails.this,emailArrayList);

        LinearLayoutManager manager = new LinearLayoutManager(Mails.this );
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

//        b1.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent=new Intent(Mails.this,composemail.class);
////                startActivity(intent);
//
//
//            }
//        } );


//        btnnew.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    //openDialog();
//
//
//
//
//
//                }
//                catch(Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//        } );
//
//

//        search.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    Intent intent=new Intent(Mails.this,SearchActivity.class);
//                    startActivity(intent);
//                }
//                catch(Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//        } );
//
//    }

//    public  void openDialog()
//    {
//        Example_Dialog example_dialog=new Example_Dialog();
//        example_dialog.show(getSupportFragmentManager(),"Example Dialog");
//
//    }
}}