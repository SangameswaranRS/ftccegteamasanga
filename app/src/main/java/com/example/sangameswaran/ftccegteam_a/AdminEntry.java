package com.example.sangameswaran.ftccegteam_a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by Sangameswaran on 25-05-2017.
 */

public class AdminEntry extends AppCompatActivity {

    TextView reportingTime,submitBtn;
    EditText mechprogress,overallprogress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_entry_layout);
        reportingTime=(TextView)findViewById(R.id.reportingtime);
        submitBtn=(TextView)findViewById(R.id.adminResponse);
        mechprogress=(EditText)findViewById(R.id.mechProgress);
        overallprogress=(EditText)findViewById(R.id.overallProgress);
        final Calendar calendar=Calendar.getInstance();
        String Currenttimestamp=calendar.getTime().toString();
        reportingTime.setText("REPORINTG TIME : "+Currenttimestamp);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mechprogressString,overallProgressString;
                mechprogressString=mechprogress.getText().toString();
                overallProgressString=overallprogress.getText().toString();
                if(mechprogressString.equals("")||overallProgressString.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter both the fields admin",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Calendar calendar1=Calendar.getInstance();
                    int date=calendar.get(Calendar.DATE);
                    int month=calendar.get(Calendar.MONTH);
                    if(month==Calendar.JUNE)
                        date+=31;
                    try{
                        int mechProgress=Integer.parseInt(mechprogressString);
                         int overallProgress=Integer.parseInt(overallProgressString);
                        if(mechProgress<=100&&overallProgress<=100&&mechProgress>0&&overallProgress>0)
                        {
                        DatabaseReference mechReference= FirebaseDatabase.getInstance().getReference();
                        MyDataPoint myDataPoint =new MyDataPoint(""+date,""+mechProgress);
                        String id=mechReference.child("mechDataPoints").push().getKey();
                        mechReference.child("mechDataPoints").child(""+date).setValue(myDataPoint);
                        DatabaseReference overallReference=FirebaseDatabase.getInstance().getReference();
                        MyDataPoint myDataPoint1 =new MyDataPoint(""+date,""+overallProgress);
                        String id2=overallReference.child("overallDataPoints").push().getKey();
                        overallReference.child("overallDataPoints").child(""+date).setValue(myDataPoint1);
                        Toast.makeText(getApplicationContext(),"Todays progress Recorded",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Enter a valid percentage",Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Exception in parsing percentage,Try again"+e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }

            }
        });



    }
}
