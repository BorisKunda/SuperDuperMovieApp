package com.happytrees.superdupermovieapp.rest;


import com.happytrees.superdupermovieapp.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/movie")
    Call<SearchResponse>getSearchedMovies(@Query("query")String movieName ,@Query("api_key")String key);


    @GET("search/tv")
    Call<SearchResponse>getSearchedTVseries(@Query("api_key")String key,@Query("query")String tvName);
}




// https://api.themoviedb.org/3/search/movie?&query=interstellar&api_key=281181dbefe0c9f3d4af2d13adc51454  -> movies
// https://api.themoviedb.org/3/search/person?api_key=281181dbefe0c9f3d4af2d13adc51454&query=brad%20pit -> people
// https://api.themoviedb.org/3/search/tv?api_key=281181dbefe0c9f3d4af2d13adc51454&query=stranger%20things -> tv shows

//https://image.tmdb.org/t/p/w600_and_h900_bestv2