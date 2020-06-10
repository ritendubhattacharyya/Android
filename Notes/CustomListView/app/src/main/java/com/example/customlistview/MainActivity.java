package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView characters;
    int images[] = {
            R.drawable.blackpanther, R.drawable.captain_america,
            R.drawable.falcon, R.drawable.hulk,
            R.drawable.ironman, R.drawable.thor};
    String names[];
    customAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        characters = (ListView) findViewById(R.id.characters);
        names = getResources().getStringArray(R.array.name);
        int i = 0;
        adapter = new customAdapter(this, R.layout.custom_list);
        characters.setAdapter(adapter);
        for(String text: names) {
            dataProvider dp = new dataProvider(images[i],text);
            adapter.add(dp);
            i++;
        }


    }
}
