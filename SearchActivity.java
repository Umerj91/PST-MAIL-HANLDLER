package com.example.pstmailhandler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {
private SearchView sr;
private  DBHelper dbh;
RecyclerView recv;
ArrayList<Email> mymodellist;
MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        dbh=new DBHelper(getApplicationContext()  );
        sr=findViewById( R.id.searchbar );
        recv=findViewById( R.id.REC );


        adapter = new MyAdapter(this,dbh.Records());

        LinearLayoutManager manager = new LinearLayoutManager(this );
        recv.setLayoutManager(manager);
        recv.setHasFixedSize(true);
        recv.setAdapter(adapter);

        sr.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                MyAdapter myAdapter = new MyAdapter(SearchActivity.this, dbh.searchEmail(query));
                recv.setAdapter(myAdapter);
                //filter( query );

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                MyAdapter myAdapter = new MyAdapter(SearchActivity.this, dbh.searchEmail(query));
//                recv.setAdapter(myAdapter);
                //filter(newText);
//                dbh.searchRecords(query );
                MyAdapter myAdapter = new MyAdapter(SearchActivity.this, dbh.searchEmail(newText));
                recv.setAdapter(myAdapter);
                return true;
            }
        } );
    }

//    private void filter(String newText) {
//        ArrayList<Email> filteredlist=new ArrayList<Email>();
//        for(Email item:mymodellist)
//        {
//            if(item.getMsubject().toLowerCase().contains( newText.toLowerCase() ))
//            {
//                filteredlist.add( item );
//            }
//        }
//        adapter.filterlist( filteredlist  );
//    }
}