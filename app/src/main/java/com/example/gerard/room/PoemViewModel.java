package com.example.gerard.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PoemViewModel extends AndroidViewModel {

    private PoemRepository mRepository;

    public PoemViewModel(Application application) {
        super(application);
        mRepository = new PoemRepository(application);
    }

    LiveData<List<Poem>> getAllPoems() { return mRepository.getAllPoems(); }

    LiveData<List<Poem>> getAllPoemsOrderedBy(String order) { return mRepository.getAllPoemsOrderedBy(order); }

    LiveData<Poem> getPoem(int id){ return mRepository.getPoem(id); }

    public void insertPoem(Poem poem) { mRepository.insert(poem); }

    public void setRating(Poem poem) { mRepository.setRating(poem); }
}