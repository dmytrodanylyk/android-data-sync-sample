package com.todo.todo.sync;

import android.content.Context;
import android.support.annotation.NonNull;
import com.todo.todo.sync.event.SyncType;

class RemindersSync extends AbsSync {

    RemindersSync(@NonNull Context context) {
        super(context);
    }

    @Override
    protected SyncType getSyncType() {
        return null;
    }

    @Override
    protected void post() {

    }

    @Override
    protected void get() {

    }
}
