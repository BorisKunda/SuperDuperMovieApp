package com.happytrees.superdupermovieapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);//remove divider line between tabs and action bar
        ViewPager viewPager = v.findViewById(R.id.SearchFragmentViewPager);
        viewPager.setAdapter(new SearchPagerAdapter(getChildFragmentManager()));//getChildFragmentManager() cause we have child  fragments(tabs) inside SearchFragment
        TabLayout tabLayout = v.findViewById(R.id.search_tabs);//TabLayout provides a horizontal layout to display tabs
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }
}

