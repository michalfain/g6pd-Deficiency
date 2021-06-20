package com.example.g6pd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
    RecycleAdapter recycleAdapter;
        List<Items> items = new ArrayList<Items>();
        DatabaseReference reference;
        Map<String, Object> mapList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference("Alleries").child("g6pd");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    mapList.put(dataSnapshot.getKey(), dataSnapshot.getValue());
                        String name = ((Map)(mapList.get(dataSnapshot.getKey()))).get("name").toString();
                        String company = ((Map)(mapList.get(dataSnapshot.getKey()))).get("company").toString();
                        String type = ((Map)(mapList.get(dataSnapshot.getKey()))).get("type").toString();
                        Items i = new Items(name, company, type, "no photo");
                        items.add(i);
                }
                recycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        items.add(new Items("בורגול עבה", "ציפוריי",  "food"));
//        items.add(new Items("תחליף שמנת 31%", "Flora",  "food"));
//        items.add(new Items("פול", " ",  "food"));
//        items.add(new Items("סילברול קרם", " ",  "pharm"));

//        items.add(new Items("דפסון", " ",  "pharm"));
//        items.add(new Items("אבלוסולפון", " ",  "pharm"));
//        items.add(new Items("מתילון בלו", " ",  "pharm"));
//        items.add(new Items("ניטרופורנטוין", " ",  "pharm"));
//        items.add(new Items("מקרודנטין", " ",  "pharm"));
//        items.add(new Items("יובאמין", " ",  "pharm"));
//        items.add(new Items("פנזופירידן", " ",  "pharm"));
//        items.add(new Items("סדורל", " ",  "pharm"));
//        items.add(new Items("פרימקווין", " ",  "pharm"));
//        items.add(new Items("רסבוריקאז", " ",  "pharm"));
//        items.add(new Items("טולידין בלו", " ",  "pharm"));
//        items.add(new Items("סולפרטים", " ",  "pharm"));
//        items.add(new Items("דיספטיל", " ",  "pharm"));
//        items.add(new Items("רספרים", " ",  "pharm"));
//        items.add(new Items("ספרטין", " ",  "pharm"));
        recyclerView = findViewById(R.id.list_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(items, this);
        recyclerView.setAdapter(recycleAdapter);
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
                recycleAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, "I am settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.contact_us:
                Intent intent = new Intent(this, ContactUs.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}