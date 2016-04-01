package com.todo.sync.event;

import android.support.annotation.NonNull;

public class SyncRequestEvent {

    private SyncType syncType;

    public SyncRequestEvent(@NonNull SyncType syncType) {
        this.syncType = syncType;
    }

    public SyncType getSyncType() {
        return syncType;
    }
}
