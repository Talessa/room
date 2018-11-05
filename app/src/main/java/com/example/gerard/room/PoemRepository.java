package com.example.gerard.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PoemRepository {

    private PoemDAO mPoemDao;

    PoemRepository(Application application) {
        mPoemDao = PoemRoomDatabase.getDatabase(application).poemDAO();
    }

    LiveData<List<Poem>> getAllPoems() {
        return mPoemDao.getAllPoems();
    }

    LiveData<Poem> getPoem(int id){ return mPoemDao.getPoem(id); }

    public void insert(Poem poem) {
        new insertAsyncTask(mPoemDao).execute(poem);
    }

    private static class insertAsyncTask extends AsyncTask<Poem, Void, Void> {

        private PoemDAO mAsyncTaskDao;

        insertAsyncTask(PoemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Poem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void setRating(Poem poem){
        new setRatingAsyncTask(mPoemDao).execute(poem);
    }

    private static class setRatingAsyncTask extends AsyncTask<Poem, Void, Void> {

        private PoemDAO mAsyncTaskDao;

        setRatingAsyncTask(PoemDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Poem... params) {
            mAsyncTaskDao.setRating(params[0].id, params[0].rating);
            return null;
        }
    }
}
