package com.example.gerard.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Poem.class}, version = 2)
public abstract class PoemRoomDatabase extends RoomDatabase {

    public abstract PoemDAO poemDAO();

    private static volatile PoemRoomDatabase INSTANCE;

    static PoemRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PoemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PoemRoomDatabase.class, "poem_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
