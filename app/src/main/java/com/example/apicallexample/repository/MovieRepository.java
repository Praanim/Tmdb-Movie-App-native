package com.example.apicallexample.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apicallexample.models.MovieResponse;
import com.example.apicallexample.models.movie_detail.MovieDetail;
import com.example.apicallexample.network.ApiClient;
import com.example.apicallexample.network.ApiInterface;
import com.example.apicallexample.screenStates.ScreenState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    final ApiInterface apiService;
    // constructor
    public MovieRepository(){
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    // gets movie details
    public LiveData<ScreenState<MovieDetail>> getMovieDetails(String movieId){
        final MutableLiveData<ScreenState<MovieDetail>> movieDetailScreenState = new MutableLiveData<>();

        // set the loading state
        movieDetailScreenState.setValue(new ScreenState<MovieDetail>(ScreenState.Status.LOADING,null,null));
        apiService.getMovieDetails(movieId).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetail> call, @NonNull Response<MovieDetail> response) {
                if(response.isSuccessful()){
                    // set the success state
                    movieDetailScreenState.setValue(new ScreenState<MovieDetail>(ScreenState.Status.SUCCESS,response.body(),null));
                }else{
                    movieDetailScreenState.setValue(new ScreenState<MovieDetail>(ScreenState.Status.ERROR,null,"Failed to fetch the movie details"));
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                movieDetailScreenState.setValue(new ScreenState<MovieDetail>(ScreenState.Status.ERROR,null,"Network error : " +t.getMessage()));
            }
        });

        return  movieDetailScreenState;
    }
}
