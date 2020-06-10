package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditNotes extends AppCompatActivity {
    EditText editnotes;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        editnotes = (EditText)findViewById(R.id.editnotes);
        Intent intent = getIntent();
        id = intent.getIntExtra("noteId", -1);
        if(id != -1)
        {
            editnotes.setText(MainActivity.lsnotes.get(id));
        }
        else
        {
            MainActivity.lsnotes.add("");
            id = MainActivity.lsnotes.size() - 1;
        }
        editnotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.lsnotes.set(id, toString().valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                HashSet<String> set = new HashSet<>(MainActivity.lsnotes);
                editor.putStringSet("notes", set);
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
