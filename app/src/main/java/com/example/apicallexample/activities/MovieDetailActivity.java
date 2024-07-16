package com.example.apicallexample.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apicallexample.R;
import com.example.apicallexample.models.movie_detail.MovieDetail;
import com.example.apicallexample.viewModels.MovieDetailViewModel;

public class MovieDetailActivity extends AppCompatActivity {

    private int defaultValue = -1;

     private  ImageView imageView;

    private  TextView movieTitle;

    private  TextView movieDesc;

    private View commonLoadingLayout;

    private MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activiity);

        // get the activity widgets
        imageView = findViewById(R.id.imageView);
        movieTitle = findViewById(R.id.movieTitleId);
        movieDesc = findViewById(R.id.movieDetailsId);
        commonLoadingLayout = findViewById(R.id.loadingLayout);

        // movie view model
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        int movieId = getIntent().getIntExtra("MOVIE_ID",defaultValue);

        if(movieId != defaultValue){
            // call the api
           movieDetailViewModel.getMovieDetails(String.valueOf(movieId)).observe(this,movieDetailScreenState -> {
               switch (movieDetailScreenState.getStatus()){
                   case ERROR:
                       showLoading(false);
                       showError(movieDetailScreenState.getMessage());
                   case SUCCESS:
                       showLoading(false);
                       updateMovieDetails(movieDetailScreenState.getData());
                       break;
                   case LOADING:
                       showLoading(true);
                       break;

               }
           });


        }

    }

    private void showLoading(boolean show) {
        commonLoadingLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        movieTitle.setVisibility(show ? View.GONE : View.VISIBLE);
        movieDesc.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void updateMovieDetails(MovieDetail movieDetail) {
        Glide.with(imageView.getContext()).load(Uri.parse("https://image.tmdb.org/t/p/w500"+movieDetail.getBackdropPath())).into(imageView);
        movieTitle.setText(movieDetail.getTitle());
        movieDesc.setText(movieDetail.getOverview());
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}