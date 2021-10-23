package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    SearchView mysearchview;
    RecyclerView rvCountries;
    ListView lvAlphabets;
    CountryAdapter adapter;
    String[] countryName = new String[]{"India", "Egypt","Cuba","Vetican city","Burundi","Chile","Chad","Bhutan", "America", "Greenland", "France", "Australia", "Zimbawe", "China", "Japan", "North korea", "South Korea", "Tajakistan", "Afghanistan", "Azerbhaijan", "Mangolia", "Switzerland", "Bhutan", "Mynamar "};
    int[] indexOfAlphabet = new int[26];
    ArrayList<CountryData> countryDataList = new ArrayList<CountryData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysearchview = (SearchView) findViewById(R.id.search);
        rvCountries = findViewById(R.id.rvCountries);
        lvAlphabets = findViewById(R.id.lvAlphabets);

        for (int i = 0; i < countryName.length; i++) {
            CountryData countryData = new CountryData(countryName[i], R.drawable.ic_search_black_24dp);
            countryDataList.add(countryData);
        }

        Collections.sort(countryDataList, (v1, v2) -> v1.countryName.compareTo(v2.countryName));

        setIndexToAlphabets();

        adapter = new CountryAdapter(this, countryDataList, indexOfAlphabet);

        rvCountries.setLayoutManager(new LinearLayoutManager(this));
        rvCountries.setAdapter(adapter);

        lvAlphabets.setAdapter(new AlphabetsAdapter(position -> {
            rvCountries.getLayoutManager().scrollToPosition(indexOfAlphabet[position] + 1);
        }));
    }

    private void setIndexToAlphabets() {

        // using index array to count the items starting with particular character
        indexOfAlphabet[0] = 0;
        for (CountryData data : countryDataList) {
            char ch = data.countryName.charAt(0);
            int pos;
            if (Character.isUpperCase(ch)) {
                pos = ch - 'A';
            } else {
                pos = ch - 'a';
            }

            if (pos < 25) {
                indexOfAlphabet[pos + 1]++;
            }
        }

        // indexing to set position of particular character
        countryDataList.add(0, new CountryData("A", R.drawable.india));
        for(int i = 1; i < indexOfAlphabet.length; i++){
            indexOfAlphabet[i] = indexOfAlphabet[i - 1] + indexOfAlphabet[i] + 1;
        }

        for(int i = 1; i < indexOfAlphabet.length; i++){
            char ch = (char)('A' + i);
            String s = "" + ch;
            countryDataList.add(indexOfAlphabet[i] ,new CountryData(s, R.drawable.india));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mysearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);


    }

}

