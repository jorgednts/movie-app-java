package com.example.movies_app_java.data.repository;

import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import java.util.ArrayList;

import io.reactivex.Observable;

public class MovieRepositoryImpl implements MovieRepository {

    final MoviesRemoteDataSource _moviesRemoteDataSource;

    public MovieRepositoryImpl(MoviesRemoteDataSource moviesRemoteDataSource) {
        _moviesRemoteDataSource = moviesRemoteDataSource;
    }


    @Override
    public Observable<MovieDetailsModel> getMovieDetails(int movieId) throws Exception {
        return _moviesRemoteDataSource.getMovieDetails(movieId);
    }

    @Override
    public Observable<ArrayList<MovieModel>> getMovieList() throws Exception {

        return _moviesRemoteDataSource.getMovieList();

    }

}
