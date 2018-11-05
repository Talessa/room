package com.example.gerard.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Poem {
    @PrimaryKey (autoGenerate = true)
    public int id;

    public String author;
    public String title;
    public String content;
    public float rating;
}
