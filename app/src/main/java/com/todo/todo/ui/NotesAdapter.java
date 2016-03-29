package com.todo.todo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.todo.todo.data.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.CustomViewHolder> {

    private List<Note> feedItemList;

    public NotesAdapter(List<Note> feedItemList) {
        this.feedItemList = feedItemList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Note feedItem = feedItemList.get(i);
        customViewHolder.textView.setText(feedItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}


