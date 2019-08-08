package com.zeeb.myfavoritemovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeeb.myfavoritemovie.BuildConfig;
import com.zeeb.myfavoritemovie.R;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public void setmCursor(Cursor mCursor) {
        this.mCursor = mCursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.ViewHolder viewHolder, int i) {
        if (mCursor.moveToPosition(i)) {
            viewHolder.judul.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("original_title")));
            viewHolder.score.setText(mCursor.getString(mCursor.getColumnIndexOrThrow("vote_average")));
            Glide.with(mContext)
                    .load(BuildConfig.URLIMAGE + mCursor.getString(mCursor.getColumnIndexOrThrow("poster_path")))
                    .into(viewHolder.imgPoster);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;
        TextView judul, score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.imgMoview);
            judul = itemView.findViewById(R.id.tvJudulMoview);
            score = itemView.findViewById(R.id.tvScoreMovie);
        }
    }
}
