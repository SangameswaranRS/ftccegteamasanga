package com.example.sangameswaran.ftccegteam_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class chatAuthorizerActivity extends AppCompatActivity implements View.OnClickListener{
    TextView t1;
    RadioGroup rdgp;
    TextView b1;
    String controlstring="displaymessage";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_authorizer_layout);
        t1=(TextView)findViewById(R.id.displayname);
        rdgp=(RadioGroup)findViewById(R.id.messageradio);
        b1=(TextView) findViewById(R.id.continuebtn);
        b1.setOnClickListener(this);
        SharedPreferences sp=getSharedPreferences("myLoginid",MODE_PRIVATE);
        String b=sp.getString("myID","Login please");
        DatabaseReference ref;

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        t1.setText(""+b);
        rdgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.writemessagebtn)
                {
                    controlstring="writemessage";
                }
                else if(checkedId==R.id.displaymessagebtn)
                {
                    controlstring="displaymessage";
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.continuebtn)
        {
            //set the text view first and authenticate

            //get from shared preferences;


            String s1=t1.getText().toString();

            if (s1.equals("")||s1.equals("Login please"))
            {
                Toast.makeText(getApplicationContext(),"Authentication Error",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Access granted",Toast.LENGTH_LONG).show();
                if(controlstring.equals("displaymessage"))
                {
                    Intent intent=new Intent(this,GroupChatActivity.class);
                    startActivity(intent);
                }
                else if (controlstring.equals("writemessage"))
                {
                    Intent intent=new Intent(this,writeMessageActivity.class);
                    startActivity(intent);
                }
            }

        }
    }
}
