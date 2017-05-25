package com.example.sangameswaran.ftccegteam_a;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sangameswaran on 23-05-2017.
 */

public class ViewMechanismActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MechanismTrackerEntity> entities;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mechanism_tracker_layout);
        recyclerView=(RecyclerView)findViewById(R.id.mechanismtrackerRecyclerView);
        manager=new LinearLayoutManager(this);
        entities=new ArrayList<>();
        DatabaseReference m= FirebaseDatabase.getInstance().getReference("mechanismTracker");
        m.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    MechanismTrackerEntity entity = new MechanismTrackerEntity();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        entity = d.getValue(MechanismTrackerEntity.class);
                        entities.add(entity);
                        adapter = new MechanismTrackerRecyclerViewAdapter(entities,getBaseContext());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(manager);
                        adapter.notifyDataSetChanged();
                        recyclerView.setHasFixedSize(true);
                    }
                    Collections.reverse(entities);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"root not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Connectivity error",Toast.LENGTH_LONG).show();
            }
        });
        Collections.reverse(entities);
        adapter = new MechanismTrackerRecyclerViewAdapter(entities,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
