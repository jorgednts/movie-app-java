package com.example.movies_app_java.domain.repository;

import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface MovieRepository {
    Observable<MovieDetailsModel> getMovieDetails(int movieId);

    Observable<ArrayList<MovieModel>> getMovieList();
}
