package com.happytrees.superdupermovieapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.happytrees.superdupermovieapp.fragments.FragmentActors;
import com.happytrees.superdupermovieapp.fragments.FragmentMovies;
import com.happytrees.superdupermovieapp.fragments.FragmentTVShows;

public class SearchPagerAdapter extends FragmentStatePagerAdapter {

    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentMovies fragmentMovies = new FragmentMovies();
                Log.e("lifecycle","view pager M" );
                return fragmentMovies;
            case 1:
                FragmentTVShows fragmentTVShows = new FragmentTVShows();
                Log.e("lifecycle","view pager T" );
                return fragmentTVShows;
            case 2:
                FragmentActors fragmentActors = new FragmentActors();
                Log.e("lifecycle","view pager A" );
                return fragmentActors;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;//cause we have three tabs
    }

    //assign titles to tabs
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MOVIES";
            case 1:
                return "TV SHOWS";
            case 2:
                return "ACTORS";
            default:
                return null;
        }
    }
}

