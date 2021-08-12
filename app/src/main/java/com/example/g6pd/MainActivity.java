package com.example.g6pd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
        RecyclerView foodRecyclerView;
        RecyclerView listRecyclerView;
        RecyclerView pharmRecyclerView;
        List<Items> items = new ArrayList<Items>();
        List<Items> foodList = new ArrayList<Items>();
        List<Items> pharmList = new ArrayList<Items>();
        DatabaseReference reference;
        Map<String, Object> mapList = new HashMap<>();
        AdapterClass foodAdapterClass;
        AdapterClass listAdapterClass;
        AdapterClass pharmAdapterClass;
        Context context = this;
        ProgressBar mainProg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(Constants.itemListTitle);
        setContentView(R.layout.activity_main);
        mainProg = findViewById(R.id.main_prog);
        reference = FirebaseDatabase.getInstance().getReference(Constants.alleries).child(Constants.g6pd);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                if(snapshot.exists()) {
                    mainProg.setVisibility(View.INVISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        mapList.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        String name = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.name).toString();
                        String addInfo = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.addInfo).toString();
                        String company = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.company).toString();
                        String type = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.type).toString();
                        String photo = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.photoUrl).toString();
                        items.add(new Items(name, addInfo, company, type, photo));
                    }
                }
                listAdapterClass.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listRecyclerView = findViewById(R.id.items);
        listAdapterClass = new AdapterClass(items, context);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listRecyclerView.setAdapter(listAdapterClass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint(Constants.search);
            searchView.setOnQueryTextListener
                    (new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            searchList(newText);
                            return true;
                        }
                    });
        return true;
        }
        private void searchList(String query) {
            if (query != null) {
                List<Items> listItems = new ArrayList();
                for (Items object : items) {
                    if (object.name.toLowerCase().contains(query.toLowerCase())) {
                        listItems.add(object);
//                        pharmRecyclerView.setVisibility(View.INVISIBLE);
                    }
                }
                AdapterClass adapter = new AdapterClass(listItems, context);
                listRecyclerView.setAdapter(adapter);

            }
            else {
                listRecyclerView.setAdapter(listAdapterClass);
//                foodRecyclerView.setAdapter(foodAdapterClass);
//                pharmRecyclerView.setVisibility(View.VISIBLE);
            }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contact_us:
                Intent intent = new Intent(this, ContactUs.class);
                startActivity(intent);
                break;
            case R.id.info:
                Intent intent2 = new Intent(this, InfoScreen.class);
                startActivity(intent2);
                break;
            case  R.id.question:
                Intent intent3 = new Intent(this, QuestionScreen.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}