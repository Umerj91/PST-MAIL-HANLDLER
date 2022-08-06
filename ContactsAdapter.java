package com.example.pstmailhandler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    public Context context;
    private ArrayList<Contacts> ModelArrayList;
    DBHelper dbHelper;

    public ContactsAdapter(Context context, ArrayList<Contacts> modelArrayList) {
        this.context = context;
        this.ModelArrayList = modelArrayList;
        dbHelper = new DBHelper(context);

    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context.getApplicationContext() ).inflate( R.layout.singlerowdesign,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        final Contacts model = ModelArrayList.get(position);
        final String cid =  model.getConid();
        String fname = model.getFnanme();
        String lname = model.getLname();
        String pic = model.getPicture();
        String email = model.getEmail();
        String  num = model.getNumber();
        String  accid = model.getAccid();
        String name=fname+" "+lname;
        holder.txtname.setText( name );
        holder.txtnum.setText( num );

        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),composemail.class);

                intent.putExtra( "sender",model.getEmail());

                context.startActivity(intent);
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
//        ImageButton clipmenu;
        // CheckBox c1;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txtname =(TextView)itemView.findViewById(R.id.t1);
            txtnum =(TextView)itemView.findViewById(R.id.t2);
            image =(ImageView) itemView.findViewById(R.id.img);
//            clipmenu = (ImageButton)itemView.findViewById(R.id.flowmenu);
            cardView =(CardView)itemView.findViewById(R.id.discarview);
            //  c1=(CheckBox) itemView.findViewById(R.id.chkbox);


        }



    }
}
