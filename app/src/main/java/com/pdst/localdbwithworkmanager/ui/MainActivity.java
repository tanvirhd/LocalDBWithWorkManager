package com.pdst.localdbwithworkmanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pdst.localdbwithworkmanager.R;
import com.pdst.localdbwithworkmanager.adapter.AdapterNoteList;
import com.pdst.localdbwithworkmanager.model.ModelNote;
import com.pdst.localdbwithworkmanager.viewmodel.ViewModelNote;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AdapterNoteList adapterNoteList;
    private List<ModelNote> noteList;
    private RecyclerView recycNotes;
    private TextView btnLoadNote;
    private ViewModelNote viewModelNote;

    private EditText etNoteTitle;
    private EditText etNoteBody;
    private Button btnSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycNotes=findViewById(R.id.recycNotes);
        btnSaveNote=findViewById(R.id.btnSaveNote);
        etNoteTitle=findViewById(R.id.noteTitle);
        etNoteBody=findViewById(R.id.noteBody);

        init();



        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNoteTitle.getText().toString().isEmpty() || etNoteBody.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Empty Field!!", Toast.LENGTH_SHORT).show();
                }else {
                    ModelNote newNote=new ModelNote(etNoteTitle.getText().toString(),etNoteBody.getText().toString());

                    UUID uid=viewModelNote.createNote(newNote);
                    WorkManager.getInstance(MainActivity.this).getWorkInfoByIdLiveData(uid).observe(MainActivity.this,
                            new Observer<WorkInfo>() {
                                @Override
                                public void onChanged(WorkInfo workInfo) {
                                    Log.d(TAG, "onChanged: "+workInfo.getState().toString());
                                    if(workInfo.getState().toString().equals("SUCCEEDED")){
                                        getAllNotes();
                                    }
                                }
                            });
                }
            }
        });

        //viewModelNote.getAllNotes().ob

    }

    void getAllNotes(){
        viewModelNote.getAllNotes().observe(MainActivity.this, new Observer<List<ModelNote>>() {
            @Override
            public void onChanged(List<ModelNote> modelNotes) {
                if(!modelNotes.isEmpty() && modelNotes!=null){
                    noteList.clear();
                    noteList.addAll(modelNotes);
                    adapterNoteList.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void init(){
        noteList=new ArrayList<>();
        viewModelNote=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelNote.class);
        adapterNoteList=new AdapterNoteList(noteList,MainActivity.this);
        recycNotes.setAdapter(adapterNoteList);
        recycNotes.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        getAllNotes();
    }

    void addNewNote(ModelNote note){
        noteList.add(note);
        adapterNoteList.notifyDataSetChanged();
    }

    List<ModelNote> generateDummyNote(){
        List<ModelNote> notes=new ArrayList<>();
        notes.add(new ModelNote("tiltle 1","body of title 1"));
        notes.add(new ModelNote("tiltle 2","body of title 2"));
        notes.add(new ModelNote("tiltle 3","body of title 3"));
        notes.add(new ModelNote("tiltle 4","body of title 4"));
        return notes;
    }

}