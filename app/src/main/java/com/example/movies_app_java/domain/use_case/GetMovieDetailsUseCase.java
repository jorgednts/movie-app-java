package com.example.movies_app_java.domain.use_case;

import com.example.movies_app_java.domain.model.details.MovieDetailsModel;

import io.reactivex.Observable;

public interface GetMovieDetailsUseCase{
    Observable<MovieDetailsModel> call(int movieId) throws Exception;
}

