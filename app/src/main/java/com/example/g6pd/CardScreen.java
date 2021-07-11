package com.example.g6pd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CardScreen extends AppCompatActivity {
    Context context;
    CardView cvMain, cvContact, cvInfo, cvQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_screen);
        context = this;
        cvMain = findViewById(R.id.main);
        cvContact = findViewById(R.id.contact);
        cvInfo = findViewById(R.id.info);
        cvQuestions = findViewById(R.id.questions);
        cvMain.setOnClickListener(v -> goMain());
        cvContact.setOnClickListener(v -> goContact());
        cvInfo.setOnClickListener(v -> goInfo());
        cvQuestions.setOnClickListener(v -> goQuestion());
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
}