package com.example.pstmailhandler;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MainScreen extends AppCompatActivity {
    /////

    String Accemail;
    String Acctype;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<Folders> listDataHeader;
    HashMap<Folders, List<Folders>> listDataChild;


    ////
    ArrayList<ModelMenu> mmlist;
    private DrawerLayout drawerLayout;
    private SearchView src;
    public MenuItem item;
    public int fid = 0;
    DBHelper dbh;
    ArrayList<Folders> fdetail;

    Button btnsel;
    MyAdapter myAdapter;
    String id;
    ExpandableListView exp;
 Button btnmainscreen;

    // boolean iscontextualmenu=false;
    public Toolbar toolbar;

    private void prepareListData() {
        listDataHeader = new ArrayList<Folders>();
        listDataChild = new HashMap<Folders, List<Folders>>();

        // Adding child data
        for (Folders f : fdetail) {
            List<String> fd=new ArrayList<>();
            listDataHeader.add( f);
            ArrayList<Folders> subfolders=dbh.FolderRecords(Integer.parseInt(f.folderid));

            if(subfolders.size()>0)
                listDataChild.put( f,subfolders);

        }
//        listDataHeader.add("Top 250");
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");

        // Adding child data
//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");
//
//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");
//
//        List<String> comingSoon = new ArrayList<String>();
//        comingSoon.add("2 Guns");
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_screen );
        View v = getLayoutInflater().inflate(R.layout.navheader,null);
        btnmainscreen=findViewById( R.id.allfolders );


        Intent i=getIntent();
        Bundle b=i.getExtras();
        id=b.getString( "Aid" );
        Accemail=b.getString( "Accemail" );
        Acctype=b.getString( "Acctype" );
        exp= (ExpandableListView) findViewById(R.id.lvExp);

//        Bundle bundle = getIntent().getBundleExtra( "AccountData" );
//        Accemail = bundle.getString( "AccEmail" );
//        Acctype = bundle.getString( "AccType" );


        btnmainscreen.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, AllFolders.class);

               // intent.putExtra("Aid",mo
               startActivity( intent );
            }
        } );
//
//



        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar(toolbar);
        btnsel=findViewById( R.id.btnselect );



        dbh = new DBHelper( this );
        //     dbh.FolderRecords();

        drawerLayout = findViewById( R.id.drawer_layout );

        expListView = (ExpandableListView) findViewById( R.id.lvExp );
        NavigationView navigationView = findViewById( R.id.nav_view );

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txtEmail);
        TextView navType = (TextView) headerView.findViewById(R.id.txtAcctype);
        navUsername.setText(Accemail);
        navType.setText( Acctype );
        ///
        fdetail = dbh.FolderRecordsById(Integer.parseInt( id ));
        mmlist = new ArrayList<>();
        final Menu menu = navigationView.getMenu();
        for (int in = 0; in < fdetail.size(); in++) {

            menu.add( fdetail.get( in ).foldername );
            //  menu.add(fdetail.get( i ).folderid );
            menu.getItem( in ).setIcon( R.drawable.ic_folder );
            ModelMenu men = new ModelMenu();
            men.id = Integer.parseInt( fdetail.get( in ).folderid );
            men.name = fdetail.get( in ).foldername;
            mmlist.add( men );

        }
        prepareListData();

        listAdapter = new ExpandableListAdapter( this, listDataHeader, listDataChild );

        // setting list adapter
        expListView.setAdapter( listAdapter );
        ////


//        final SubMenu subMenu = menu.addSubMenu("SubMenu Title");
//        for (int i = 1; i <= 2; i++) {
//            subMenu.add("SubMenu Item " + i);
//        }
        menu.clear();


        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String str = item.getTitle().toString();
                Optional<Folders> f = (Optional<Folders>) fdetail.stream().filter( x -> x.foldername.equalsIgnoreCase( str ) ).findFirst();

                Folders clicked = f.get();
                Fragment fragment = null;
                toolbar.setTitle( item.getTitle() );




                fragment = new junkfragment( Integer.parseInt( clicked.folderid ) );
                //search given item id in mmlist


//                switch (item.getItemId())
//                {
//                    case R.id.junk:
////                        fid=13;
//////                      dbh.RecordsbyId( fid );
//                        toolbar.setTitle("Junk");
//                        Toast.makeText( MainScreen.this, "Junk Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        fragment = new junkfragment();
//                        break;
//                    case R.id.setting:
//
//
//                        toolbar.setTitle("Settings");
//                        fragment = new settingsfragment();
//                        Toast.makeText( MainScreen.this, "Settings Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//                    case R.id.deleted:
//                        toolbar.setTitle("Deleted");
//                        fragment = new deletedfragment();
//                        Toast.makeText( MainScreen.this, "Deleted Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//
//                    case R.id.drafts:
////                        fid=0;
////                        dbh.RecordsbyId( fid );
//                        toolbar.setTitle("Drafts");
//                        fragment = new draftsfragment();
//                        Toast.makeText( MainScreen.this, "Drafts Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//
//                    case R.id.inbox:
////                        fid=12;
////                        dbh.RecordsbyId( fid );
//                        toolbar.setTitle("Inbox");
//                        fragment = new inboxfragment();
//                        Toast.makeText( MainScreen.this, "Inbox Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//
//                    case R.id.contacts:
//                        toolbar.setTitle("Contacts");
//                        Intent in=new Intent(MainScreen.this,ContactsActivity.class);
//                        startActivity( in );
//                        finish();
//                        Toast.makeText( MainScreen.this, "Contacts Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//
//
//                    case R.id.sent:
//                        toolbar.setTitle("Sent");
//                        fragment = new sentfragment();
//                        Toast.makeText( MainScreen.this, "Sent Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//
//                    case R.id.archive:
//                        toolbar.setTitle("Archive");
//                        fragment = new archivefragment();
//                        Toast.makeText( MainScreen.this, "Archive Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//
//                    case R.id.newaccount:
//                        // fragment = new BlankFragment();
//                        Intent in1=new Intent(MainScreen.this,AccountConfiguration.class);
//                        startActivity( in1 );
//                        finish();
//                        //Toast.makeText( MainActivity.this, "Home Fragment is Opened", Toast.LENGTH_SHORT ).show();
//                        break;
//                }



                loadFragment( fragment );
                drawerLayout.closeDrawer( GravityCompat.START );
                return true;
            }

        } );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( MainScreen.this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );

        drawerLayout.addDrawerListener( toggle );
        toggle.syncState();
        if (savedInstanceState == null) {
            loadFragment( new inboxfragment() );

        }
        //getSupportFragmentManager().beginTransaction().replace( R.id.drawer_layout ,new inboxfragment()).commit();

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace( R.id.framelayout, fragment ).commit();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen( GravityCompat.START )) {
            drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
//        inflater.inflate( R.menu.menu2,menu );
        inflater.inflate( R.menu.delete_menu,menu );

//          item=menu.findItem( R.id.search1 );
//
//          item.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
//              @Override
//              public boolean onMenuItemClick(MenuItem menuItem) {
//                  Intent intent=new Intent(MainScreen.this,SearchActivity.class);
//                  startActivity(intent);
//                  return false;
//              }
//          } );

        return super.onCreateOptionsMenu( menu );
    }

}
