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

public class QuestionScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    List<QuestionInfo> questionList = new ArrayList<>();
    DatabaseReference reference;
    Map<String, Object> mapList = new HashMap<>();
    InfoAdapter infoAdapter;
    Context context = this;
    ProgressBar questionProg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_question_screen);
        questionProg =  findViewById(R.id.question_prog);
        reference = FirebaseDatabase.getInstance().getReference(Constants.alleries).child(Constants.commonQA);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionList.clear();
                if(snapshot.exists()) {
                    questionProg.setVisibility(View.INVISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        mapList.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        String question = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.question).toString();
                        String answer = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.answer).toString();
                        String id = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.id).toString();
                        QuestionInfo q = new QuestionInfo(Integer.parseInt(id), question, answer);
                        questionList.add(q);
                    }
                }
                infoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView = findViewById(R.id.list_question);
        infoAdapter = new InfoAdapter(questionList, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(infoAdapter);
    }
}