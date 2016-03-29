package com.todo.todo.sync;

import android.content.Context;
import android.support.annotation.NonNull;
import com.todo.todo.sync.event.SyncType;

import java.util.HashMap;

class SyncManager {

    private HashMap<SyncType, AbsSync> syncMap;

    SyncManager(@NonNull Context context) {
        syncMap = new HashMap<>();
        syncMap.put(SyncType.NOTES, new NotesSync(context));
        syncMap.put(SyncType.REMINDERS, new RemindersSync(context));
    }

    void doSync(@NonNull SyncType syncType) {
        syncMap.get(syncType).sync();
    }
}
