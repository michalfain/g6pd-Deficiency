package com.example.g6pd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemScreen extends AppCompatActivity {
    TextView tvName, tvCompany, tvDescription;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);
        tvName = findViewById(R.id.item_name);
        tvCompany = findViewById(R.id.item_company);
        tvDescription = findViewById(R.id.item_description);
        ivPhoto = findViewById(R.id.item_photo);
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvCompany.setText(intent.getStringExtra("company"));
        if(intent.getStringExtra("type") == "food"){
            ivPhoto.setImageResource(R.drawable.food);
        }else {
            ivPhoto.setImageResource(R.drawable.pharm);

        }
    }
}