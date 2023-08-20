package com.example.movies_app_java.domain.use_case;

import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import java.util.ArrayList;

import io.reactivex.Observable;

public class GetMovieListUseCaseImpl implements GetMovieListUseCase {
    final MovieRepository _movieRepository;

    public GetMovieListUseCaseImpl(MovieRepository movieRepository) {
        _movieRepository = movieRepository;
    }

    @Override
    public Observable<ArrayList<MovieModel>> call() {
        return _movieRepository.getMovieList();
    }
}
