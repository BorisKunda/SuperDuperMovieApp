package com.happytrees.superdupermovieapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.models.SearchMovieResult;

import java.util.ArrayList;


//create a class that extends RecyclerView.Adapter .put inside the < >  ==> Yourclass.YourInnerClassViewHolder

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.SearchMovieViewHolder> {

    private Context context;
    private ArrayList<SearchMovieResult> smResults;

    public MovieSearchAdapter(Context context, ArrayList<SearchMovieResult> smResults) {
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
        SearchMovieResult searchMovieResult = smResults.get(position);
        holder.title.setText(searchMovieResult.title);

    }

    @Override
    public int getItemCount() {
        return smResults.size();
    }

    //create inner class  YourInnerClassViewHolder extends RecyclerView.ViewHolder => implement constructor
    public class SearchMovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public SearchMovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieResult);
        }

    }

}
