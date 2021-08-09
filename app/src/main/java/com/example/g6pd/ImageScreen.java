package com.example.g6pd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class ImageScreen extends AppCompatActivity {
    ImageView iv;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_screen);
        Intent intent = getIntent();
       String photo = intent.getStringExtra(Constants.photoUrl);
        iv = findViewById(R.id.iv);
        Glide.with(context).load(photo).into(iv);
    }
}