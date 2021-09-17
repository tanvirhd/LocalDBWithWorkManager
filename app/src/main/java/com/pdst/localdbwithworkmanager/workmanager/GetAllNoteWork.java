package com.pdst.localdbwithworkmanager.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.pdst.localdbwithworkmanager.model.ModelNote;
import com.pdst.localdbwithworkmanager.room.NoteDao;
import com.pdst.localdbwithworkmanager.room.NoteDatabase;

public class GetAllNoteWork extends Worker {

    public GetAllNoteWork(Context context,WorkerParameters workerParams) {
        super(context, workerParams);


    }

    @NonNull
    @Override
    public Result doWork() {
        return  null;
    }
}
