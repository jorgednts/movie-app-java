package com.example.movies_app_java.presentation.movie_list;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies_app_java.domain.exception.CustomNetworkException;
import com.example.movies_app_java.domain.exception.GenericErrorException;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCase;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListViewModel extends ViewModel {
    final GetMovieListUseCase _getMovieListUseCase;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Observable<ArrayList<MovieModel>>> movieList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MovieListViewModel(GetMovieListUseCase getMovieListUseCase) {
        _getMovieListUseCase = getMovieListUseCase;
    }


    public MutableLiveData<Observable<ArrayList<MovieModel>>> getMovieListLiveData() {
        return movieList;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getErrorMessageLiveData() {
        return errorMessage;
    }


    public void getMovieList() {
        isLoading.setValue(true);
        disposables.add(Observable.fromCallable(_getMovieListUseCase::call)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movies -> {
                            movieList.setValue(movies);
                            isLoading.setValue(false);
                        },
                        throwable -> {
                            isLoading.setValue(false);
                            if (throwable instanceof CustomNetworkException) {
                                errorMessage.setValue("Custom Network Exception occurred");
                            } else if (throwable instanceof GenericErrorException) {
                                errorMessage.setValue("Generic Error Exception occurred");
                            } else {
                                errorMessage.setValue("Unknown error occurred");
                            }
                        }
                ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
