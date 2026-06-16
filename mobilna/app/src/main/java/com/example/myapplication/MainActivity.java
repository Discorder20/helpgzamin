package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Note> notes = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.note_list);
        EditText titleInput = findViewById(R.id.note_title);
        EditText contentInput = findViewById(R.id.note_content);
        Button addButton = findViewById(R.id.add_note_button);

        noteAdapter = new NoteAdapter();
        listView.setAdapter(noteAdapter);

        addButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            String content = contentInput.getText().toString().trim();

            if (title.isEmpty() && content.isEmpty()) {
                return;
            }

            notes.add(new Note(title, content));
            noteAdapter.notifyDataSetChanged();
            titleInput.setText("");
            contentInput.setText("");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class NoteAdapter extends ArrayAdapter<Note> {

        NoteAdapter() {
            super(MainActivity.this, 0, notes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                row = inflater.inflate(R.layout.item_note, parent, false);
            }

            Note note = getItem(position);
            TextView titleView = row.findViewById(R.id.item);
            TextView contentView = row.findViewById(R.id.sub_item);

            if (note != null) {
                titleView.setText(note.title);
                contentView.setText(note.content);
            }

            return row;
        }
    }

    private static class Note {
        final String title;
        final String content;

        Note(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }
}