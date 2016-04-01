package com.todo.sync;

import android.content.Context;
import android.support.annotation.NonNull;
import com.todo.sync.event.SyncType;

class RemindersSync extends AbsSync {

    RemindersSync(@NonNull Context context) {
        super(context);
    }

    @Override
    protected SyncType getSyncType() {
        return SyncType.REMINDERS;
    }

    @Override
    protected void post() {
        // not implemented
    }

    @Override
    protected void get() {
        // not implemented
    }
}
