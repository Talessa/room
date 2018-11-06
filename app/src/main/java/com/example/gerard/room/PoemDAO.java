package com.example.gerard.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class PoemDAO {

    @Insert
    abstract void insert(Poem poem);

    @Query("SELECT * FROM poem")
    abstract LiveData<List<Poem>> getAllPoems();

    @Query("SELECT * FROM poem ORDER BY date DESC")
    abstract LiveData<List<Poem>> getAllPoemsOrderedByDate();

    @Query("SELECT * FROM poem ORDER BY title")
    abstract LiveData<List<Poem>> getAllPoemsOrderedByTitle();

    @Query("SELECT * FROM poem ORDER BY rating DESC")
    abstract LiveData<List<Poem>> getAllPoemsOrderedByRating();

    LiveData<List<Poem>> getAllPoemsOrderedBy(String order){
        if(order.equals("date")){
            return getAllPoemsOrderedByDate();
        }else if(order.equals("rating")){
            return getAllPoemsOrderedByRating();
        }
        return getAllPoemsOrderedByTitle();
    }

    @Query("SELECT * FROM poem WHERE id = :id")
    abstract LiveData<Poem> getPoem(int id);

    @Query("UPDATE poem SET rating=:rating WHERE id=:id")
    abstract void setRating(int id, float rating);
}
