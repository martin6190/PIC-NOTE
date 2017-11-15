package com.example.martin.trialnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by martin on 11/14/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    ArrayList<Note> listNote;


    public NoteAdapter(ArrayList<Note> note){
        this.listNote = note;

    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView, itemView.findViewById(R.id.Note_Container),
                (TextView) itemView.findViewById(R.id.NoteItemTitle),
                (TextView)itemView.findViewById(R.id.NoteItemNote));

    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note currentNote = listNote.get(position);
        holder.note.setText(currentNote.getNote());
        holder.title.setText(currentNote.getTitle());
        holder.container.setTag(R.id.Note_Container,currentNote);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewNoteIntent = new Intent(v.getContext(), EditNote.class);
                Bundle extras = new Bundle();
                extras.putString("NOTE_TITLE",currentNote.getTitle());
                extras.putString("NOTE_NOTE",currentNote.getNote());
                viewNoteIntent.putExtras(extras);
                v.getContext().startActivity(viewNoteIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }
}
