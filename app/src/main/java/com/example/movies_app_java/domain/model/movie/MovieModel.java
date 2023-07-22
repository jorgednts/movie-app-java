package com.example.movies_app_java.domain.model.movie;

import java.util.ArrayList;

public class MovieModel {
    private final Integer id;
    private final Double voteAverage;
    private final String title;
    private final String imageUrl;
    private final String releaseDate;
    private final ArrayList<String> genres;

    public MovieModel(Integer id, Double voteAverage, String title, String imageUrl, String releaseDate, ArrayList<String> genres) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }
}
