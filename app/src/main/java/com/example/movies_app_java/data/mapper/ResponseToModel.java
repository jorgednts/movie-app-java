package com.example.movies_app_java.data.mapper;

import static com.example.movies_app_java.extensions.StringExtensions.convertIfIsNullOrBlank;

import com.example.movies_app_java.constants.NullResponseConstants;
import com.example.movies_app_java.data.remote.model.details.MovieDetailsResponse;
import com.example.movies_app_java.data.remote.model.movie.MovieResponse;
import com.example.movies_app_java.data.remote.model.production_company.ProductionCompanyResponse;
import com.example.movies_app_java.data.remote.model.spoken_languages.SpokenLanguagesResponse;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.details.ProductionCompanyModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.ArrayList;

public class ResponseToModel {

    private ResponseToModel() {
    }

    public static ArrayList<MovieModel> convertToMovieListModel(ArrayList<MovieResponse> responses) {
        ArrayList<MovieModel> movies = new ArrayList<>();

        for (MovieResponse response : responses) {
            movies.add(new MovieModel(response.getId(), response.getVoteAverage(), response.getTitle(), response.getImageUrl(), response.getReleaseDate(), response.getGenres()));
        }
        return movies;
    }

    public static MovieDetailsModel convertToMovieDetailsModel(MovieDetailsResponse response) {
        ArrayList<String> spokenLanguages = new ArrayList<>();
        if (response.getSpokenLanguages() != null) {
            for (SpokenLanguagesResponse spokenLanguage : response.getSpokenLanguages()) {
                spokenLanguages.add(convertIfIsNullOrBlank(spokenLanguage.getName()));
            }
        }

        ArrayList<ProductionCompanyModel> productionCompanies = new ArrayList<>();
        if (response.getProductionCompanies() != null) {
            for (ProductionCompanyResponse company : response.getProductionCompanies()) {
                productionCompanies.add(new ProductionCompanyModel(
                        company.getId() != null ? company.getId() : NullResponseConstants.nullIntResponse,
                        convertIfIsNullOrBlank(company.getLogoUrl()),
                        convertIfIsNullOrBlank(company.getName()),
                        convertIfIsNullOrBlank(company.getOriginCountry())
                ));
            }
        }

        return new MovieDetailsModel(
                response.getIsAdult() != null ? response.getIsAdult() : NullResponseConstants.nullBoolResponse,
                response.getBudget() != null ? response.getBudget() : NullResponseConstants.nullIntResponse,
                response.getGenres() != null ? response.getGenres() : new ArrayList<String>(),
                response.getId() != null ? response.getId() : NullResponseConstants.nullIntResponse,
                convertIfIsNullOrBlank(response.getOriginalLanguage()),
                convertIfIsNullOrBlank(response.getOriginalTitle()),
                convertIfIsNullOrBlank(response.getOverview()),
                convertIfIsNullOrBlank(response.getPosterUrl()),
                productionCompanies,
                convertIfIsNullOrBlank(response.getReleaseDate()),
                response.getRevenue() != null ? response.getRevenue() : NullResponseConstants.nullIntResponse,
                response.getRuntime() != null ? response.getRuntime() : NullResponseConstants.nullIntResponse,
                spokenLanguages,
                convertIfIsNullOrBlank(response.getStatus()),
                convertIfIsNullOrBlank(response.getTitle()),
                response.getVoteAverage() != null ? response.getVoteAverage() : NullResponseConstants.nullDoubleResponse
        );
    }
}