package com.example.gerard.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PoemDAO {

    @Insert
    void insert(Poem poem);

    @Query("SELECT * FROM poem")
    LiveData<List<Poem>> getAllPoems();

    @Query("SELECT * FROM poem WHERE id = :id")
    LiveData<Poem> getPoem(int id);

    @Query("UPDATE poem SET rating=:rating WHERE id=:id")
    void setRating(int id, float rating);
}
