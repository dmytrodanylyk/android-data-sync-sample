package com.todo.todo.ui.notes;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
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
        String modifiedStr = String.format("Is modified: %s", feedItem.isModified());

        SpannableStringBuilder spannable = new SpannableStringBuilder();
        spannable.append(feedItem.getTitle());
        spannable.append("\n");
        spannable.append(feedItem.getCreatedDate().toString());
        spannable.append("\n");
        spannable.append(modifiedStr);
        spannable.append("\n");
        bold(spannable, feedItem.getTitle());
        customViewHolder.textView.setText(spannable);
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

    private void bold(@NonNull Spannable original, @NonNull String text) {
        String originalString = original.toString();
        int start = originalString.indexOf(text);
        int end = originalString.indexOf(text) + text.length();

        if (start >= 0 && end > 0) {
            original.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}


