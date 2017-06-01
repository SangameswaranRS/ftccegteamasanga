package com.example.sangameswaran.ftccegteam_a;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<IntroModelClass> setter=new ArrayList<>();
    String b1;
    ProgressDialog dialog;
    ProgressDialog loadGraph;
    AlertDialog.Builder alertdialog;
    GraphView g1,g2;
    ArrayList<MyDataPoint> overallDataPoints=new ArrayList<>();
    ArrayList<MyDataPoint> mechProgress=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertdialog=new AlertDialog.Builder(this);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadGraph=new ProgressDialog(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Initializing graph modules..");
        dialog.setCancelable(false);
        dialog.show();
        loadGraph.setMessage("Initializing graph modules..");
        loadGraph.setCancelable(false);
        loadGraph.show();
        IntroModelClass modelClass=new IntroModelClass(R.drawable.akila_akka,"Myself Akila..am doing my third year ECE in CEG. Quite talkative with Pleasant smile..Perfect extrovert");
        DatabaseReference ref;
        setter.add(modelClass);
        recyclerView=(RecyclerView)findViewById(R.id.introrecyclerview);
        g1=(GraphView)findViewById(R.id.mechperformance);
        g2=(GraphView)findViewById(R.id.techperformance);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter=new IntroRecyclerViewAdapter(setter);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        SharedPreferences sp1=getSharedPreferences("myLoginid",MODE_PRIVATE);
         b1=sp1.getString("myID","Login please");
        DatabaseReference flagCheck=FirebaseDatabase.getInstance().getReference("login_flag_"+b1);
        flagCheck.setValue("1");
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DatabaseReference getMechDataPoints=FirebaseDatabase.getInstance().getReference("mechDataPoints");
        getMechDataPoints.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())  {
                    MyDataPoint dataPoint=new MyDataPoint();
                    for(DataSnapshot ds :dataSnapshot.getChildren())
                    {
                         dataPoint=ds.getValue(MyDataPoint.class);
                        mechProgress.add(dataPoint);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Progress doesnot exist",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference getOverallDataPoints=FirebaseDatabase.getInstance().getReference("overallDataPoints");
        getOverallDataPoints.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren())
                {
                    MyDataPoint dataPoint=new MyDataPoint();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        dataPoint=ds.getValue(MyDataPoint.class);
                        overallDataPoints.add(dataPoint);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Progress doesnot exist",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                BarGraphSeries<DataPoint> series=new BarGraphSeries<>();
                series.appendData(new DataPoint(24,45),true,100);
                Log.d("knsdbnldsf",mechProgress.toString());
                for(MyDataPoint dataPoint:mechProgress)
                {
                    int a=Integer.parseInt(dataPoint.current_date);
                    int b=Integer.parseInt(dataPoint.date_progress);
                    Log.d("tag   hv",""+a+b);
                    series.appendData(new DataPoint(a,b),true,100);
                }
                loadGraph.dismiss();
                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        if(data.getY()>=70)
                        {
                            return Color.parseColor("#309229");
                        }
                        else if(data.getY()>=30&&data.getY()<=70)
                        {
                            return Color.parseColor("#ffd001");
                        }

                        return Color.parseColor("#ff0000");
                    }
                });

                series.setSpacing(50);
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.parseColor("#0660f1"));
                g1.addSeries(series);
                g1.getViewport().setScalable(true);
                g1.getViewport().setScrollable(true);
                g1.getViewport().setScalableY(true);
                g1.getViewport().setScrollableY(true);
                g1.addSeries(series);
                loadGraph.show();
                BarGraphSeries<DataPoint> series2=new BarGraphSeries<>();
                series2.appendData(new DataPoint(24,37),true,100);
                Log.d("knsdbnldsf",overallDataPoints.toString());
                for(MyDataPoint dataPoint:overallDataPoints)
                {
                    int a=Integer.parseInt(dataPoint.current_date);
                    int b=Integer.parseInt(dataPoint.date_progress);
                    Log.d("tag   hv",""+a+b);
                    series2.appendData(new DataPoint(a,b),true,100);
                }
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        if(data.getY()>=70)
                        {
                            return Color.parseColor("#309229");
                        }
                        else if(data.getY()>=30&&data.getY()<=70)
                        {
                            return Color.parseColor("#ffd001");
                        }

                        return Color.parseColor("#ff0000");
                    }
                });
                loadGraph.dismiss();
                series2.setSpacing(50);
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.parseColor("#0660f1"));
                g2.addSeries(series2);
                g2.getViewport().setScalable(true);
                g2.getViewport().setScrollable(true);
                g2.getViewport().setScalableY(true);
                g2.getViewport().setScrollableY(true);


                g2.addSeries(series2);
            }
        },5000);
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
                    DatabaseReference flagCheck=FirebaseDatabase.getInstance().getReference("login_flag_"+b1);
                    flagCheck.setValue("0");
                    finishAffinity();

                }
            });
            alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertdialog.show();
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
        else if(id==R.id.mech_tracker)
        {
            Intent intent=new Intent(this,MechanismTrackerActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.viewprogress)
        {
            Intent intent=new Intent(this,ViewMechanismActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.retreive)
        {
            Intent intent=new Intent(this,AdminEntry.class);
            startActivity(intent);
        }
        else if(id==R.id.viewOurWebsite)
        {
            Intent intent=new Intent(this,WebView2.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
