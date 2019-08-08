package com.zeeb.myfavoritemovie.fragment;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeeb.myfavoritemovie.R;
import com.zeeb.myfavoritemovie.adapter.AdapterMovie;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    public static final String AUTHORITY = "com.zeeb.moviecataloguelocalstorage.providers";
    public static final Uri URI_MOVIE = Uri.parse("content://" + AUTHORITY + "/" + "tb_movie");
    private static final int LOADER = 1;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    Unbinder unbinder;
    private AdapterMovie adapterMovie;

    private FragmentManager fragmentManager;


    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static MovieFragment newInstance(FragmentManager fragmentManager) {
        MovieFragment fragment = new MovieFragment();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            adapterMovie = new AdapterMovie();
            rvMovie.setHasFixedSize(true);
            rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvMovie.setAdapter(adapterMovie);

            getActivity().getSupportLoaderManager().initLoader(LOADER, null, mLoaderCallbacks);

        }
    }


    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @NonNull
                @Override
                public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                    /*TODO ERROR DI SINI*/
                    switch (id){
                        case LOADER:
                            return new CursorLoader(
                                    getActivity().getApplicationContext(),
                                    URI_MOVIE,
                                    null,
                                    null,
                                    null,
                                    null);


                        default:
                            throw new IllegalArgumentException();
                    }

                }

                @Override
                public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                    Log.e("DATASET", "MONMON");
                    switch (loader.getId()){
                        case LOADER:
                            adapterMovie.setmCursor(data);
                            break;
                    }
                }

                @Override
                public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                    switch (loader.getId()){
                        case LOADER:
                            adapterMovie.setmCursor(null);
                            break;
                    }
                }
            };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
