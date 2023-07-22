package com.example.movies_app_java.data.remote.data_source;

import com.example.movies_app_java.data.api.MovieDataService;
import com.example.movies_app_java.data.mapper.ResponseToModel;
import com.example.movies_app_java.domain.exception.CustomNetworkException;
import com.example.movies_app_java.domain.exception.GenericErrorException;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MovieRemoteDataSourceImpl implements MoviesRemoteDataSource {
    private final MovieDataService movieDataService;

    public MovieRemoteDataSourceImpl(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }

    @Override
    public Observable<MovieDetailsModel> getMovieDetails(int movieId) {

        return movieDataService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ResponseToModel::convertToMovieDetailsModel)
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof HttpException
                            && ((HttpException) throwable).code() == 404) {
                        return Observable.error(new GenericErrorException());
                    } else {
                        return Observable.error(new CustomNetworkException());
                    }
                });

    }

    @Override
    public Observable<ArrayList<MovieModel>> getMovieList() {
        return movieDataService.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ResponseToModel::convertToMovieListModel)
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof HttpException
                            && ((HttpException) throwable).code() == 404) {
                        return Observable.error(new GenericErrorException());
                    } else {
                        return Observable.error(new CustomNetworkException());
                    }
                });
    }

}
