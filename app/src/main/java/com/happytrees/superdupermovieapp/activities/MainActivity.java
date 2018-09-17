package com.happytrees.superdupermovieapp.activities;


import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;
import com.happytrees.superdupermovieapp.fragments.SearchFragment;
import com.happytrees.superdupermovieapp.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {

    // TO:DO
    //View Model -> boolean splashed


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
        Log.e("lifecycle","ACTIVITY onCreate" );

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

  /*  @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.e("lifecycle","ACTIVITY onAttachFragment" );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("lifecycle","ACTIVITY onStart" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifecycle","ACTIVITY onResume" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifecycle","ACTIVITY onPause" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("lifecycle","ACTIVITY onStop" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle","ACTIVITY onDestroy" );
    } */
}

