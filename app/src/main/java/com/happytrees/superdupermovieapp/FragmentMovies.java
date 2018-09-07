package com.happytrees.superdupermovieapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovies extends Fragment {

    TextView mTV;

    @Override
    public void onAttach(Context context) {
        Log.e(  "FragmentMovies", "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(  "FragmentMovies", "onCreate");

    }

    public FragmentMovies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View m = inflater.inflate(R.layout.fragment_fragment_movies, container, false);


        mTV = m.findViewById(R.id.moviesTV);


       SearchViewModel movieModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this activity and ViewModel.

            movieModel.searchQuery.observe(this, new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    mTV.setText(o.toString());
                }
            });





        return m;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(  "FragmentMovies " + "TV " + mTV, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(  "FragmentMovies  TV " + mTV , " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(  "FragmentMovies TV " + mTV, " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(  "FragmentMovies", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(  "FragmentMovies", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(  "FragmentMovies", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(  "FragmentMovies", "onDestroy");
    }


}

