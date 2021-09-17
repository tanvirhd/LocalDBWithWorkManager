package com.pdst.localdbwithworkmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdst.localdbwithworkmanager.R;
import com.pdst.localdbwithworkmanager.model.ModelNote;

import java.util.List;

public class AdapterNoteList extends  RecyclerView.Adapter<AdapterNoteList.ViewHolderAdapterNoteList>{

    List<ModelNote> noteList;
    Context context;

    public AdapterNoteList(List<ModelNote> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @Override
    public ViewHolderAdapterNoteList onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_note,parent,false);
        ViewHolderAdapterNoteList viewHolder=new ViewHolderAdapterNoteList(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( AdapterNoteList.ViewHolderAdapterNoteList holder, int position) {
        holder.tvNoteTitle.setText( noteList.get(position).getTitle() );
        holder.tvNoteBody.setText( noteList.get(position).getBody() );
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class  ViewHolderAdapterNoteList extends RecyclerView.ViewHolder{
        TextView tvNoteTitle,tvNoteBody;

        public ViewHolderAdapterNoteList(@NonNull View itemView) {
            super(itemView);
            tvNoteBody=itemView.findViewById(R.id.tvNoteBody);
            tvNoteTitle=itemView.findViewById(R.id.tvNoteTitle);
        }
    }
}
