package com.example.sangameswaran.ftccegteam_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Sangameswaran on 05-03-2017.
 */

public class LoginCommonActivity extends AppCompatActivity implements View.OnClickListener{

    EditText e1,e2;
    TextView b1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.common_login_activity_layout);

        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);

        b1=(TextView) findViewById(R.id.button2);
        b1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        if (id==R.id.button2) {
            //save button is pressed.. write data into shared preferences
           final String s1, s2;
            s1 = e1.getText().toString();
            s2 = e2.getText().toString();

            if ( s2.equals("") || s1.equals(""))
            {
                b1.setText("          Retry          ");
                Toast.makeText(getApplicationContext(), "Enter all the fields", Toast.LENGTH_LONG).show();
            } else {
                DatabaseReference ref2;
                ref2 = FirebaseDatabase.getInstance().getReference("MEMO/" + s1 + "/COUNT");

                //final int[] a = new int
                // String s;

                // final int[] a = new int[1];
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChildren()) {
                            String value = dataSnapshot.getValue(String.class);

                            int a = Integer.parseInt(value);
                            Log.d("TAAAAG", "HELLOOOOOOOOOO" + value);
                            //  Toast.makeText(this,"val"+a,Toast.LENGTH_LONG).show();
                            //write into shared preferences
                            SharedPreferences sp = getSharedPreferences("mycount" + s1, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("mymemoCount" + s1, a);
                            editor.commit();

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                    //passwords match ..authenticate and store them
                    if ((s1.equals("FTC01-DANISH") && s2.equals("danishahmed")) || (s1.equals("FTC02-SANGA") && s2.equals("sangakrish")) || (s1.equals("FTC03-ROHITH") && s2.equals("rohith")) || (s1.equals("FTC04-BALAJI") && s2.equals("balajiganesh")) || (s1.equals("FTC05-PERVEEN") && s2.equals("perveen")) || (s1.equals("FTC06-AKILA") && s2.equals("akila"))) {
                        Toast.makeText(getApplicationContext(), "Authentication successful", Toast.LENGTH_LONG).show();
                        SharedPreferences sp = getSharedPreferences("myLoginid", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("myID", s1);
                        editor.commit();
                        SharedPreferences sp1 = getSharedPreferences("myLoginpw", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sp1.edit();
                        editor1.putString("myPW", s2);
                        editor1.commit();
                        //Toast.makeText(getApplicationContext(), "Data storage successful", Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(this,MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Authentication Error", Toast.LENGTH_LONG).show();
                        b1.setText("          Retry          ");

                    }


            }
        }
    }
}
