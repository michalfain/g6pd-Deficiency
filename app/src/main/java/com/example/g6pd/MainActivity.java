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
import android.widget.Toast;
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
        RecyclerView recyclerView;
        List<Items> items = new ArrayList<Items>();
        DatabaseReference reference;
        Map<String, Object> mapList = new HashMap<>();
        AdapterClass adapterClass;
        Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference("Alleries").child("g6pd");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        mapList.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        String name = ((Map) (mapList.get(dataSnapshot.getKey()))).get("name").toString();
                        String company = ((Map) (mapList.get(dataSnapshot.getKey()))).get("company").toString();
                        String type = ((Map) (mapList.get(dataSnapshot.getKey()))).get("type").toString();
                        Items i = new Items(name, company, type, "no photo");
                        items.add(i);
                    }
                    adapterClass.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView = findViewById(R.id.list_item);
        adapterClass = new AdapterClass(items, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("חיפוש");
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
                    }
                }
                AdapterClass adapter = new AdapterClass(listItems, context);
                recyclerView.setAdapter(adapter);
            }
            else {
                recyclerView.setAdapter(adapterClass);
            }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contact_us:
                Intent intent = new Intent(this, ContactUs.class);
                startActivity(intent);
            case R.id.info:
                Intent intent2 = new Intent(this, InfoScreen.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}