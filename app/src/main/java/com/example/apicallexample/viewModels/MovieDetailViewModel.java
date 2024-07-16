package com.example.apicallexample.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.apicallexample.models.movie_detail.MovieDetail;
import com.example.apicallexample.repository.MovieRepository;
import com.example.apicallexample.screenStates.ScreenState;

public class MovieDetailViewModel extends ViewModel {

    // movie repo
    private final MovieRepository movieRepository;

    // constructor
    public  MovieDetailViewModel(){
        movieRepository = new MovieRepository();
    }

    public LiveData<ScreenState<MovieDetail>> getMovieDetails(String movieId){
       return movieRepository.getMovieDetails(movieId);
    }
}
