package com.happytrees.superdupermovieapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class FragmentActors extends Fragment {


    public FragmentActors() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_actors, container, false);
        TextView tv = v.findViewById(R.id.actorsTV);

        SearchViewModel tvModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this activity and ViewModel.
        tvModel.searchQuery.observe(this, o -> tv.setText(o.toString()));//observe changes in searchQuery live data

        return v;


    }

}
