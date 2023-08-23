package com.example.movies_app_java.domain.model.movie;

import java.util.ArrayList;

public class MovieModel {
    private final Integer id;
    private final Double voteAverage;
    private final String title;
    private final String imageUrl;
    private final String releaseDate;
    private final ArrayList<String> genres;

    public MovieModel(Integer id, Double voteAverage, String title, String imageUrl,
                      String releaseDate, ArrayList<String> genres) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MovieModel other = (MovieModel) obj;
        return id.equals(other.id) &&
                voteAverage.equals(other.voteAverage) &&
                title.equals(other.title) &&
                imageUrl.equals(other.imageUrl) &&
                releaseDate.equals(other.releaseDate) &&
                genres.equals(other.genres);
    }
}
