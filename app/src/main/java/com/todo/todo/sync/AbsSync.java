package com.todo.todo.sync;

import android.content.Context;
import android.support.annotation.NonNull;
import com.todo.todo.sync.event.SyncEvent;
import com.todo.todo.sync.event.SyncStatus;
import com.todo.todo.sync.event.SyncType;
import com.todo.todo.utils.NetworkUtils;

abstract class AbsSync {

    private Context context;

    public AbsSync(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    void sync() {
        if (NetworkUtils.isNetworkConnected(context)) {
            SyncEvent.send(getSyncType(), SyncStatus.IN_PROGRESS);
            post();
            get();
            SyncEvent.send(getSyncType(), SyncStatus.COMPLETED);
        }
    }

    protected abstract SyncType getSyncType();

    protected abstract void post();
    protected abstract void get();

}
