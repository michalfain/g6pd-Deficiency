package com.example.g6pd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    List<QuestionInfo> questionInfoList = new ArrayList<>();
    DatabaseReference reference;
    Map<String, Object> mapList = new HashMap<>();
    InfoAdapter infoAdapter;
    Context context = this;
    ProgressBar infoProg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info_screen);
        infoProg = findViewById(R.id.info_prog);
        reference = FirebaseDatabase.getInstance().getReference(Constants.alleries).child(Constants.commonInfo);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionInfoList.clear();
                if(snapshot.exists()) {
                    infoProg.setVisibility(View.INVISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        mapList.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        String question = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.question).toString();
                        String answer = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.answer).toString();
                        String id = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.id).toString();
                        QuestionInfo q = new QuestionInfo(Integer.parseInt(id), question, answer);
                        questionInfoList.add(q);
                    }
                }
                infoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView = findViewById(R.id.list_info);
        infoAdapter = new InfoAdapter(questionInfoList, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(infoAdapter);
    }
}