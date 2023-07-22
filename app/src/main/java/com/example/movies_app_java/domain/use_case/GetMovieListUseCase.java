package com.example.movies_app_java.domain.use_case;

import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface GetMovieListUseCase {
    Observable<ArrayList<MovieModel>> call() throws Exception;
}

