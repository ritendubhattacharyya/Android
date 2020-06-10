package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static ListView notes;
    static List<String> lsnotes;
    static ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = (ListView) findViewById(R.id.listnote);

        lsnotes = new ArrayList<>();


        SharedPreferences sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notes", null);
        if(set==null) {
            lsnotes.add("ExampleNotes");
        }
        else {
            lsnotes = new ArrayList(set);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lsnotes);
        notes.setAdapter(arrayAdapter);
        notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditNotes.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });
        notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                lsnotes.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                HashSet<String> set = new HashSet<>(MainActivity.lsnotes);
                                editor.putStringSet("notes", set);
                                editor.apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .setCancelable(false);

                AlertDialog aler = builder.create();
                aler.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, EditNotes.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
