package com.example.pstmailhandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Folderadapter extends RecyclerView.Adapter<Folderadapter.ViewHolder> {




    public Context context;

    private ArrayList<Rules> ModelArrayList;

    DBHelper dbHelper;






    public Folderadapter(Context context, ArrayList<Rules> modelArrayList) {
        this.context = context;
        this.ModelArrayList = modelArrayList;

        dbHelper = new DBHelper(context);

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context.getApplicationContext() ).inflate( R.layout.singlerowdesign,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public  void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        final Rules model = ModelArrayList.get(position);
        String Rid = String.valueOf( model.getRid() );

        String Rname = model.getRname();
        String fname = model.getFname();
        String sEmail = model.getSEmail();

        holder.txtMSub.setText( Rname );
        holder.txtMBody.setText( sEmail );


    }
    @Override
    public int getItemCount() {
        return ModelArrayList.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMSub,txtMBody;
        ImageView image;
        CardView cardView;
        // ImageButton clipmenu;
//        ImageView c1;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            txtMSub =(TextView)itemView.findViewById(R.id.t1);
            txtMBody =(TextView)itemView.findViewById(R.id.t2);

            // clipmenu = (ImageButton)itemView.findViewById(R.id.flowmenu);
            cardView =(CardView)itemView.findViewById(R.id.discarview);
//            c1= (ImageView) itemView.findViewById( R.id.check_box );



        }



    }


}
