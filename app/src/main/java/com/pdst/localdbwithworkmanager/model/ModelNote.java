package com.pdst.localdbwithworkmanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class ModelNote {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    private  String title;

    @ColumnInfo(name = "body")
    private  String body;

    public ModelNote(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
