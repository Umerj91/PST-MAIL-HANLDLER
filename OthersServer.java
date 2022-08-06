package com.example.pstmailhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OthersServer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_others_server );

        Spinner spinneroutc=findViewById(R.id.spinnerinc);
        Spinner spinneroutg=findViewById(R.id.spinneroutg);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.advaccinc, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.advaccoutg, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinneroutc.setAdapter(adapter);
        spinneroutg.setAdapter(adapter2);
    }
}