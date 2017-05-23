package com.example.sangameswaran.ftccegteam_a;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by Sangameswaran on 23-05-2017.
 */

public class MechanismTrackerActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    TextView t1;
    Button b1;
    RadioGroup r;
    String Rstring="NoError";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanism_tracker_activity);
        e1=(EditText)findViewById(R.id.edit1);
        e2=(EditText)findViewById(R.id.edit2);
        e3=(EditText)findViewById(R.id.edit3);
        e4=(EditText)findViewById(R.id.edit4);
        t1=(TextView)findViewById(R.id.textView);
        r=(RadioGroup)findViewById(R.id.radiogroup2);
        b1=(Button)findViewById(R.id.submit_response);
        Calendar calendar=Calendar.getInstance();
        String timestamp=calendar.getTime().toString();
        t1.setText("Reporting time : "+timestamp);
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.conceptualerror)
                    Rstring="ConceptualError";
                else if(checkedId==R.id.implementationError)
                    Rstring="ImplementationError";
                else if(checkedId==R.id.noerror)
                    Rstring="NoError";
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1,s2,s3,s4;
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                if(s1.equals(""))
                    e1.setError("Required field");

                if(s2.equals(""))
                    e2.setError("Required field");

                if(s3.equals(""))
                    e3.setError("Required field");

                if(s4.equals(""))
                    e4.setError("Required field");

                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter all the fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Calendar calendar1=Calendar.getInstance();
                    String priority;
                    priority=computePriority(s3);
                    MechanismTrackerEntity entity=new MechanismTrackerEntity(calendar1.getTime().toString(),s1,s2,s3,Rstring,priority,s4);
                    DatabaseReference mdatabseReference= FirebaseDatabase.getInstance().getReference();
                    String key=mdatabseReference.child("mechanismTracker").push().getKey();
                    mdatabseReference.child("mechanismTracker").child(key).setValue(entity);
                    Toast.makeText(getApplicationContext(),"Entry recorded",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public  String computePriority(String percent)
    {
        int a;
        String s;
        try{
            a=Integer.parseInt(percent);
            if(a<=40)
            {
                 s="red";
                return s;
            }
            else
            {
                if(a>=41&&a<=70)
                {
                    s="yellow";
                    return s;
                }
                else
                {
                    s="green";
                    return s;
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Enter a valid Percentage",Toast.LENGTH_LONG).show();
        }
      return  "priority_not_defined";
    }
}
