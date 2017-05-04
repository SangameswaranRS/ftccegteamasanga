package com.example.sangameswaran.ftccegteam_a;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Sangameswaran on 06-03-2017.
 */

public class write_memo_activity extends AppCompatActivity implements View.OnClickListener {

    TextView e1;
    //RadioGroup r1;
    TextView b1;
    EditText e2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_memo_layout);
        e1 = (TextView) findViewById(R.id.logintextview);
        //  r1=(RadioGroup)findViewById(R.id.memoradiogrp);
        b1 = (TextView) findViewById(R.id.continuebtn1);
        e2 = (EditText) findViewById(R.id.mem);
        b1.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("myLoginid", MODE_PRIVATE);
        String b = sp.getString("myID", "Login please");
        e1.setText("" + b);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.continuebtn1) {
           /* ModelClass modelClass=new ModelClass("faketest ID","Fake content");
            DatabaseReference reference;
            reference= FirebaseDatabase.getInstance().getReference();
            reference.child("MEMO/FTC01-DANISH/COUNT").setValue("0");
            reference.child("MEMO/FTC01-DANISH/MEMOS").setValue(modelClass);
            reference.child("MEMO/FTC02-SANGA/COUNT").setValue("0");
            reference.child("MEMO/FTC02-SANGA/MEMOS").setValue(modelClass);
            reference.child("MEMO/FTC03-ROHITH/COUNT").setValue("0");
            reference.child("MEMO/FTC03-ROHITH/MEMOS").setValue(modelClass);
            reference.child("MEMO/FTC04-BALAJI/COUNT").setValue("0");
            reference.child("MEMO/FTC04-BALAJI/MEMOS").setValue(modelClass);
            reference.child("MEMO/FTC05-PERVEEN/COUNT").setValue("0");
            reference.child("MEMO/FTC05-PERVEEN/MEMOS").setValue(modelClass);
            reference.child("MEMO/FTC06-AKILA/COUNT").setValue("0");
            reference.child("MEMO/FTC06-AKILA/MEMOS").setValue(modelClass);*/

            final String s1, s2;
            s1 = e1.getText().toString();
            s2 = e2.getText().toString();
            if (s1.equals("") || s2.equals("")) {
                Toast.makeText(getApplicationContext(), "Enter all the fields", Toast.LENGTH_LONG).show();
            } else {
                //fields entered ..parse them and send to firebase

                DatabaseReference ref2;
                ref2 = FirebaseDatabase.getInstance().getReference("MEMO/" + s1 + "/COUNT");

                //final int[] a = new int
                // String s;

                // final int[] a = new int[1];
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        String value = dataSnapshot.getValue(String.class);

                        int a = Integer.parseInt(value);
                        Log.d("TAAAAG", "HELLOOOOOOOOOO" + value);
                        //  Toast.makeText(this,"val"+a,Toast.LENGTH_LONG).show();
                        //write into shared preferences
                        SharedPreferences sp = getSharedPreferences("mycount"+s1, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("mymemoCount"+s1, a);
                        editor.commit();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //reading from shared preferences;
                SharedPreferences sp = getSharedPreferences("mycount"+s1, MODE_PRIVATE);
                int b = sp.getInt("mymemoCount"+s1, -1);

                Toast.makeText(this, "b=" + b, Toast.LENGTH_LONG).show();
                b++;
                //int a =Integer.parseInt();
                DatabaseReference myref1;
                myref1 = FirebaseDatabase.getInstance().getReference("MEMO/" + s1 + "/COUNT");
                //int b=  +1;

                myref1.setValue("" + b);
                DatabaseReference myref;
                myref = FirebaseDatabase.getInstance().getReference();


                ModelClass modelClass = new ModelClass(s1, s2);
                myref.child("MEMO/" + s1 + "/MEMOS/" + b).setValue(modelClass);


                Toast.makeText(getApplicationContext(), "Memo saved in cloud", Toast.LENGTH_LONG).show();

            }

        }

    }
}
