package com.todo.sync.event;

import android.support.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

public final class SyncEvent {

    private SyncType syncType;
    private SyncStatus syncStatus;

    private SyncEvent(@NonNull SyncType type, @NonNull SyncStatus status) {
        syncType = type;
        syncStatus = status;
    }

    public SyncType getType() {
        return syncType;
    }

    public SyncStatus getStatus() {
        return syncStatus;
    }

    public static void send(@NonNull final SyncType type, @NonNull final SyncStatus status) {
        EventBus.getDefault().post(new SyncEvent(type, status));
    }
}
