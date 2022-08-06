package com.example.pstmailhandler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {
    public Context context;
    private ArrayList<Accounts> ModelArrayList;
    DBHelper dbHelper;

    public AccountsAdapter(Context context, ArrayList<Accounts> modelArrayList) {
        this.context = context;
        this.ModelArrayList = modelArrayList;
        dbHelper = new DBHelper(context);

    }

    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context.getApplicationContext() ).inflate( R.layout.accountsinglerow,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AccountsAdapter.ViewHolder holder, int position) {
        final Accounts model = ModelArrayList.get(position);
       // final String cid =  model.getAccid();
        String accemail = model.getAccemail();
        String acctype = model.getAcctype();
//        String pic = model.getPicture();
//        String email = model.getEmail();
//        String  num = model.getNumber();
//        String  accid = model.getAccid();
        //String name=fname+""+lname;
        holder.txtname.setText( accemail );
        holder.txtnum.setText( acctype );

        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MainScreen.class);
                intent.putExtra("Aid",model.getAccid() );
                intent.putExtra("Accemail",model.getAccemail() );
                intent.putExtra("Acctype",model.getAcctype() );

                context.startActivity(intent);

            }
        } );


        holder.clipmenu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,((AddNewAccount) context).findViewById( R.id.flowmenu ));
                popupMenu.inflate(R.menu.sync_menu);

                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.sync_menu:
                                Intent in=new Intent(context,ClientConnectivity.class);
                                context.startActivity( in );
                                break;
                            default:
                        }
                        return false;
                    }
                } );
                popupMenu.show();
            }
        } );

    }

    @Override
    public int getItemCount() {
        return ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtname,txtnum;
        ImageView image;
        CardView cardView;
        ImageButton clipmenu;
        // CheckBox c1;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txtname =(TextView)itemView.findViewById(R.id.t1);
            txtnum =(TextView)itemView.findViewById(R.id.t2);
            image =(ImageView) itemView.findViewById(R.id.img);
            clipmenu = (ImageButton)itemView.findViewById(R.id.flowmenu);
            cardView =(CardView)itemView.findViewById(R.id.discarview);
            //  c1=(CheckBox) itemView.findViewById(R.id.chkbox);


        }



    }
}
