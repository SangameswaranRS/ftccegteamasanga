package com.example.sangameswaran.ftccegteam_a;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<IntroModelClass> setter=new ArrayList<>();

    //ImageView im;
    ProgressDialog dialog;
    AlertDialog.Builder alertdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertdialog=new AlertDialog.Builder(this);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
        //im=(ImageView) findViewById(R.id.imagev);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading..Please wait");
        dialog.setCancelable(false);
        dialog.show();
        IntroModelClass modelClass=new IntroModelClass(R.drawable.akila_akka,"Myself Akila..am doing my third year ECE in CEG. Quite talkative with Pleasant smile..Perfect extrovert");
        DatabaseReference ref;
        setter.add(modelClass);
        recyclerView=(RecyclerView)findViewById(R.id.introrecyclerview);

        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter=new IntroRecyclerViewAdapter(setter);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);




        //im.setImageResource((modelClass.url));
        ref= FirebaseDatabase.getInstance().getReference("MESSAGECOUNT");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                int a=Integer.parseInt(value);
                SharedPreferences sp=getSharedPreferences("myShare",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("messageCount",a);
                editor.commit();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // Toast.makeText(getApplicationContext(),"Initialize Complete",Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Go Team CEG!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
      //         im.setImageResource(R.drawable.img_one);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            alertdialog.setMessage("Are you sure to exit?");
          //  alertdialog.show();
            alertdialog.setPositiveButton("Yes,Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();

                }
            });
            alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertdialog.show();
            //super.onBackPressed();
            //finishAffinity();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        //
        // TODO here the click handling.
        int id = item.getItemId();


        if(id==R.id.chatActivity)
        {

            Intent intent=new Intent(this,GroupChatActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.viewPersonalNoteActivity)
        {
            //inflate the personal note activity
            Intent intent=new Intent(this,write_memo_activity.class);
            startActivity(intent);
        }
        else if (id==R.id.home)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.viewp)
        {
            Intent intent=new Intent(this,memo_recycler_activity.class);
            startActivity(intent);


        }
        else if(id==R.id.menu5)
        {
            // inflate the web activity
            Intent intent=new Intent(this,WebView1.class);
            startActivity(intent);
        }
        else if (id==R.id.recentbot)
        {
            Intent intent=new Intent(this,UploadRetreive.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
