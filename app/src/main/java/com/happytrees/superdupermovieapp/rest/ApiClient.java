package com.happytrees.superdupermovieapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //one single instance
    private static Retrofit retrofit = null;

    //private constructor -> the class can only instantiate itself and not by another class
    private ApiClient() {
    }

    //method to allow access to that instance

    public static Retrofit getRetrofit() {
        if(retrofit==null) {//instantiate retrofit only if there is no another instance already
            retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}


