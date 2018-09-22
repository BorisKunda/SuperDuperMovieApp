package com.happytrees.superdupermovieapp.fragments;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.happytrees.superdupermovieapp.AutoFitGridLayoutManager;
import com.happytrees.superdupermovieapp.Constants;
import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ReusableMethods;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;
import com.happytrees.superdupermovieapp.adapters.MovieSearchAdapter;
import com.happytrees.superdupermovieapp.models.SearchResponse;
import com.happytrees.superdupermovieapp.models.SearchMovieTVResult;
import com.happytrees.superdupermovieapp.rest.ApiClient;
import com.happytrees.superdupermovieapp.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMovies extends Fragment {

    TextView mTV;
    private String query;


    public FragmentMovies() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("lifecycle", "FragmentMovies onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lifecycle", "FragmentMovies onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("lifecycle", "FragmentMovies onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("lifecycle", "FragmentMovies onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("lifecycle", "FragmentMovies onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("lifecycle", "FragmentMovies onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lifecycle", "FragmentMovies onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("lifecycle", "FragmentMovies onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle", "FragmentMovies onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lifecycle", "FragmentMovies onDetach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View m = inflater.inflate(R.layout.fragment_fragment_movies, container, false);



        Log.e("lifecycle", "FragmentMovies onCreateView");

        SearchViewModel movieModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this fragment and ViewModel.
        movieModel.searchQuery.observe(this, o -> {
            //Retrofit
            if (movieModel.searchQuery != null) {
                Log.e("deb", "deb");
                query = o.toString();
                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                Call<SearchResponse> call = apiInterface.getSearchedMovies(query, Constants.API_KEY);
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        ArrayList<SearchMovieTVResult> searchMovieResults = new ArrayList<>();
                        ArrayList<SearchMovieTVResult> searchMovieResultsFiltered = new ArrayList<>();//search movie results where those without poster filtered out
                        SearchResponse searchResponse = response.body();
                        if (searchResponse != null) {
                            searchMovieResults.addAll(searchResponse.results);
                            //filter results without poster
                            for (SearchMovieTVResult searchMovieTVResult : searchMovieResults) {
                                if (searchMovieTVResult.poster_path != null) {
                                    searchMovieResultsFiltered.add(searchMovieTVResult);
                                }
                            }

                            //filter duplicate results
                            for(int i =0 ;i<searchMovieResultsFiltered.size();i++) {
                                int n =0; //n -> number of times we find same items in array.Because we compare array to itself it is okay if n<1.only if n>1 it means there are duplicate items
                                for(int k =0;k<searchMovieResultsFiltered.size();k++) {
                                    if(searchMovieResultsFiltered.get(i)==searchMovieResultsFiltered.get(k)) {
                                        n++;
                                        if(n>1) {
                                            searchMovieResultsFiltered.remove(i);
                                        }
                                    }
                                }
                            }

                        } else {
                            //no results text view
                        }

                        RecyclerView recyclerView = m.findViewById(R.id.searchMovielist);
                        recyclerView.setHasFixedSize(true);//If the size(width,height) of the RecyclerView doesn't depend on the adapter content you can setHasFixedSize(true). it will improve recycler view performance.
                        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(getActivity(), 500);
                        recyclerView.setLayoutManager(autoFitGridLayoutManager);
                        MovieSearchAdapter movieSearchAdapter = new MovieSearchAdapter(getActivity(), searchMovieResultsFiltered);
                        recyclerView.setAdapter(movieSearchAdapter);


                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        //no results text view
                    }
                });

            }
        });//observe changes in searchQuery live data

        return m;
    }

}

