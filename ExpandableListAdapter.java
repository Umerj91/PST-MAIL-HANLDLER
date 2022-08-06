package com.example.pstmailhandler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Folders> listDataHeader;
    private HashMap<Folders, List<Folders>> listDataChild;
    Fragment fragment;

    public ExpandableListAdapter(Context context, List<Folders> listDataHeader,
                                 HashMap<Folders, List<Folders>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public Folders getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).foldername;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
            TextView txtListChild = convertView
                    .findViewById(R.id.lblListHeader);

            txtListChild.setText(childText);

            txtListChild.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Mails.class);
                    intent.putExtra("fid",Integer.parseInt(  getChild(groupPosition, childPosition ).folderid) );
                    context.startActivity( intent );

                }
            } );
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();


    }

    @Override
    public Folders getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);

    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final String headerTitle = getGroup(groupPosition).foldername;
        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService( Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.lst_group_header, null);
            



        }


      //  Button btn=convertView.findViewById( R.id.btnid );
        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
//
        lblListHeader.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,((MainScreen) context).findViewById( R.id.btnselect ));
                popupMenu.inflate(R.menu.viewmenu);

                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mail_menu:
                                Intent in=new Intent(view.getContext(),Mails.class);
                                in.putExtra("fid",Integer.parseInt(  getGroup( groupPosition ).folderid) );    in.putExtra("fid",Integer.parseInt(  getGroup( groupPosition ).folderid) );
            //    in.putExtra("foldername",(getGroup( groupPosition ).foldername.toString()) );
                                in.putExtra( "folername",getGroup( groupPosition ).foldername.toString() );

                                view.getContext().startActivity( in );

                                context.startActivity( in );

                                break;
                            case R.id.rule_menu:
                                /////Move  opreation
                                Intent in1=new Intent(view.getContext(),Addnewrule.class);
                                in1.putExtra("fid",Integer.parseInt(  getGroup( groupPosition ).folderid) );
                                context.startActivity( in1 );
                                break;

                            default:
                        }
                        return false;
                    }
                } );
                popupMenu.show();
            }
        } );


//                Intent in=new Intent(view.getContext(),Mails.class);
//
//                in.putExtra("fid",Integer.parseInt(  getGroup( groupPosition ).folderid) );
//            //    in.putExtra("foldername",(getGroup( groupPosition ).foldername.toString()) );
//                in.putExtra( "folername",getGroup( groupPosition ).foldername.toString() );
//
//
//                view.getContext().startActivity( in );
//                Toast.makeText( view.getContext(), "Clicked..... ", Toast.LENGTH_SHORT ).show();



        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}