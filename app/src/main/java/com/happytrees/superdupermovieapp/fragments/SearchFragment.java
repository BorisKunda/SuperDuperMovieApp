package com.happytrees.superdupermovieapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.adapters.SearchPagerAdapter;


public class SearchFragment extends Fragment {//PARENT FRAGMENT FOR SEARCH TABS  CHILD FRAGMENTS

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("lifecycle","SearchFragment onCreateView" );
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);//remove divider line between tabs and action bar
        ViewPager viewPager = v.findViewById(R.id.SearchFragmentViewPager);
        viewPager.setAdapter(new SearchPagerAdapter(getChildFragmentManager()));//getChildFragmentManager() cause we have child  fragments(tabs) inside SearchFragment
        TabLayout tabLayout = v.findViewById(R.id.search_tabs);//TabLayout provides a horizontal layout to display tabs
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("lifecycle","SearchFragment onAttach" );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lifecycle","SearchFragment onCreate" );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("lifecycle","SearchFragment onActivityCreated" );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("lifecycle","SearchFragment onStart" );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("lifecycle","SearchFragment onResume" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("lifecycle","SearchFragment onPause" );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lifecycle","SearchFragment onStop" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("lifecycle","SearchFragment onDestroyView" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle","SearchFragment onDestroy" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lifecycle","SearchFragment onDetach" );
    } */


}

