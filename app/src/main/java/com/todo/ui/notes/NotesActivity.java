package com.todo.ui.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.todo.R;
import com.todo.db.NotesStorage;
import com.todo.sync.SyncService;
import com.todo.sync.event.EventBusManager;
import com.todo.sync.event.SyncEvent;
import com.todo.sync.event.SyncStatus;
import com.todo.sync.event.SyncType;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNote) {
            NewNoteActivity.start(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SyncEvent event) {
        if (event.getType() == SyncType.NOTES && event.getStatus() == SyncStatus.IN_PROGRESS) {
            if (!swipeView.isRefreshing()) {
                swipeView.setRefreshing(true);
            }
        } else if (event.getType() == SyncType.NOTES && event.getStatus() == SyncStatus.COMPLETED) {
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
