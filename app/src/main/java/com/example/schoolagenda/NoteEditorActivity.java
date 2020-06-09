package com.example.schoolagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText ediText = (EditText) findViewById(R.id.editTextTextMultiLine);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1){
            ediText.setText(NotesActivity.notes.get(noteId));
        }
        else {
            NotesActivity.notes.add("");
            noteId = NotesActivity.notes.size()-1;
            NotesActivity.arrayAdapter.notifyDataSetChanged();
        }

        ediText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NotesActivity.notes.set(noteId, String.valueOf(s));
                NotesActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.schoolagenda", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(NotesActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}