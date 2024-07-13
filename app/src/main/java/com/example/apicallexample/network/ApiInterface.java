package com.example.apicallexample.network;

import com.example.apicallexample.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(
            @Query("language") String language,
            @Query("page") int page
    );
}
