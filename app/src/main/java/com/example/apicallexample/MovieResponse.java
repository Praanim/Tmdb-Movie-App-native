package com.example.apicallexample;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse{

    @SerializedName("page")
    private  final int page;

    @SerializedName("results")
    private final List<Movie> movies;

    public MovieResponse(int page, List<Movie> movies) {
        this.page = page;
        this.movies = movies;
    }

    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
    }


    public static class Movie {

        @SerializedName("id")
        private final int id;

        @SerializedName("title")
        private final String title;

        @SerializedName("overview")
        private final String description;

        @SerializedName("poster_path")
        private final String imagePath;


        public Movie(int id, String title, String description, String imagePath) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.imagePath = imagePath;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImagePath() {
            return imagePath;
        }
    }

}

