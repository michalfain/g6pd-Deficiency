package com.example.g6pd;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;
    List<Items> items = new ArrayList<Items>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        items.add(new Items("עוגית חלבון צימוקים", "בייק סיטי",  "food"));
        items.add(new Items("עוגית חלבון חמאת בוטנים", "בייק סיטי",  "food"));
        items.add(new Items("עוגית חלבון הוואית", "בייק סיטי",  "food"));
        items.add(new Items("כוסמת", "כוסמת",  "food"));
        items.add(new Items("עדשים  ירוקות", "סולמני",  "food"));
        items.add(new Items("אפונה ירוקה", "סולמני",  "food"));
        items.add(new Items("אורז שירזין", "סולמני",  "food"));
        items.add(new Items("גזר ננסי מוקפא", "פרי גליל",  "food"));
        items.add(new Items("בורגול עבה", "ציפוריי",  "food"));
        items.add(new Items("תחליף שמנת 31%", "Flora",  "food"));
        items.add(new Items("עוגית חלבון צימוקים", "בייק סיטי",  "food"));
        items.add(new Items("עוגית חלבון חמאת בוטנים", "בייק סיטי",  "food"));
        items.add(new Items("עוגית חלבון הוואית", "בייק סיטי",  "food"));
        items.add(new Items("כוסמת", "כוסמת",  "food"));
        items.add(new Items("עדשים  ירוקות", "סולמני",  "food"));
        items.add(new Items("אפונה ירוקה", "סולמני",  "food"));
        items.add(new Items("אורז שירזין", "סולמני",  "food"));
        items.add(new Items("גזר ננסי מוקפא", "פרי גליל",  "food"));
        items.add(new Items("בורגול עבה", "ציפוריי",  "food"));
        items.add(new Items("תחליף שמנת 31%", "Flora",  "food"));
        recyclerView = findViewById(R.id.list_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(items, this);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}