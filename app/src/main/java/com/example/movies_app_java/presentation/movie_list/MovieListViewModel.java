package com.example.movies_app_java.presentation.movie_list;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies_app_java.domain.exception.CustomNetworkException;
import com.example.movies_app_java.domain.exception.GenericErrorException;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCase;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

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


    void getMovieList() throws Exception {
        isLoading.setValue(true);
        disposables.add(_getMovieListUseCase.call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> isLoading.setValue(false))
                .subscribe(
                        movieList::setValue,
                        throwable -> {
                            if (throwable instanceof CustomNetworkException) {
                                isLoading.setValue(false);
                                errorMessage.setValue("Custom Network Exception occurred");
                            } else if (throwable instanceof GenericErrorException) {
                                isLoading.setValue(false);
                                errorMessage.setValue("Generic Error Exception occurred");
                            } else {
                                isLoading.setValue(false);
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
