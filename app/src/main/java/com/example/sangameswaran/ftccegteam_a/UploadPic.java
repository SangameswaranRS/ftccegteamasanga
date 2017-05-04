package com.example.sangameswaran.ftccegteam_a;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sangameswaran on 21-04-2017.
 */

public class UploadPic extends AppCompatActivity implements View.OnClickListener{
    Button b1;
    Button download;
    ProgressDialog dialog;
    StorageReference reference;
    final int REQUEST_CODE=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadpic);
        b1=(Button) findViewById(R.id.uploadbtn);
        download=(Button)findViewById(R.id.downloadbtn);
        b1.setOnClickListener(this);
        download.setOnClickListener(this);
        reference= FirebaseStorage.getInstance().getReference();
        dialog=new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.uploadbtn)
        {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_CODE);

        }
        else if (id==R.id.downloadbtn)
        {
            dialog.setMessage("Downloading");
            dialog.show();

            //download and store it into local storage
            StorageReference reference3;
            reference3=reference.child("BOT17").child("r1");
            File localFile;

            try {
                localFile = File.createTempFile("ftc",".jpg");

                reference3.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Successfully Downloaded",Toast.LENGTH_LONG).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }




        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
        {
            StorageReference reference2=reference.child("BOT17").child("r1");

            Uri uri=data.getData();
            dialog.setMessage("Uploading..");
            dialog.show();
            reference2.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Upload Complete",Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}
