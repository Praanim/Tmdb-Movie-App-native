package com.example.apicallexample.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.apicallexample.adapters.MovieAdapter;
import com.example.apicallexample.R;
import com.example.apicallexample.models.MovieResponse;
import com.example.apicallexample.network.ApiClient;
import com.example.apicallexample.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        movieAdapter = new MovieAdapter(new ArrayList<>(),null);
//        recyclerView.setAdapter(movieAdapter);

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
                        movieAdapter = new MovieAdapter(movieList, new MovieAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(MovieResponse.Movie movie) {
                                Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
                                intent.putExtra("MOVIE_ID",movie.getId());
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(movieAdapter);


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