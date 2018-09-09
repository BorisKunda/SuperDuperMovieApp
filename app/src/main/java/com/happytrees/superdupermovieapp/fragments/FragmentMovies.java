package com.happytrees.superdupermovieapp.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;
import com.happytrees.superdupermovieapp.adapters.MovieSearchAdapter;
import com.happytrees.superdupermovieapp.models.SearchMovieResponse;
import com.happytrees.superdupermovieapp.models.SearchMovieResult;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View m = inflater.inflate(R.layout.fragment_fragment_movies, container, false);


        SearchViewModel movieModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);//create association between this activity and ViewModel.
        movieModel.searchQuery.observe(this, o -> {
            query = o.toString();
            //Retrofit
            if (query != null) {

                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                Call<SearchMovieResponse> call = apiInterface.getSearchedMovies(query, "281181dbefe0c9f3d4af2d13adc51454");
                call.enqueue(new Callback<SearchMovieResponse>() {
                    @Override
                    public void onResponse(Call<SearchMovieResponse> call, Response<SearchMovieResponse> response) {

                        ArrayList<SearchMovieResult> searchMovieResults = new ArrayList<>();
                        if(response!=null) {
                            SearchMovieResponse searchMovieResponse = response.body();
                            if(searchMovieResponse!=null) {
                                searchMovieResults.addAll(searchMovieResponse.results);
                            }

                        }


                        if (searchMovieResults != null) {
                            //snack bar - no results
                            RecyclerView recyclerView = m.findViewById(R.id.searchMovielist);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            MovieSearchAdapter movieSearchAdapter = new MovieSearchAdapter(getActivity(), searchMovieResults);
                            recyclerView.setAdapter(movieSearchAdapter);
                            Log.e("d","f" );
                        } else {
                            //no results snack bar

                        }
                    }

                    @Override
                    public void onFailure(Call<SearchMovieResponse> call, Throwable t) {
                        Log.e("call fr movies failed", t.toString());
                        //snack bar - something went wrong
                    }
                });

            }
        });//observe changes in searchQuery live data

        return m;
    }


}

/*
    final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);
   Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser>
                    response) {

                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar()).get();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                avatarImg.setImageBitmap(myImage);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    userNameTV.setText("No name provided");
                } else {
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                logIn.setText("LogIn: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setText("No email provided");
                } else{
                    email.setText("Email: " + response.body().getEmail());
                }



            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });
 */