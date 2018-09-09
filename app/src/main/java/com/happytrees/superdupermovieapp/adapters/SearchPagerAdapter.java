package com.happytrees.superdupermovieapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                return fragmentMovies;
            case 1:
                FragmentTVShows fragmentTVShows = new FragmentTVShows();
                return fragmentTVShows;
            case 2:
                FragmentActors fragmentActors = new FragmentActors();
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

