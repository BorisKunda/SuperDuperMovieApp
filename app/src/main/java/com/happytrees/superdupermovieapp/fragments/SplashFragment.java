package com.happytrees.superdupermovieapp.fragments;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happytrees.superdupermovieapp.R;


public class SplashFragment extends Fragment {//provides splash screen


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_splash, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();//if you use getActionBar instead getSupportActionBar in case of AppCompatActivity there will be null pointer exception
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//lock device to portrait mode


        //closes splash fragment after 2.5 seconds
        new Handler().postDelayed(() -> {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();//re-enable action bar upon splash fragment closing
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);//restore ability to change configuration
            getActivity().getSupportFragmentManager().beginTransaction().remove(SplashFragment.this).commit();//close splash fragment after 2.5 seconds
        }, 2500);

        return v;
    }

}
