package com.example.movies_app_java.domain.model.details;

import java.util.ArrayList;

public class MovieDetailsModel {
    private final Boolean adult;
    private final Integer budget;
    private final ArrayList<String> genres;
    private final Integer id;
    private final String originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final  String posterUrl;
    private final ArrayList<ProductionCompanyModel> productionCompanies;
    private final String releaseDate;
    private final Integer revenue;
    private final Integer runtime;
    private final ArrayList<String> spokenLanguages;
    private final String status;
    private final String title;
    private final Double voteAverage;

    public MovieDetailsModel(Boolean adult, Integer budget, ArrayList<String> genres, Integer id, String originalLanguage, String originalTitle, String overview, String posterUrl, ArrayList<ProductionCompanyModel> productionCompanies, String releaseDate, Integer revenue, Integer runtime, ArrayList<String> spokenLanguages, String status, String title, Double voteAverage) {
        this.adult = adult;
        this.budget = budget;
        this.genres = genres;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.posterUrl = posterUrl;
        this.productionCompanies = productionCompanies;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.title = title;
        this.voteAverage = voteAverage;
    }
}
