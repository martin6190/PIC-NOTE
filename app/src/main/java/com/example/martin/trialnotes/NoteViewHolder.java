package com.example.martin.trialnotes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by martin on 11/14/2017.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {
    TextView note;
    View container;
    TextView title;

    public NoteViewHolder(View itemView, View container, TextView title, TextView note){
        super(itemView);
        this.note = note;
        this.title = title;
        this.container = container;
    }
}
