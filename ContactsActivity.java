package com.example.pstmailhandler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsActivity extends AppCompatActivity {

    DBHelper dbh;
    RecyclerView recv;

    //  ArrayList<Contacts> mymodellist;
    ContactsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contacts );


        dbh = new DBHelper( this );

        recv = findViewById( R.id.RECview );



        adapter = new ContactsAdapter( ContactsActivity.this, dbh.ContactRecords() );
//        if (adapter.getItemCount() == 0) {
//            // Toast.makeText(getApplicationContext(), "NO Data Found",Toast.LENGTH_SHORT).show();
//            txtnodata.setVisibility( View.VISIBLE );
//
//        }




            LinearLayoutManager manager = new LinearLayoutManager( ContactsActivity.this );
            recv.setLayoutManager( manager );
            recv.setHasFixedSize( true );
            recv.setAdapter( adapter );



//        sr.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                ContactsAdapter myAdapter = new ContactsAdapter(ContactsActivity.this, dbh.ContactsRecordsbyId(query));
//                recv.setAdapter(myAdapter);
//                //filter( query );
//
//                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                MyAdapter myAdapter = new MyAdapter(SearchActivity.this, dbh.searchEmail(query));
////                recv.setAdapter(myAdapter);
//                //filter(newText);
////                dbh.searchRecords(query );
//                ContactsAdapter myAdapter = new ContactsAdapter(ContactsActivity.this, dbh.ContactsRecordsbyId(newText));
//                recv.setAdapter(myAdapter);
//                return true;
//            }
//        } );
    }
}
