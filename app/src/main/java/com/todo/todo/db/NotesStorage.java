package com.todo.todo.db;

import android.support.annotation.NonNull;
import com.todo.todo.data.Note;
import io.realm.Realm;

import java.util.List;

public class NotesStorage {

    public static void save(@NonNull final List<Note> dataList) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dataList);
            }
        });
    }

    public static void markAsNotModified(@NonNull final List<Note> dataList) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Note note : dataList) {
                    Note realmNote = realm.where(Note.class).equalTo("id", note.getId()).findFirst();
                    if (realmNote != null) {
                        realmNote.setModified(false);
                    }
                }
            }
        });
    }

    @NonNull
    public static List<Note> getAllModified(@NonNull Realm realm) {
        return realm.where(Note.class).equalTo("isModified", true).findAll();
    }

    @NonNull
    public static List<Note> getAll(@NonNull Realm realm) {
        return realm.where(Note.class).findAll();
    }
}
