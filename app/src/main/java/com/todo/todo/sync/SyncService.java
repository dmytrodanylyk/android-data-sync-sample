package com.todo.todo.sync;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.todo.todo.sync.event.EventBusManager;
import com.todo.todo.sync.event.SyncRequestEvent;
import com.todo.todo.sync.event.SyncType;
import com.todo.todo.utils.L;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncService extends Service {

    private ExecutorService executor = null;
    private SyncManager syncManager = null;

    @Override
    public void onCreate() {
        L.i("SyncService onCreate");
        executor = Executors.newSingleThreadExecutor();
        syncManager = new SyncManager(getApplicationContext());
        EventBusManager.register(this);
    }

    @Override
    public void onDestroy() {
        L.i("SyncService onDestroy");
        EventBusManager.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(@NonNull final SyncRequestEvent event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                syncManager.doSync(event.getSyncType());
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, SyncService.class);
        context.startService(intent);
    }

    public static void request(@NonNull SyncType type) {
        EventBusManager.send(new SyncRequestEvent(type));
    }
}
