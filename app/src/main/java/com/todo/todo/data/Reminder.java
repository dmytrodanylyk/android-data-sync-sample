package com.todo.todo.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Reminder extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private boolean isModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }
}
