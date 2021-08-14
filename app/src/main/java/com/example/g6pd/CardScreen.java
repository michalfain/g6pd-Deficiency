package com.example.g6pd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CardScreen extends AppCompatActivity {
    Context context;
    CardView cvMain, cvContact, cvInfo, cvQuestions, cvAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_card_screen);
        context = this;
        cvMain = findViewById(R.id.main);
        cvContact = findViewById(R.id.contact);
        cvInfo = findViewById(R.id.info);
        cvQuestions = findViewById(R.id.questions);
        cvAboutUs = findViewById(R.id.about_us);

        cvMain.setOnClickListener(v -> goMain());
        cvContact.setOnClickListener(v -> goContact());
        cvInfo.setOnClickListener(v -> goInfo());
        cvQuestions.setOnClickListener(v -> goQuestion());
        cvAboutUs.setOnClickListener(v -> goAboutUs());
    }
    public void goMain(){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
    public void goContact(){
        Intent intent = new Intent(context, ContactUs.class);
        startActivity(intent);
    }
    public void goInfo(){
        Intent intent = new Intent(context, InfoScreen.class);
        startActivity(intent);
    }
    public void goQuestion(){
        Intent intent = new Intent(context, QuestionScreen.class);
        startActivity(intent);
    }
    public void goAboutUs(){
        Intent intent = new Intent(context, AboutUs.class);
        startActivity(intent);
    }
}