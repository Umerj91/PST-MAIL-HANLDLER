package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddNewAccount extends AppCompatActivity {
private Button btnadd;
    private SearchView sr;
    private  DBHelper dbh;
    RecyclerView recv;
    String email,acctype;
    Accounts accounts;
    AccountsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_new_account );

        btnadd =findViewById(  R.id.btnaddaccount );
        dbh=new DBHelper(AddNewAccount.this );
        sr=findViewById( R.id.searchbar );
        recv=findViewById( R.id.recycleview1 );

        adapter = new AccountsAdapter(this,dbh.AccountRecord());

        LinearLayoutManager manager = new LinearLayoutManager(this );
        recv.setLayoutManager(manager);
        recv.setHasFixedSize(true);
        recv.setAdapter(adapter);
        btnadd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( AddNewAccount.this, ServerSelection.class );

                startActivity( intent );
//                finish();
            }
        } );




    }


}