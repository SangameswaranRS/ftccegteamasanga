package com.example.sangameswaran.ftccegteam_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Sangameswaran on 06-03-2017.
 */

public class memo_recycler_activity extends AppCompatActivity implements View.OnClickListener
{

    RecyclerView recyclerView;
    SearchView memosearch;
    MessageRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<ModelClass> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_recycler_view);
        recyclerView=(RecyclerView)findViewById(R.id.memorecyclerview);

        memosearch=(SearchView) findViewById(R.id.memosearch);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        memosearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                ArrayList<ModelClass> newlist=new ArrayList<>();
                // ModelClass m=new ModelClass();
                for(ModelClass m : arrayList)
                {
                    if (m.getMessage().contains(query))
                    {
                        newlist.add(m);
                    }

                }
                adapter.fun(newlist);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        DatabaseReference ref;
        SharedPreferences sp1=getSharedPreferences("myLoginid",MODE_PRIVATE);
        String b1=sp1.getString("myID","Login please");


        Toast.makeText(getApplicationContext(),"fetching"+b1+" Memo",Toast.LENGTH_LONG).show();
        ref= FirebaseDatabase.getInstance().getReference("MEMO/"+b1+"/COUNT");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                int a=Integer.parseInt(value);
                SharedPreferences sp=getSharedPreferences("mycount" ,MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("mymemoCount",a);
                editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        SharedPreferences sp=getSharedPreferences("mycount",MODE_PRIVATE);
        int b=sp.getInt("mymemoCount",-1);
        DatabaseReference ref3;
        ref3=FirebaseDatabase.getInstance().getReference();
        for(int i=b;i>0;i--)
        {
            ref3=FirebaseDatabase.getInstance().getReference("MEMO/" + b1 + "/MEMOS/" +i);
            //add objects to the arraylist
            ref3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ModelClass m=new ModelClass();
                    m=dataSnapshot.getValue(ModelClass.class);

                    Log.d("HERE","MESS:"+m.getMessage());
                    arrayList.add(m);
                    adapter = new MessageRecyclerViewAdapter(arrayList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        adapter=new MessageRecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);



        if(arrayList.size()==b)
        adapter.notifyDataSetChanged();



    }

    @Override
    public void onClick(View v) {

    }

    /*@Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        ArrayList<ModelClass> newlist=new ArrayList<>();
       // ModelClass m=new ModelClass();
        for(ModelClass m : arrayList)
        {
            if (m.getMessage().contains(newText))
            {
                newlist.add(m);
            }
        }
        adapter=new MessageRecyclerViewAdapter(newlist);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return true;
    }*/
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

