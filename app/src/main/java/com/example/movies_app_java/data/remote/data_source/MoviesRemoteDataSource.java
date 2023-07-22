package com.example.movies_app_java.data.remote.data_source;

import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface MoviesRemoteDataSource {
    Observable<MovieDetailsModel> getMovieDetails(int movieId) throws Exception;

    Observable<ArrayList<MovieModel>> getMovieList() throws Exception;
}
