package com.todo.todo.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.todo.todo.data.Note;
import com.todo.todo.utils.L;
import io.realm.Realm;

import java.util.List;

public class NotesStorage {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.allObjects(Note.class).clear();
            }
        });
    }

    public static void save(@NonNull final Note data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    public static void save(@NonNull final List<Note> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dataList);
            }
        });
    }

    public static void markAsNotModified(@NonNull final List<String> noteIdList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (String id : noteIdList) {
                    Note realmNote = realm.where(Note.class).equalTo("id", id).findFirst();
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

    private static void executeTransaction(@NonNull Realm.Transaction transaction) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(transaction);
        } catch (Throwable e) {
            L.e("executeTransaction", e);
        } finally {
            close(realm);
        }
    }

    private static void close(@Nullable Realm realm) {
        if (realm != null) {
            realm.close();
        }
    }
}
