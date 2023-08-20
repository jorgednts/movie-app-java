package com.example.movies_app_java.presentation.movie_list;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies_app_java.domain.exception.CustomNetworkException;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCase;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends ViewModel {
    final GetMovieListUseCase _getMovieListUseCase;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ArrayList<MovieModel>> movieList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MovieListViewModel(GetMovieListUseCase getMovieListUseCase) {
        _getMovieListUseCase = getMovieListUseCase;
    }

    public MutableLiveData<ArrayList<MovieModel>> getMovieListLiveData() {
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
        errorMessage.setValue("");
        disposables.add(_getMovieListUseCase.call()
                .subscribe(
                        movieListData -> {
                            movieList.setValue(movieListData);
                            isLoading.setValue(false);
                        },
                        throwable -> {
                            isLoading.setValue(false);
                            if (throwable instanceof CustomNetworkException) {
                                errorMessage.setValue("Network error!");
                            } else {
                                errorMessage.setValue("An error has occurred!");
                            }
                        }
                )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
