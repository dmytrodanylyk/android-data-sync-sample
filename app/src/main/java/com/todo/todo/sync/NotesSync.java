package com.todo.todo.sync;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.todo.todo.data.Note;
import com.todo.todo.db.NotesStorage;
import com.todo.todo.sync.event.SyncType;
import com.todo.todo.utils.L;
import io.realm.Realm;

import java.util.*;
import java.util.concurrent.TimeUnit;

class NotesSync extends AbsSync {

    NotesSync(@NonNull Context context) {
        super(context);
    }

    @Override
    protected SyncType getSyncType() {
        return SyncType.NOTES;
    }

    @Override
    protected void post() {
        Realm realm = Realm.getDefaultInstance();
        List<Note> noteList = NotesStorage.getAllModified(realm);
        if (!noteList.isEmpty()) {
            L.v("Notes POST request start");
            L.v("%d modified items need to be uploaded to server", noteList.size());
            log(noteList);
            simulateRequestDelay();
            NotesStorage.markAsNotModified(noteList);
            L.v("Notes POST request end");
        }
        realm.close();
    }

    @Override
    protected void get() {
        L.v("Notes GET request start");
        List<Note> noteList = generateNoteItems();
        L.v("%d new items available", noteList.size());
        log(noteList);
        simulateRequestDelay();
        NotesStorage.save(noteList);
        L.v("Notes GET request end");
    }

    @NonNull
    private List<Note> generateNoteItems() {
        Random random = new Random();
        int itemsCount = random.nextInt(5);
        List<Note> noteList = new ArrayList<>(itemsCount);
        for (int i = 0; i < itemsCount; i++) {
            String title = String.format("Random note %s", new Date().toString());
            Note note = new Note();
            note.setId(UUID.randomUUID().toString());
            note.setTitle(title);
            noteList.add(note);

        }
        return noteList;
    }

    private void log(List<Note> noteList) {
        for (Note note : noteList) {
            L.v("Note id: %s Title: %s IsModified: %s", note.getId(), note.getTitle(), note.isModified());
        }
    }

    private void simulateRequestDelay() {
        Random random = new Random();
        long millis = TimeUnit.SECONDS.toMillis(5);
        SystemClock.sleep(random.nextInt((int) millis));
    }

}
