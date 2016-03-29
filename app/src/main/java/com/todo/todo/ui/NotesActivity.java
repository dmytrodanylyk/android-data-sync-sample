package com.todo.todo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.todo.todo.R;
import com.todo.todo.db.NotesStorage;
import com.todo.todo.sync.SyncService;
import com.todo.todo.sync.event.EventBusManager;
import com.todo.todo.sync.event.SyncEvent;
import com.todo.todo.sync.event.SyncStatus;
import com.todo.todo.sync.event.SyncType;
import io.realm.Realm;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NotesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeView;
    private NotesAdapter adapter;

    public static void start(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, NotesActivity.class));
    }

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        realm = Realm.getDefaultInstance();
        EventBusManager.register(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        EventBusManager.unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SyncEvent event) {
        if (event.getType() == SyncType.NOTES && event.getStatus() == SyncStatus.COMPLETED) {
            swipeView.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipeView);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SyncService.request(SyncType.NOTES);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(NotesStorage.getAll(realm));
        recyclerView.setAdapter(adapter);
    }
}
