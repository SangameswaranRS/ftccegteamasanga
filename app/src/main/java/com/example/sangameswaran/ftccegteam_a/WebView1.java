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

public class WebView1 extends AppCompatActivity {
    WebView wb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_1);
        wb=(WebView)findViewById(R.id.wb1);
        wb.setWebChromeClient(new WebChromeClient());
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl("https://www.youtube.com/watch?v=MCQ_pgSw_-8");


    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
