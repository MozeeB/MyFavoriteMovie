package com.zeeb.myfavoritemovie.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.zeeb.myfavoritemovie.model.Movie;

@Database(entities = Movie.class, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase movieDatabase;

    public static MovieDatabase getMovieDatabase(Context context) {
        synchronized (MovieDatabase.class) {
            if (movieDatabase == null) {
                movieDatabase = Room.databaseBuilder(context, MovieDatabase.class, "db_movie").allowMainThreadQueries().build();
            }
        }
        return movieDatabase;
    }
}
