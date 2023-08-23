package com.example.movies_app_java.domain.use_case;

import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import io.reactivex.Observable;

public class GetMovieDetailsUseCaseImpl implements GetMovieDetailsUseCase {

    final MovieRepository movieRepository;

    public GetMovieDetailsUseCaseImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<MovieDetailsModel> call(int movieId) {
        return movieRepository.getMovieDetails(movieId);
    }
}
