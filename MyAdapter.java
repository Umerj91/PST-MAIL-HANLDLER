package com.example.pstmailhandler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Button button;
    public static int malid;
    AlertDialog alertDialog1;



    public Context context;
    Activity activity;
    Email email;
    Folders folderDetail;


    ArrayList<Integer> selectList=new ArrayList<>();

    private ArrayList<Email> ModelArrayList;
    private ArrayList<Folders> FolderArrayList;

    Activity MainScreen;
    ArrayList<Folders> fdetail;
    ArrayList<ModelMenu> mmlist;
    public  int mailid;
    public  String  Fid="5";
    DBHelper dbHelper;
    public  ModelMenu men = new ModelMenu();
    public final ArrayList<Integer> selections = new ArrayList<>();





    public MyAdapter(Context context, ArrayList<Email> modelArrayList) {
        this.context = context;
        this.ModelArrayList = modelArrayList;

        dbHelper = new DBHelper(context);

    }

    public MyAdapter( ArrayList<Folders> folderRecords) {
    }

    public MyAdapter(AllFolders context, ArrayList<Folders> folderRecords) {
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context.getApplicationContext() ).inflate( R.layout.singlerowdesign,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public  void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {





        final Email model = ModelArrayList.get(position);
        final  String id =   model.getMailid();
        String folderid = model.getFolderid();
        String sender = model.getSender();
        String data = model.getMdata();
        String subject = model.getMsubject();
        String body = model.getMbody();
        String deleted = model.getDeleted();
        MenuInflater inflater =new MenuInflater( context.getApplicationContext() );
//        btnselect=((Mails)context).btnselect.findViewById(R.id.btnselect);
        holder.txtMSub.setText( subject );
        holder.txtMBody.setText( body );


        holder.txtMSub.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("mid", model.getMailid());
                bundle.putString("folderid", model.getFolderid());
                bundle.putString("sender", model.getSender());
                bundle.putString("data", model.getMdata());
                bundle.putString("subject", model.getMsubject());
                bundle.putString("body", model.getMbody());
                bundle.putString("deleted", model.getDeleted());
                Intent intent = new Intent(context, MailView.class);
                intent.putExtra("MailData", bundle);
                context.startActivity(intent);
            }
        } );
        //Selecting Mails and Enabling Button View/////////////////////////
      holder.cardView.setOnLongClickListener( new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View view) {
                //Unselecting
              if (selections.contains(position)){
                  selections.remove(selections.indexOf(position));
                  holder.cardView.setCardBackgroundColor( Color.WHITE);
                  if(selections.size()==0) {

                      ( (MainScreen)context).findViewById(R.id.btnselect).setVisibility( View.INVISIBLE );
                         // ((Mails) context).findViewById( R.id.btnselect ).setVisibility( View.INVISIBLE );

                  }
                  else {
                      ( (MainScreen)context).findViewById(R.id.btnselect).setVisibility( View.INVISIBLE );
                          // ((Mails) context).findViewById( R.id.btnselect ).setVisibility( View.VISIBLE );

                  }
              } else {
                  //Selecting
                  selections.add(position);

                  holder.cardView.setCardBackgroundColor(Color.GRAY);

                  ( (MainScreen)context).findViewById(R.id.btnselect).setVisibility( View.VISIBLE );
                     // ((Mails)context).findViewById(R.id.btnselect).setVisibility( View.VISIBLE );

              }



              return false;
          }
          } );
       // Select Button And Showing Options
//        ((MainScreen)context).findViewById( R.id.btnselect ).setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popupMenu = new PopupMenu(context,((MainScreen) context).findViewById( R.id.btnselect ));
//                popupMenu.inflate(R.menu.flow_menu);
//                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.delete_menu:
//                                /////delete opreation
//                                dbHelper.deletemail("5",id);
//                               // dbHelper.update( Fid,id );
//
//                                ModelArrayList.remove(position);
//                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position,ModelArrayList.size());
//                                ((MainScreen) context).findViewById( R.id.btnselect ).setVisibility( View.INVISIBLE );
//
//                                //refresh list after deleting record
//                                break;
//                            case R.id.move_menu:
//                                /////Move  opreation
//                                Intent in=new Intent(context,AllFolders.class);
//                                int mid =selections.indexOf( position );
//                                in.putExtra( "Mid",position );
//
//                                context.startActivity( in );
//
//                             //
//                                //
//                                //
//                                   CreateAlertDialogWithRadioButtonGroup();
//                                //  dbHelper.update( Fid,id );
//
////                                Intent intent = new Intent(context, AllFolders.class);
////                                intent.putExtra( "Mailid",selections.get(position) );
////                                context.startActivity( intent );
//                                holder.cardView.setCardBackgroundColor(Color.WHITE);
//                                ModelArrayList.remove(position);
//                                notifyItemRemoved(position);
//                                notifyItemRangeChanged(position,ModelArrayList.size());
//                                Toast.makeText( context, "Mail Moved Successfully", Toast.LENGTH_SHORT ).show();
//                            //    ((Mails) context).findViewById( R.id.btnselect ).setVisibility( View.INVISIBLE );
//
//
//                            default:
//                        }
//                        return false;
//                    }
//                } );
//                popupMenu.show();
//            }
//        } );




    }



    public void CreateAlertDialogWithRadioButtonGroup(){

        fdetail = dbHelper.FolderRecords();
        mmlist = new ArrayList<>();
        for (int i = 0; i < fdetail.size(); i++) {

          men = new ModelMenu();
            men.id = Integer.parseInt( fdetail.get( i ).folderid );
            men.name = fdetail.get( i ).foldername;
            mmlist.add( men );
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String[] options = {"Option1", "Option2", "Option3", "Option4", "Option3"};


//        for (int i=0;i< fdetail.size();i++)
//        {
//            folder[i]=men.name=fdetail.get( i ).foldername;
//        }
        builder.setTitle("Select Your Choice");

        builder.setSingleChoiceItems( options, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:

                        Toast.makeText(context.getApplicationContext(), "First Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 1:

                        Toast.makeText(context.getApplicationContext(), "Second Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case 2:

                        Toast.makeText(context.getApplicationContext(), "Third Item Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
                alertDialog1.dismiss();

            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();

    }


    @Override
    public int getItemCount() {
        return ModelArrayList.size();
    }
    public  void openDialog()
    {
        Example_Dialog example_dialog=new Example_Dialog();
        example_dialog.show( example_dialog.getParentFragmentManager(), "Example Dialog");

    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMSub,txtMBody;
        ImageView image;
        CardView cardView;
        Button btnsel;
       // ImageButton clipmenu;
//        ImageView c1;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txtMSub =(TextView)itemView.findViewById(R.id.t1);
            txtMBody =(TextView)itemView.findViewById(R.id.t2);
            image =(ImageView) itemView.findViewById(R.id.img);
           // clipmenu = (ImageButton)itemView.findViewById(R.id.flowmenu);
            cardView =(CardView)itemView.findViewById(R.id.discarview);
//            c1= (ImageView) itemView.findViewById( R.id.check_box );
            btnsel=(Button)itemView.findViewById( R.id.btnselect );



        }



    }

    @SuppressLint("NotifyDataSetChanged")
    public  void  filterlist(ArrayList<Email> filteredlist)
    {
        ModelArrayList=filteredlist;
        notifyDataSetChanged();
    }






}
