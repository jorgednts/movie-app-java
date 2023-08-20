package com.example.movies_app_java.presentation.movie_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies_app_java.domain.exception.CustomNetworkException;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCase;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailsViewModel extends ViewModel {
    final GetMovieDetailsUseCase _getMovieDetailsUseCase;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<MovieDetailsModel> movieDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MovieDetailsViewModel(GetMovieDetailsUseCase getMovieDetailsUseCase) {
        _getMovieDetailsUseCase = getMovieDetailsUseCase;
    }

    public MutableLiveData<MovieDetailsModel> getMovieDetailsLiveData() {
        return movieDetailsLiveData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void getMovieDetails(int movieId) {
        isLoading.setValue(true);
        disposables.add(
                _getMovieDetailsUseCase.call(movieId)
                        .subscribe(
                                movieDetailsData -> {
                                    movieDetailsLiveData.setValue(movieDetailsData);
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
