package com.example.sangameswaran.ftccegteam_a;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
 * Created by Sangameswaran on 02-03-2017.
 */

public class writeMessageActivity extends AppCompatActivity implements View.OnClickListener{
    EditText e2;
    TextView e1;
    TextView btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_message_layout);
        e1=(TextView) findViewById(R.id.ideditText);
        e2=(EditText)findViewById(R.id.editText2);
        btn=(TextView) findViewById(R.id.sendbtn);

        btn.setOnClickListener(this);


        SharedPreferences sp=getSharedPreferences("myLoginid",MODE_PRIVATE);
        String b=sp.getString("myID","Login please");
        e1.setText(""+b);
        //views complete job write into database
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.sendbtn)
        {
            //send button pressed..send the message and update the messagecount
            //parse into object
           final    String s1,s2;
            s1=e1.getText().toString();
            s2=e2.getText().toString();
            if(s1.equals("")||s2.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Enter all the fields",Toast.LENGTH_LONG).show();
            }
            else
            {
                //updatecount


                DatabaseReference ref2;
                ref2=FirebaseDatabase.getInstance().getReference("MESSAGECOUNT");

                //final int[] a = new int
                // String s;

               // final int[] a = new int[1];
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {




                        String value=dataSnapshot.getValue(String.class);

                        int a =Integer.parseInt(value);
                      //  Toast.makeText(this,"val"+a,Toast.LENGTH_LONG).show();
                        //write into shared preferences
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

                //Toast.makeText(this,"b="+b,Toast.LENGTH_LONG).show();
                b++;
                //int a =Integer.parseInt();
                DatabaseReference myref1;
                myref1=FirebaseDatabase.getInstance().getReference("MESSAGECOUNT");
                //int b=  +1;

                myref1.setValue(""+b);
                DatabaseReference myref;
                myref= FirebaseDatabase.getInstance().getReference();


                ModelClass modelClass=new ModelClass(s1,s2);
                myref.child("MESSAGES/"+b).setValue(modelClass);



                Toast.makeText(getApplicationContext(),"Message sent",Toast.LENGTH_LONG).show();
            }


        }

    }
}
