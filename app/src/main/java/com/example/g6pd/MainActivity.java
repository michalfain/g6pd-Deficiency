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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(Constants.itemListTitle);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference(Constants.alleries).child(Constants.g6pd);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        mapList.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        String name = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.name).toString();
                        String addInfo = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.addInfo).toString();
                        String company = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.company).toString();
                        String type = ((Map) (mapList.get(dataSnapshot.getKey()))).get(Constants.type).toString();
                        items.add(new Items(name, addInfo, company, type, Constants.noPhoto));

//                        if(i.type.equals("food")){
//                            foodList.add(i);
//                            foodAdapterClass.notifyDataSetChanged();
//                        }
//                        else {
//                            pharmList.add(i);
//                            pharmAdapterClass.notifyDataSetChanged();
//                        }
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
//        foodRecyclerView = findViewById(R.id.list_food);
//        foodAdapterClass = new AdapterClass(pharmList, context);
//        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        foodRecyclerView.setAdapter(foodAdapterClass);
//        pharmRecyclerView = findViewById(R.id.list_pharm);
//        pharmAdapterClass = new AdapterClass(pharmList, context);
//        pharmRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        pharmRecyclerView.setAdapter(pharmAdapterClass);
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