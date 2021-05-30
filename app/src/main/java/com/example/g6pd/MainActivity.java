package com.example.g6pd;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

        items.add(new Items("עוגית חלבון צימוקים", "בייק סיטי",  "food"));
        items.add(new Items("עוגית חלבון חמאת בוטנים", "בייק סיטי",  "food"));
        items.add(new Items("עוגית חלבון הוואית", "בייק סיטי",  "food"));
        items.add(new Items("כוסמת", "שוקוחה",  "food"));
        items.add(new Items("עדשים  ירוקות", "סולמני",  "food"));
        items.add(new Items("אפונה ירוקה", "סולמני",  "food"));
        items.add(new Items("אורז שירזין", "סולמני",  "food"));
        items.add(new Items("גזר ננסי מוקפא", "פרי גליל",  "food"));
        items.add(new Items("בורגול עבה", "ציפוריי",  "food"));
        items.add(new Items("תחליף שמנת 31%", "Flora",  "food"));
        items.add(new Items("פול", " ",  "food"));

        items.add(new Items("נפטלין", " ",  "pharm"));
        items.add(new Items("דפסון", " ",  "pharm"));
        items.add(new Items("אבלוסולפון", " ",  "pharm"));
        items.add(new Items("מתילון בלו", " ",  "pharm"));
        items.add(new Items("ניטרופורנטוין", " ",  "pharm"));
        items.add(new Items("מקרודנטין", " ",  "pharm"));
        items.add(new Items("יובאמין", " ",  "pharm"));
        items.add(new Items("פנזופירידן", " ",  "pharm"));
        items.add(new Items("סדורל", " ",  "pharm"));
        items.add(new Items("פרימקווין", " ",  "pharm"));
        items.add(new Items("רסבוריקאז", " ",  "pharm"));
        items.add(new Items("טולידין בלו", " ",  "pharm"));
        items.add(new Items("סולפרטים", " ",  "pharm"));
        items.add(new Items("דיספטיל", " ",  "pharm"));
        items.add(new Items("רספרים", " ",  "pharm"));
        items.add(new Items("ספרטין", " ",  "pharm"));
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
        }
        return super.onOptionsItemSelected(item);
    }
}