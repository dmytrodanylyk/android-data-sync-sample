package com.todo.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.Date;

public class Note extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private Date createdDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }
}
