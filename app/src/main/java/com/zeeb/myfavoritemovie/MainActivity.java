package com.zeeb.myfavoritemovie;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    public static final String AUTHORITY = "com.zeeb.moviecataloguelocalstorage.providers";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + "tb_movie");
    private static final int CODE_MOVIE_DIR = 1;
    private static final int CODE_MOVIE_ITEM = 2;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;


    private RecyclerAdapter recyclerAdapter;


    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "tb_movie", CODE_MOVIE_DIR);
        MATCHER.addURI(AUTHORITY, "tb_movie" + "/#", CODE_MOVIE_ITEM);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        recyclerAdapter = new RecyclerAdapter();
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        rvMovie.setAdapter(recyclerAdapter);
        getLoaderManager().initLoader(CODE_MOVIE_DIR, null, loaderCallBack);
    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderCallBack = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getApplicationContext(), CONTENT_URI, null, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            recyclerAdapter.setMovie(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            recyclerAdapter.setMovie(null);
        }
    };


    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private Cursor cursor;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (cursor.moveToPosition(position)) {
                holder.judul.setText(cursor.getString(cursor.getColumnIndexOrThrow("original_title")));
                holder.score.setText(cursor.getString(cursor.getColumnIndexOrThrow("vote_average")));
                Glide.with(getApplicationContext())
                        .load(BuildConfig.URLIMAGE + cursor.getString(cursor.getColumnIndexOrThrow("poster_path")))
                        .into(holder.imgPoster);

            }
        }

        @Override
        public int getItemCount() {
            return cursor == null ? 0 : cursor.getCount();
        }

        void setMovie(Cursor o) {
            cursor = o;
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imgPoster;
            TextView judul, score;

            ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_movie, parent, false));

                imgPoster = itemView.findViewById(R.id.imgMoview);
                judul = itemView.findViewById(R.id.tvJudulMoview);
                score = itemView.findViewById(R.id.tvScoreMovie);
            }
        }
    }
}

