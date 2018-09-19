package com.happytrees.superdupermovieapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.happytrees.superdupermovieapp.Constants;
import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.models.SearchActor;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActorSearchAdapter extends RecyclerView.Adapter<ActorSearchAdapter.SearchActorViewHolder> {

    ArrayList<SearchActor> searchActors;
    Context context;

    public ActorSearchAdapter(ArrayList<SearchActor> searchActors, Context context) {
        this.searchActors = searchActors;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.actor_search_item, null);
        SearchActorViewHolder searchActorViewHolder = new SearchActorViewHolder(v);
        return searchActorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchActorViewHolder holder, int position) {
        SearchActor searchActor = searchActors.get(position);
        String fullUrl = Constants.POSTER_URL_INITIAL_PART + searchActor.profile_path;
        //Glide
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder);
        requestOptions.error(R.drawable.noimage);
        requestOptions.dontAnimate();//disable glide animation to prevent conflict with CircularImage lib
        Glide.with(context).load(fullUrl).apply(requestOptions).into(holder.actorIV);

        holder.actorName.setText(searchActor.name);

    }

    @Override
    public int getItemCount() {
        return searchActors.size();
    }

    public class SearchActorViewHolder extends RecyclerView.ViewHolder {
        TextView actorName;
        CircleImageView actorIV;

        public SearchActorViewHolder(View itemView) {
            super(itemView);
            actorName = itemView.findViewById(R.id.actorTV);
            actorIV = itemView.findViewById(R.id.actorImage);
        }
    }

}
