package com.pdst.localdbwithworkmanager.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.pdst.localdbwithworkmanager.model.ModelNote;
import com.pdst.localdbwithworkmanager.room.NoteDao;
import com.pdst.localdbwithworkmanager.room.NoteDatabase;

public class CreateNoteWork extends Worker {
    private NoteDao noteDao;
    public CreateNoteWork(Context context,WorkerParameters workerParams) {
        super(context, workerParams);
        noteDao= NoteDatabase.getDb(context).getNoteDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData=getInputData();
        ModelNote note=new ModelNote(inputData.getString("title"),inputData.getString("body"));

        try{
            noteDao.insertNewNote(note);
            return Result.success();
        }catch (Exception e){
            Data errorLog=new Data.Builder().putString("sqlerrorlog",e.getMessage()).build();
            return  Result.failure(errorLog);
        }
    }
}
