package com.happytrees.superdupermovieapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.happytrees.superdupermovieapp.Constants;
import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.models.SearchMovieTVResult;

import java.util.ArrayList;


//create a class that extends RecyclerView.Adapter .put inside the < >  ==> Yourclass.YourInnerClassViewHolder

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.SearchMovieViewHolder> {

    private Context context;
    private ArrayList<SearchMovieTVResult> smResults;

    public MovieSearchAdapter(Context context, ArrayList<SearchMovieTVResult> smResults) {
        this.context = context;
        this.smResults = smResults;
    }


    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_movie_search_result, null);
        SearchMovieViewHolder searchMovieViewHolder = new SearchMovieViewHolder(view);
        return searchMovieViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder holder, int position) {
        //bind data to view holder
        SearchMovieTVResult searchMovieTVResult = smResults.get(position);

        if (searchMovieTVResult.poster_path == null) {
            holder.iv.setImageResource(R.drawable.noimage);
        } else {
            String fullUrl = Constants.POSTER_URL_INITIAL_PART + searchMovieTVResult.poster_path;
            //GLIDE
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.placeholder);
            requestOptions.error(R.drawable.noimage);
            Glide.with(context).load(fullUrl).apply(requestOptions).into(holder.iv);
        }


    }

    @Override
    public int getItemCount() {
        return smResults.size();
    }

    //create inner class  YourInnerClassViewHolder extends RecyclerView.ViewHolder => implement constructor
     class SearchMovieViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

         SearchMovieViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.IV);

        }

    }

}

