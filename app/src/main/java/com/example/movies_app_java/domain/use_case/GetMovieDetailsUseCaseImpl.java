package com.example.movies_app_java.domain.use_case;

import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import io.reactivex.Observable;

public class GetMovieDetailsUseCaseImpl implements GetMovieDetailsUseCase {

    final MovieRepository _movieRepository;

    public GetMovieDetailsUseCaseImpl(MovieRepository movieRepository) {
        _movieRepository = movieRepository;
    }

    @Override
    public Observable<MovieDetailsModel> call(int movieId) throws Exception {
        return _movieRepository.getMovieDetails(movieId);
    }
}
