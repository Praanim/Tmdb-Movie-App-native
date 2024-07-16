package com.example.apicallexample.network;

import com.example.apicallexample.models.MovieResponse;
import com.example.apicallexample.models.movie_detail.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{movie_id}?language=en-US")
    Call<MovieDetail> getMovieDetails(
            @Path("movie_id") String movieId

    );
}
