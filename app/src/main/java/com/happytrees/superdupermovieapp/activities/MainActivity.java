package com.happytrees.superdupermovieapp.activities;


import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;
import com.happytrees.superdupermovieapp.fragments.FragmentChanger;
import com.happytrees.superdupermovieapp.fragments.SearchFragment;
import com.happytrees.superdupermovieapp.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity implements FragmentChanger {

    // TO:DO
    //Fix bug : no text in search .still there results
    //Fix Crash -> rotation change splash fragments
    //View Model -> boolean splashed save this variable in view model instead of using saveOnInstance
    //clear search query after menu collapse
    //check the movie db link . what are default results


    private boolean splashed = false;
    private FragmentManager fragmentManager;
    private SearchFragment searchFragment;
    SearchView searchView;
    public String search;
    SearchViewModel searchViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("lifecycle", "ACTIVITY onCreate");


        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);//create association between this activity and ViewModel.


        getSupportActionBar().setDisplayShowTitleEnabled(false);//remove title from action bar

        fragmentManager = getSupportFragmentManager();
        //FRAGMENTS
        searchFragment = new SearchFragment();


        //SPLASH FRAGMENT - FIX
        if (savedInstanceState != null) {
            splashed = savedInstanceState.getBoolean("boolean", false);
        }
        if (splashed != true) {//if splashed is true then activity already had called splash fragment once.
            splashFrag();
        }


    }


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        Log.e("f", "f");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        //setting menu item
        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.e("d", "d");
                fragmentManager.beginTransaction().replace(R.id.fragment_container, searchFragment).addToBackStack("search fragment").commit();//adds search fragment
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.e("d", "d");
                fragmentManager.beginTransaction().remove(searchFragment).commit();
                return true;
            }
        });

        //setting search view
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("d", "d");
                searchViewModel.searchQuery.setValue(newText);
                return true;
            }
        });
        searchView.setQueryHint("Movies");

        //setting search manager to hook up searchview with searchable.xml configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;


    }

    //METHODS:

    //adds splash fragment
    void splashFrag() {
        SplashFragment splashFragment = new SplashFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();//call the Fragment Manager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, splashFragment).commit();
        splashed = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("boolean", splashed);
        Log.e("d", "e");
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String voiceQuery = intent.getStringExtra(SearchManager.QUERY);
            searchViewModel.searchQuery.setValue(voiceQuery);
            searchView.setQuery(voiceQuery, true);
        }
    }


    @Override
    public void ChangeFrTab(int pos) {
    Log.e("deb","deb" );
    if(searchView!=null){
        switch (pos) {
            case 0:
                searchView.setQueryHint("Movies");
                break;
            case 1:
                searchView.setQueryHint("TV Series");
                break;
            case 2:
                searchView.setQueryHint("Actors");
                break;
            default:
                searchView.setQueryHint("Movies");
        }
    }


    }
}

/*
	 int month = 15;//changing month value will change case
		 //try for instance : int month = 3;
		 //after each case you must add break;
	        String monthString;

	        switch (month) {

	            case 1:  monthString = "January";
	                     break;
	            case 2:  monthString = "February";
	                     break;
	            case 3:  monthString = "March";
	                     break;
	            case 4:  monthString = "April";
	                     break;
	            case 5:  monthString = "May";
	                     break;
	            case 6:  monthString = "June";
	                     break;
	            case 7:  monthString = "July";
	                     break;
	            case 8:  monthString = "August";
	                     break;
	            case 9:  monthString = "September";
	                     break;
	            case 10: monthString = "October";
	                     break;
	            case 11: monthString = "November";
	                     break;
	            case 12: monthString = "December";
	                     break;
	            default: monthString = "Invalid month";
	                     break;
	        }
	        System.out.println(monthString);
 */