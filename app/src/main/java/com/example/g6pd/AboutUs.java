package com.example.g6pd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUs extends AppCompatActivity {
    TextView tvRacheli, tvRinat, tvPressHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about_us);
        tvRacheli = findViewById(R.id.racheli_email);
        tvRinat = findViewById(R.id.rinat_email);
        tvPressHere = findViewById(R.id.press_here);
        tvRacheli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(Constants.racheliEmail);
            }
        });
        tvRinat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(Constants.rinatEmail);
            }
        });
        tvPressHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLink(Constants.fbLink);
            }
        });

    }
    private void goToLink(String link){
        Uri uri = Uri.parse(link);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));

    }
    void sendMail(String email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.setType("message/rfc822");
        startActivity(intent);
    }
}