package com.example.apicallexample.adapters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apicallexample.R;
import com.example.apicallexample.models.MovieResponse;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private  List<MovieResponse.Movie> movieList ;

    // constructor
    public MovieAdapter(List<MovieResponse.Movie> movieList){
        this.movieList = movieList;
    }

    // set Movie Response
    @SuppressLint("NotifyDataSetChanged")
    public void setMovieResponse(List<MovieResponse.Movie>  movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tile_item,parent,false);
        return  new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieResponse.Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getDescription());
        Glide.with(holder.imageView.getContext())
                .load(Uri.parse("https://image.tmdb.org/t/p/w500" + movie.getImagePath()))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return movieList.size();
    }

    // posts view holder class
    public static class MovieViewHolder extends  RecyclerView.ViewHolder{
        public TextView title, description;
        public ImageView imageView;

        //constructor
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView5);
            description = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
}