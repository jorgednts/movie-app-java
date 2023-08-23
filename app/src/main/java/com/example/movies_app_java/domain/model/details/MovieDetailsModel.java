package com.example.movies_app_java.domain.model.details;

import java.util.ArrayList;
import java.util.Objects;

public class MovieDetailsModel {
    private final Boolean adult;
    private final Integer budget;
    private final ArrayList<String> genres;
    private final Integer id;
    private final String originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final String posterUrl;
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

    public Boolean getAdult() {
        return adult;
    }

    public Integer getBudget() {
        return budget;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public Integer getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public ArrayList<ProductionCompanyModel> getProductionCompanies() {
        return productionCompanies;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public ArrayList<String> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieDetailsModel that = (MovieDetailsModel) o;

        if (!Objects.equals(adult, that.adult)) return false;
        if (!Objects.equals(budget, that.budget)) return false;
        if (!Objects.equals(genres, that.genres)) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(originalLanguage, that.originalLanguage)) return false;
        if (!Objects.equals(originalTitle, that.originalTitle)) return false;
        if (!Objects.equals(overview, that.overview)) return false;
        if (!Objects.equals(posterUrl, that.posterUrl)) return false;
        if (!Objects.equals(productionCompanies, that.productionCompanies)) return false;
        if (!Objects.equals(releaseDate, that.releaseDate)) return false;
        if (!Objects.equals(revenue, that.revenue)) return false;
        if (!Objects.equals(runtime, that.runtime)) return false;
        if (!Objects.equals(spokenLanguages, that.spokenLanguages)) return false;
        if (!Objects.equals(status, that.status)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(voteAverage, that.voteAverage);
    }
}
