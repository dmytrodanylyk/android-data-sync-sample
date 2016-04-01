package com.todo.todo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.todo.todo.R;
import com.todo.todo.db.NotesStorage;
import com.todo.todo.sync.SyncService;
import com.todo.todo.ui.notes.NotesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SyncService.start(getApplicationContext());

        findViewById(R.id.btnNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.btnNotesClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesStorage.removeAll();
            }
        });
    }
}
