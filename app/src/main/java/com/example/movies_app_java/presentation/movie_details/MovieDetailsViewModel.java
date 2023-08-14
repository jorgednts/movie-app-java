package com.example.movies_app_java.presentation.movie_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsViewModel extends ViewModel {
    final GetMovieDetailsUseCase _getMovieDetailsUseCase;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<Observable<MovieDetailsModel>> movieDetailsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MovieDetailsViewModel(GetMovieDetailsUseCase getMovieDetailsUseCase) {
        _getMovieDetailsUseCase = getMovieDetailsUseCase;
    }

    public MutableLiveData<Observable<MovieDetailsModel>> getMovieDetailsLiveData() {
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
                Observable.fromCallable(() -> _getMovieDetailsUseCase.call(movieId))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                movieDetailsData -> {
                                    movieDetailsLiveData.setValue(movieDetailsData);
                                    isLoading.setValue(false);
                                },
                                throwable -> {
                                    isLoading.setValue(false);
                                    errorMessage.setValue("Error loading movie details");
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
