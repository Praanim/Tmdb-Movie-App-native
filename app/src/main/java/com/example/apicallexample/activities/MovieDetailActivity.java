package com.example.apicallexample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apicallexample.R;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView movieIdTextView;

    private int defaultValue = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activiity);

        movieIdTextView = findViewById(R.id.movieId);

        int movieId = getIntent().getIntExtra("MOVIE_ID",defaultValue);

        if(movieId != defaultValue){
            movieIdTextView.setText("The movie id is : " + movieId);
        }

    }
}