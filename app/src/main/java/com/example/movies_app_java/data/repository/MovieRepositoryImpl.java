package com.example.movies_app_java.data.repository;

import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import java.util.ArrayList;

import io.reactivex.Observable;

public class MovieRepositoryImpl implements MovieRepository {

    final MoviesRemoteDataSource moviesRemoteDataSource;

    public MovieRepositoryImpl(MoviesRemoteDataSource moviesRemoteDataSource) {
        this.moviesRemoteDataSource = moviesRemoteDataSource;
    }


    @Override
    public Observable<MovieDetailsModel> getMovieDetails(int movieId){
        return moviesRemoteDataSource.getMovieDetails(movieId);
    }

    @Override
    public Observable<ArrayList<MovieModel>> getMovieList(){
        return moviesRemoteDataSource.getMovieList();
    }

}
