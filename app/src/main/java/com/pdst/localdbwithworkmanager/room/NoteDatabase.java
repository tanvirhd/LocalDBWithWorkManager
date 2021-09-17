package com.pdst.localdbwithworkmanager.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pdst.localdbwithworkmanager.model.ModelNote;

@Database(entities = {ModelNote.class} , version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();

    public static NoteDatabase dbInstance;

    public static synchronized NoteDatabase getDb(Context context){
        if(dbInstance==null){
            dbInstance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }

}
