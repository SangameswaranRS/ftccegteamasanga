package com.example.sangameswaran.ftccegteam_a;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Sangameswaran on 28-04-2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token= FirebaseInstanceId.getInstance().getToken();
//        Toast.makeText(getApplicationContext(),"Token generated",Toast.LENGTH_LONG).show();
        //DatabaseReference myref= FirebaseDatabase.getInstance().getReference();
        //myref.child("TokenRegister").push().setValue(""+token);
    }
}
