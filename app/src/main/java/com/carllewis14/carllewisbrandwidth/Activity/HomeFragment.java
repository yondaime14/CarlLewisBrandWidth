package com.carllewis14.carllewisbrandwidth.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carllewis14.carllewisbrandwidth.Adapter.MovieAdapter;
import com.carllewis14.carllewisbrandwidth.Model.Movie;
import com.carllewis14.carllewisbrandwidth.Model.MovieResponse;
import com.carllewis14.carllewisbrandwidth.R;
import com.carllewis14.carllewisbrandwidth.network.ApiClient;
import com.carllewis14.carllewisbrandwidth.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Home Fragment is default fragment for movies
 */
public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mAdapter;

    private final static String API_KEY = "670d03a721dd007862c0181bfd097e5d";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.movies_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        return rootView;

    }

    private void initData() {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = api.getMostPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                mRecyclerView.setAdapter(new MovieAdapter(movies, R.layout.list_item_movie, getContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Log.d(TAG, t.toString());

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
