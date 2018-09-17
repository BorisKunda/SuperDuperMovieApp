package com.happytrees.superdupermovieapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happytrees.superdupermovieapp.AutoFitGridLayoutManager;
import com.happytrees.superdupermovieapp.Constants;
import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ReusableMethods;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;
import com.happytrees.superdupermovieapp.adapters.MovieSearchAdapter;
import com.happytrees.superdupermovieapp.models.SearchMovieTVResult;
import com.happytrees.superdupermovieapp.models.SearchResponse;
import com.happytrees.superdupermovieapp.rest.ApiClient;
import com.happytrees.superdupermovieapp.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTVShows extends Fragment {

    private String query;

    public FragmentTVShows() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vSh = inflater.inflate(R.layout.fragment_fragment_tvshows, container, false);
        Log.e("lifecycle","FragmentTVShows onCreateView" );
       SearchViewModel tvModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this fragment and ViewModel.
        tvModel.searchQuery.observe(this, o -> {
            //Retrofit
            if (tvModel.searchQuery != null) {
                query = o.toString();
                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                Call<SearchResponse> call = apiInterface.getSearchedTVseries(Constants.API_KEY, query);
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        ArrayList<SearchMovieTVResult> searchTVResults = new ArrayList<>();
                        SearchResponse searchResponse = response.body();
                        if (searchResponse != null) {
                            searchTVResults.addAll(searchResponse.results);
                        } else {
                        //    ReusableMethods.closeMyKeyboard(vSh, getActivity());
                        //    ReusableMethods.noResultsSnackBar(vSh);
                        }


                        RecyclerView recyclerView = vSh.findViewById(R.id.searchTVlist);
                        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(getActivity(), 500);
                        recyclerView.setLayoutManager(autoFitGridLayoutManager);
                        MovieSearchAdapter movieSearchAdapter = new MovieSearchAdapter(getActivity(), searchTVResults);
                        recyclerView.setAdapter(movieSearchAdapter);

                        //ReusableMethods.closeMyKeyboard(vSh,getActivity());
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                     //   ReusableMethods.closeMyKeyboard(vSh,getActivity());
                       // Snackbar.make(vSh.findViewById(R.id.movieSearchRL), "unexpected error", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });//observe changes in searchQuery live data

        return vSh;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("lifecycle","FragmentTV onAttach" );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lifecycle","FragmentTV onCreate" );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("lifecycle","FragmentTV onActivityCreated" );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("lifecycle","FragmentTV onStart" );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("lifecycle","FragmentTV onResume" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("lifecycle","FragmentTV onPause" );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lifecycle","FragmentTV onStop" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("lifecycle","FragmentTV onDestroyView" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle","FragmentTV onDestroy" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lifecycle","FragmentTV onDetach" );
    }

}


