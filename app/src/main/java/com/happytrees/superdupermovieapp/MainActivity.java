package com.happytrees.superdupermovieapp;

import android.app.FragmentManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //BUGS:
    boolean splashed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            splashed = savedInstanceState.getBoolean("boolean", false);
        }



        if (splashed != true) {//if splashed is true then activity already had called splash fragment once
            splashFrag();
        }


    }


    //adds splash fragment
    void splashFrag() {
        Log.e("d", "e");
        SplashFragment splashFragment = new SplashFragment();
        FragmentManager fragmentManager = getFragmentManager();//call the Fragment Manager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, splashFragment).commit();
        splashed = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("boolean", splashed);
        Log.e("d", "e");
        super.onSaveInstanceState(outState);
    }
}
