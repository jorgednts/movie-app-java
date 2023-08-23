package com.example.movies_app_java.domain.use_case;

import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import java.util.ArrayList;

import io.reactivex.Observable;

public class GetMovieListUseCaseImpl implements GetMovieListUseCase {
    private final MovieRepository movieRepository;

    public GetMovieListUseCaseImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<ArrayList<MovieModel>> call() {
        return movieRepository.getMovieList();
    }
}
