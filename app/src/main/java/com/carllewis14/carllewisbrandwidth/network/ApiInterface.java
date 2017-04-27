package com.carllewis14.carllewisbrandwidth.network;

import com.carllewis14.carllewisbrandwidth.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API Interface class end points defined here
 */

public interface ApiInterface {

    @GET("movie/popular")
    Call<MovieResponse> getMostPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
