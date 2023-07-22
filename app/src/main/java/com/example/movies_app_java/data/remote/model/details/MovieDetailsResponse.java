package com.example.movies_app_java.data.remote.model.details;

import com.example.movies_app_java.data.remote.model.production_company.ProductionCompanyResponse;
import com.example.movies_app_java.data.remote.model.spoken_languages.SpokenLanguagesResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetailsResponse {
    @SerializedName("adult")
    private final Boolean isAdult;
    @SerializedName("budget")
    private final Integer budget;
    @SerializedName("genres")
    private final ArrayList<String> genres;
    @SerializedName("id")
    private final Integer id;
    @SerializedName("original_language")
    private final String originalLanguage;
    @SerializedName("original_title")
    private final String originalTitle;
    @SerializedName("overview")
    private final String overview;
    @SerializedName("poster_url")
    private final String posterUrl;
    @SerializedName("production_companies")
    private final ArrayList<ProductionCompanyResponse> productionCompanies;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("revenue")
    private final Integer revenue;
    @SerializedName("runtime")
    private final Integer runtime;
    @SerializedName("spoken_languages")
    private final ArrayList<SpokenLanguagesResponse> spokenLanguages;
    @SerializedName("status")
    private final String status;
    @SerializedName("title")
    private final String title;
    @SerializedName("vote_average")
    private final Double voteAverage;

    public Boolean getIsAdult() {
        return isAdult;
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

    public ArrayList<ProductionCompanyResponse> getProductionCompanies() {
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

    public ArrayList<SpokenLanguagesResponse> getSpokenLanguages() {
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

    public MovieDetailsResponse(Boolean isAdult, Integer budget, ArrayList<String> genres, Integer id, String originalLanguage,
                                String originalTitle, String overview, String posterUrl, ArrayList<ProductionCompanyResponse> productionCompanies,
                                String releaseDate, Integer revenue, Integer runtime, ArrayList<SpokenLanguagesResponse> spokenLanguages,
                                String status, String title, Double voteAverage) {
        this.isAdult = isAdult;
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
