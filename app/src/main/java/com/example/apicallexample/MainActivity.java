package com.example.apicallexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(new ArrayList<>());
        recyclerView.setAdapter(movieAdapter);

        //make api call
        fetchNowPlayingMovies();


    }

    public  void fetchNowPlayingMovies(){

        //make api call
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        // Make a network request
        Call<MovieResponse> call = apiInterface.getNowPlayingMovies("en-US", 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        List<MovieResponse.Movie> movieList = movieResponse.getMovies();
                        // Handle the response data
                        movieAdapter.setMovieResponse(movieList);

                    }else{
                        System.out.println("Request failed: " + response.body());

                    }
                } else {
                    // Handle the error
                    System.out.println("Request failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Handle the failure
                System.out.println("Request failed: " + t.getMessage());
            }
        });
    }


}