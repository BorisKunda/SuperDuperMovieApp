package com.happytrees.superdupermovieapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happytrees.superdupermovieapp.Constants;
import com.happytrees.superdupermovieapp.R;
import com.happytrees.superdupermovieapp.ViewModels.SearchViewModel;
import com.happytrees.superdupermovieapp.adapters.ActorSearchAdapter;
import com.happytrees.superdupermovieapp.models.SearchActor;
import com.happytrees.superdupermovieapp.models.SearchResponseActors;
import com.happytrees.superdupermovieapp.models.SearchResponseActors;
import com.happytrees.superdupermovieapp.rest.ApiClient;
import com.happytrees.superdupermovieapp.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentActors extends Fragment {

    private String query;

    public FragmentActors() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_actors, container, false);
        Log.e("lifecycle", "FragmentActors onCreateView");

        SearchViewModel actorModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);
        actorModel.searchQuery.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //Retrofit
                if (actorModel.searchQuery != null) {
                    query = o.toString();
                    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                    Call<SearchResponseActors> call = apiInterface.getSearchedActors(Constants.API_KEY, query);
                    call.enqueue(new Callback<SearchResponseActors>() {
                        @Override
                        public void onResponse(Call<SearchResponseActors> call, Response<SearchResponseActors> response) {
                            ArrayList<SearchActor> searchActors = new ArrayList<>();
                            ArrayList<SearchActor> searchActorsFiltered = new ArrayList<>();//search actor results where those without poster filtered out
                            SearchResponseActors searchResponseActors = response.body();
                            if (searchResponseActors != null) {
                                Log.e("lifecycle", "FragmentActors onCreateView");
                                searchActors.addAll(searchResponseActors.results);


                                RecyclerView recyclerView = v.findViewById(R.id.actorsSearchList);
                                recyclerView.setHasFixedSize(true);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(layoutManager);
                                ActorSearchAdapter actorSearchAdapter  = new ActorSearchAdapter(searchActors,getActivity());
                                recyclerView.setAdapter(actorSearchAdapter);

                            } else {
                                //tv no results
                            }


                        }

                        @Override
                        public void onFailure(Call<SearchResponseActors> call, Throwable t) {
                            //tv no results
                        }
                    });
                }
            }
        });//observe changes in searchQuery live data


        return v;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("lifecycle", "FragmentActors onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lifecycle", "FragmentActors onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("lifecycle", "FragmentActors onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("lifecycle", "FragmentActors onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("lifecycle", "FragmentActors onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("lifecycle", "FragmentActors onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lifecycle", "FragmentActors onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("lifecycle", "FragmentActors onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle", "FragmentActors onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lifecycle", "FragmentActors onDetach");
    }


}
