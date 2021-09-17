package com.pdst.localdbwithworkmanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.pdst.localdbwithworkmanager.model.ModelNote;
import com.pdst.localdbwithworkmanager.room.NoteDao;
import com.pdst.localdbwithworkmanager.room.NoteDatabase;
import com.pdst.localdbwithworkmanager.workmanager.CreateNoteWork;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;


public class ViewModelNote extends AndroidViewModel {
    Application application;
    public ViewModelNote(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public LiveData<List<ModelNote>> getAllNotes(){
        MutableLiveData<List<ModelNote>> allNotes=new MutableLiveData<>();
        NoteDao noteDao= NoteDatabase.getDb(application.getApplicationContext()).getNoteDao();
        Executors.newSingleThreadExecutor().execute(() -> {
            allNotes.postValue(noteDao.getAllNotes());
        });
        return allNotes;
    }

    public UUID createNote(ModelNote note){

        Data inputData=new Data.Builder()
                .putString("title",note.getTitle())
                .putString("body",note.getBody())
                .build();


        OneTimeWorkRequest request=new OneTimeWorkRequest.Builder(CreateNoteWork.class)
                .setInputData(inputData)
                .build();

        WorkManager.getInstance(application.getApplicationContext()).enqueue(request);

        return request.getId();
    }

}
