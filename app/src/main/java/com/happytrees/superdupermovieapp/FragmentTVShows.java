package com.happytrees.superdupermovieapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTVShows extends Fragment {


    public FragmentTVShows() {
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
        View vSh = inflater.inflate(R.layout.fragment_fragment_tvshows, container, false);

        TextView tv = vSh.findViewById(R.id.showsTV);

        SearchViewModel tvModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this activity and ViewModel.
        tvModel.searchQuery.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                tv.setText(o.toString());
            }
        });

        return vSh;
    }


}


