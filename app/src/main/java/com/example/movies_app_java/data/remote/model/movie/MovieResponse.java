package com.example.movies_app_java.data.remote.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class MovieResponse {
    @SerializedName("id")
    private final Integer id;
    @SerializedName("vote_average")
    private final Double voteAverage;
    private final String title;
    @SerializedName("poster_url")
    private final String imageUrl;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("genres")
    private final ArrayList<String> genres;

    public Integer getId() {
        return id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public MovieResponse(Integer id, Double voteAverage, String title, String imageUrl, String releaseDate, ArrayList<String> genres) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }
}
