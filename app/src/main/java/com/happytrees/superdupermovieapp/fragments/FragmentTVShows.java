package com.happytrees.superdupermovieapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTVShows extends Fragment {


    public FragmentTVShows() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vSh = inflater.inflate(R.layout.fragment_fragment_tvshows, container, false);

        TextView tv = vSh.findViewById(R.id.showsTV);

        SearchViewModel tvModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this activity and ViewModel.
        tvModel.searchQuery.observe(this, o -> tv.setText(o.toString()));//observe changes in searchQuery live data

        return vSh;
    }


}


