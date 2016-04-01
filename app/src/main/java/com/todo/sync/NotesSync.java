package com.todo.sync;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.todo.data.Note;
import com.todo.db.NotesStorage;
import com.todo.sync.event.SyncType;
import com.todo.utils.L;
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
            L.d("Notes POST request start");
            L.v("%d modified items need to be uploaded to server", noteList.size());
            log(noteList);
            simulateRequestDelay();
            NotesStorage.markAsNotModified(mapToIdList(noteList));
            L.d("Notes POST request end");
        }
        realm.close();
    }

    @Override
    protected void get() {
        L.d("Notes GET request start");
        simulateRequestDelay();
        List<Note> noteList = generateNoteItems();
        L.v("%d new items available", noteList.size());
        log(noteList);
        NotesStorage.save(noteList);
        L.d("Notes GET request end");
    }

    @NonNull
    private List<Note> generateNoteItems() {
        Random random = new Random();
        int itemsCount = random.nextInt(4) + 1;
        List<Note> noteList = new ArrayList<>(itemsCount);
        for (int i = 0; i < itemsCount; i++) {
            Note note = new Note();
            note.setId(UUID.randomUUID().toString());
            note.setTitle("Random note from server");
            note.setCreatedDate(new Date());
            noteList.add(note);

        }
        return noteList;
    }

    private void log(List<Note> noteList) {
        for (Note note : noteList) {
            L.v("Note id: %s Title: %s Created date: %s IsModified: %s", note.getId(), note.getTitle(), note.getCreatedDate(),  note.isModified());
        }
    }

    private void simulateRequestDelay() {
        Random random = new Random();
        long millis = TimeUnit.SECONDS.toMillis(5);
        SystemClock.sleep(random.nextInt((int) millis));
    }

    @NonNull
    private List<String> mapToIdList(List<Note> noteList) {
        List<String> noteIdList = new ArrayList<>();
        for (Note note : noteList) {
            noteIdList.add(note.getId());
        }

        return noteIdList;
    }

}
