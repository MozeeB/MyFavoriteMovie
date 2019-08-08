package com.zeeb.myfavoritemovie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeeb.myfavoritemovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {


    private FragmentManager fragmentManager;


    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static TvShowFragment newInstance(FragmentManager fragmentManager) {
        TvShowFragment fragment = new TvShowFragment();
        fragment.setFragmentManager(fragmentManager);
        return fragment;
    }



    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

}
