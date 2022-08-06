package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sentfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sentfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<Email> dataholder;
    private Button b1;
    private  DBHelper dbHelper;
    private Button btnnew;
    private  Button search;
    MyAdapter adapter;
    private  int id=3;

    public sentfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sentfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sentfragment newInstance(String param1, String param2) {
        sentfragment fragment = new sentfragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate( R.layout.fragment_sentfragment, container, false );



        setHasOptionsMenu(true);
        View view= inflater.inflate( R.layout.fragment_sentfragment,container, false );
        b1=(Button) view.findViewById( R.id.btncompose );
        btnnew=(Button) view.findViewById( R.id.btnnew );
        search=(Button) view.findViewById( R.id.btnsearch );
        // c1=(CheckBox )  view.findViewById( R.id.chkbox );
        recyclerView=view.findViewById( R.id.recview );
        dbHelper = new DBHelper(getContext());

        view.findViewById( R.id.discarview );

        adapter = new MyAdapter(getContext(),dbHelper.RecordsbyId(id));

        LinearLayoutManager manager = new LinearLayoutManager(container.getContext() );
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),composemail.class);
                startActivity(intent);


            }
        } );

        btnnew.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    openDialog();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


            }

        } );


        search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(getContext(),SearchActivity.class);
                    startActivity(intent);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }


            }

        } );

        return  view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.mymenu, menu);
        inflater.inflate(R.menu.menu2, menu);
        MenuItem item = menu.findItem( R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView searchView = (SearchView) item.getActionView();



        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MyAdapter myAdapter = new MyAdapter(getContext(), dbHelper.searchEmail(query));
                recyclerView.setAdapter(myAdapter);
                //  dbHelper.searchEmails( query );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MyAdapter myAdapter = new MyAdapter(getContext(), dbHelper.searchEmail(newText));
                recyclerView.setAdapter(myAdapter);
                //  dbHelper.searchEmails(newText);

                return true;
            }
        } );

    }
    public  void openDialog()
    {
        Example_Dialog example_dialog=new Example_Dialog();
        example_dialog.show(getParentFragmentManager(),"Example Dialog");

    }
}