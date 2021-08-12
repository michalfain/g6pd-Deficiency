package com.example.g6pd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class ItemScreen extends AppCompatActivity {
    TextView tvName, tvCompany, tvDescription;
    ImageView ivPhoto;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_screen);
        tvName = findViewById(R.id.item_name);
        tvCompany = findViewById(R.id.item_company);
        tvDescription = findViewById(R.id.item_description);
        ivPhoto = findViewById(R.id.item_photo);
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra(Constants.name));
        tvCompany.setText(intent.getStringExtra(Constants.company));
        tvDescription.setText(intent.getStringExtra(Constants.addInfo));
        String photo = intent.getStringExtra(Constants.photoUrl);
        Glide.with(context).load(photo).into(ivPhoto);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageScreen.class);
                intent.putExtra(Constants.photoUrl, photo);
                context.startActivity(intent);
            }
        });

//        if(intent.getStringExtra(Constants.type) == Constants.food){
//            ivPhoto.setImageResource(R.drawable.food);
//        }else {
//            ivPhoto.setImageResource(R.drawable.pharm);
//
//        }
    }
}