package com.example.sangameswaran.ftccegteam_a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



/**
 * Created by Sangameswaran on 02-03-2017.
 */

public class GroupChatActivity extends AppCompatActivity implements View.OnClickListener {

   // EditText e1,e2;
    //Button send;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<ModelClass> arrayList=new ArrayList<>();
    ProgressDialog d;

    EditText message;
    TextView sendit;
    TextView myname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_chat_activity_layout);
        d=new ProgressDialog(this);
        message=(EditText)findViewById(R.id.ideditTexet);
        sendit=(TextView) findViewById(R.id.sende);

        myname=(TextView)findViewById(R.id.myname);
        sendit.setOnClickListener(this);
      //  e1=(EditText)findViewById(R.id.ideditText);
        //e2=(EditText)findViewById(R.id.messageeditText);
        //send=(Button)findViewById(R.id.sendBtn);
        recyclerView=(RecyclerView)findViewById(R.id.MessageRecyclerView);

       // d.setMessage("Loading messages");
        //d.show();

        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        //copy from firebase database to arraylist

        DatabaseReference ref;


        SharedPreferences sp5=getSharedPreferences("myLoginid",MODE_PRIVATE);
        String b5=sp5.getString("myID","Login please");
        myname.setText(""+b5);
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
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        SharedPreferences sp=getSharedPreferences("myShare",MODE_PRIVATE);
        int b=sp.getInt("messageCount",-1);
        DatabaseReference ref3;
        ref3=FirebaseDatabase.getInstance().getReference();
         int i;
        Log.d("BVALUEEE","b"+b);
       for( i=b;i>0;i--) {
            ref3 = FirebaseDatabase.getInstance().getReference("MESSAGES/" + i);
            //add objects to the arraylist
            ref3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ModelClass m = new ModelClass();
                    m = dataSnapshot.getValue(ModelClass.class);

                    Log.d("HERE", "MESS:" + m.getMessage());
                    arrayList.add(m);
                    adapter = new MessageRecyclerViewAdapter(arrayList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                    //i--;


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            Log.d("IVALUE", "i=" + i+"size="+arrayList.size());

        }


                adapter = new MessageRecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        if(arrayList.size()==b) {
            adapter.notifyDataSetChanged();
            Log.d("DDDDDDDDDEEE", "set adapter full");

        }}



    @Override
    public void onClick(View v) {

        int id=v.getId();
        if(id==R.id.sende)
        {
            final    String s1,s2;
            s1=myname.getText().toString();
            s2=message.getText().toString();
            if(s1.equals("")||s2.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Enter message",Toast.LENGTH_LONG).show();
            }
            else
            {

                DatabaseReference ref2;
                ref2=FirebaseDatabase.getInstance().getReference("MESSAGECOUNT");
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value=dataSnapshot.getValue(String.class);
                        int a =Integer.parseInt(value);
                        SharedPreferences sp=getSharedPreferences("myShare",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putInt("messageCount",a);
                        editor.commit();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //reading from shared preferences;
                SharedPreferences sp=getSharedPreferences("myShare",MODE_PRIVATE);
                int b=sp.getInt("messageCount",-1);
                b++;
                DatabaseReference myref1;
                myref1=FirebaseDatabase.getInstance().getReference("MESSAGECOUNT");
                myref1.setValue(""+b);
                DatabaseReference myref;
                myref= FirebaseDatabase.getInstance().getReference();
                ModelClass modelClass=new ModelClass(s1,s2);
                myref.child("MESSAGES/"+b).setValue(modelClass);
                ArrayList<ModelClass> refresh=new ArrayList<>();
                refresh.add(modelClass);
                refresh.addAll(arrayList);
                Toast.makeText(getApplicationContext(),"Message sent",Toast.LENGTH_LONG).show();
                adapter = new MessageRecyclerViewAdapter(refresh);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                adapter.notifyDataSetChanged();
                message.setText("");
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
