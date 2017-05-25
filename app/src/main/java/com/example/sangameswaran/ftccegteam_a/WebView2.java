package com.example.sangameswaran.ftccegteam_a;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Sangameswaran on 12-03-2017.
 */

public class WebView2 extends AppCompatActivity{
    WebView wb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_2);
        wb=(WebView) findViewById(R.id.wb2);
        wb.setWebChromeClient(new WebChromeClient());
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl("https://console.firebase.google.com/project/p2pnavview-3519c/database/data/");
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
