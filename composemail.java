package com.example.pstmailhandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class composemail extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    Button btnsend;
    Button btniattach;
    Button btnoptions;
    String sender;
    EditText editText,bcc;
    Context context;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    ActivityResultLauncher<Intent> resultLauncher;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composemail);

        btnsend=findViewById( R.id.btnsend );
        btniattach=findViewById( R.id.btnattach );
        btnoptions=findViewById( R.id.btnoptions );
        editText=findViewById( R.id.txtTo );
        bcc=findViewById( R.id.txtBCC);


        // Initialize result launcher
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts
                        .StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(
                            ActivityResult result)
                    {
                        // Initialize result data
                        Intent data = result.getData();
                        // check condition
                        if (data != null) {
                            // When data is not equal to empty
                            // Get PDf uri
                            Uri sUri = data.getData();
                            // set Uri on text view
                            bcc.setText( Html.fromHtml(
                                    "<big><b>PDF Uri</b></big><br>"
                                            + sUri));

                            // Get PDF path
                            String sPath = sUri.getPath();
                             //Set path on text view
//                            bcc.setText( Html.fromHtml(
//                                    "<big><b>PDF Path</b></big><br>"
//                                            + sPath));
                        }
                    }
                });

        btniattach.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(composemail.this, btniattach);
                popupMenu.getMenuInflater().inflate(R.menu.attach_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.attfile:
//                                Intent intent=new Intent(view.getContext(),Foldersview.class);
//                                startActivity( intent );
                                openFolder();

                                // check condition
//                                if (ActivityCompat.checkSelfPermission(
//                                        composemail.this,
//                                        Manifest.permission
//                                                .READ_EXTERNAL_STORAGE)
//                                        != PackageManager
//                                        .PERMISSION_GRANTED) {
//                                    // When permission is not granted
//                                    // Result permission
//                                    ActivityCompat.requestPermissions(
//                                            composemail.this,
//                                            new String[] {
//                                                    Manifest.permission
//                                                            .READ_EXTERNAL_STORAGE },
//                                            1);
//                                }
//                                else {
//                                    // When permission is granted
//                                    // Create method
//                                    selectPDF();
//                                }

                            return true;

                            default:
                                return false;
                        }

                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        } );

//For Options Menu
        btnoptions.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(composemail.this, view);
                popup.setOnMenuItemClickListener(composemail.this);
                popup.inflate(R.menu.addrecipientoptions);

                popup.show();


            }
        } );





    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addcont:
                // do your code
                startActivity(new Intent(getApplicationContext(), ContactsActivity.class));
                return true;

            default:
                return false;
        }
    }


//    private void selectPDF()
//    {
//        // Initialize intent
//        Intent intent
//                = new Intent(Intent.ACTION_GET_CONTENT);
//        // set type
//        intent.setType("application/pdf");
//        // Launch intent
//        resultLauncher.launch(intent);
//    }

//    @Override
//    public void onRequestPermissionsResult(
//            int requestCode, @NonNull String[] permissions,
//            @NonNull int[] grantResults)
//    {
//        super.onRequestPermissionsResult(
//                requestCode, permissions, grantResults);
//
//        // check condition
//        if (requestCode == 1 && grantResults.length > 0
//                && grantResults[0]
//                == PackageManager.PERMISSION_GRANTED) {
//            // When permission is granted
//            // Call method
//            selectPDF();
//        }
//        else {
//            // When permission is denied
//            // Display toast
//            Toast
//                    .makeText(getApplicationContext(),
//                            "Permission Denied",
//                            Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }
public void openFolder() {
    Intent intent = new Intent();
    intent.setType("*/*");
//        intent.setType("file/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    intent.putExtra("return-data", true);
    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
}

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            bcc.setText(URI.getLastPathSegment());
        }
    }
}