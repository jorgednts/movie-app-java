package com.example.movies_app_java;

import com.example.movies_app_java.data.remote.model.details.MovieDetailsResponse;
import com.example.movies_app_java.data.remote.model.movie.MovieResponse;
import com.example.movies_app_java.data.remote.model.production_company.ProductionCompanyResponse;
import com.example.movies_app_java.data.remote.model.spoken_languages.SpokenLanguagesResponse;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.details.ProductionCompanyModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.ArrayList;

public class UnitTestUtils {
    public static ArrayList<MovieModel> getSuccessfulMovieListModelMock() {
        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Comedy");
        genres1.add("Drama");
        genres1.add("Romance");

        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Drama");
        genres2.add("Crime");

        MovieModel movie1 = new MovieModel(
                19404,
                9.3,
                "Dilwale Dulhania Le Jayenge",
                "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "1995-10-20",
                genres1
        );

        MovieModel movie2 = new MovieModel(
                278,
                8.6,
                "The Shawshank Redemption",
                "https://image.tmdb.org/t/p/w200/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "1994-09-23",
                genres2
        );

        ArrayList<MovieModel> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        return movieList;
    }

    public static ArrayList<MovieResponse> getSuccessfulMovieListResponseMock() {
        ArrayList<String> genres1 = new ArrayList<>();
        genres1.add("Comedy");
        genres1.add("Drama");
        genres1.add("Romance");

        ArrayList<String> genres2 = new ArrayList<>();
        genres2.add("Drama");
        genres2.add("Crime");

        MovieResponse movie1 = new MovieResponse(
                19404,
                9.3,
                "Dilwale Dulhania Le Jayenge",
                "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "1995-10-20",
                genres1
        );

        MovieResponse movie2 = new MovieResponse(
                278,
                8.6,
                "The Shawshank Redemption",
                "https://image.tmdb.org/t/p/w200/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "1994-09-23",
                genres2
        );

        ArrayList<MovieResponse> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        return movieList;
    }
    public static MovieDetailsModel getSuccessfulMovieDetailsModelMock() {
        ArrayList<String> genres = new ArrayList<>();
        genres.add("Comedy");
        genres.add("Drama");
        genres.add("Romance");

        ArrayList<ProductionCompanyModel> productionCompanies = new ArrayList<>();
        ProductionCompanyModel productionCompany = new ProductionCompanyModel(
                1569,
                "https://image.tmdb.org/t/p/w200/5WSkzUe6OiyKlpX2hJUghLlWkiU.png",
                "Yash Raj Films",
                "IN"
        );
        productionCompanies.add(productionCompany);

        ArrayList<String> spokenLanguages = new ArrayList<>();
        spokenLanguages.add("हिन्दी");

        return new MovieDetailsModel(
                false,
                13200000,
                genres,
                19404,
                "hi",
                "दिलवाले दुल्हनिया ले जायेंगे",
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                productionCompanies,
                "1995-10-20",
                100000000,
                190,
                spokenLanguages,
                "Released",
                "Dilwale Dulhania Le Jayenge",
                9.3
        );
    }

    public static MovieDetailsResponse getSuccessfulMovieDetailsResponseMock() {
        ArrayList<String> genres = new ArrayList<>();
        genres.add("Comedy");
        genres.add("Drama");
        genres.add("Romance");

        ArrayList<ProductionCompanyResponse> productionCompanies = new ArrayList<>();
        ProductionCompanyResponse productionCompany = new ProductionCompanyResponse(
                1569,
                "https://image.tmdb.org/t/p/w200/5WSkzUe6OiyKlpX2hJUghLlWkiU.png",
                "Yash Raj Films",
                "IN"
        );
        productionCompanies.add(productionCompany);

        ArrayList<SpokenLanguagesResponse> spokenLanguages = new ArrayList<>();
        spokenLanguages.add(new SpokenLanguagesResponse("हिन्दी"));

        return new MovieDetailsResponse(
                false,
                13200000,
                genres,
                19404,
                "hi",
                "दिलवाले दुल्हनिया ले जायेंगे",
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                "https://image.tmdb.org/t/p/w200/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                productionCompanies,
                "1995-10-20",
                100000000,
                190,
                spokenLanguages,
                "Released",
                "Dilwale Dulhania Le Jayenge",
                9.3
        );
    }
}
