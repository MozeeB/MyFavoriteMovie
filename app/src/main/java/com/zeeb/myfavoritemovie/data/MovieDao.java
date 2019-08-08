package com.zeeb.myfavoritemovie.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.zeeb.myfavoritemovie.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM tb_movie WHERE id = :id")
    Cursor selectItem(long id);

    @Query("SELECT * FROM tb_movie")
    Cursor getFavoriteMovie();

    @Query("SELECT * FROM tb_movie")
    List<Movie> getFavoriteNoCursor();

    @Query("SELECT * FROM tb_movie WHERE id = :id")
    Movie selectItemNoCursor(String id);
}
