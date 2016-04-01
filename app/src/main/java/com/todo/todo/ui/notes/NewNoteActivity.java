package com.todo.todo.ui.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.todo.todo.R;
import com.todo.todo.data.Note;
import com.todo.todo.db.NotesStorage;
import com.todo.todo.sync.SyncService;
import com.todo.todo.sync.event.SyncEvent;
import com.todo.todo.sync.event.SyncStatus;
import com.todo.todo.sync.event.SyncType;

import java.util.UUID;

public class NewNoteActivity extends AppCompatActivity {

    private EditText editNoteTitle;

    public static void start(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, NewNoteActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        initView();
    }

    private void initView() {
        editNoteTitle = (EditText) findViewById(R.id.editNoteTitle);

        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateNoteClicked();
            }
        });
    }

    private void onCreateNoteClicked() {
        String noteTitle = editNoteTitle.getText().toString();
        if (TextUtils.isEmpty(noteTitle)) {
            Toast.makeText(this, "Give me at least a title.", Toast.LENGTH_SHORT).show();
        } else {
            saveNewNote(noteTitle);
            SyncEvent.send(SyncType.NOTES, SyncStatus.COMPLETED); // tell all related screens to reload data from realm
            SyncService.request(SyncType.NOTES);
            finish();
        }
    }

    private void saveNewNote(@NonNull String title) {
        Note note = new Note();
        note.setId(UUID.randomUUID().toString());
        note.setTitle(title);
        NotesStorage.save(note);
    }
}
