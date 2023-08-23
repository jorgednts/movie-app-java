package com.example.movies_app_java.data.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.movies_app_java.UnitTestUtils;
import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class MoviesRepositoryTest {
    @Mock
    private MoviesRemoteDataSource remoteDataSource;
    private MovieRepositoryImpl movieRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieRepository = new MovieRepositoryImpl(remoteDataSource);
    }

    @Test
    public void getMovieListSuccessTest() {
        when(remoteDataSource.getMovieList())
                .thenReturn(Observable.just(UnitTestUtils.getSuccessfulMovieListModelMock()));

        TestObserver<ArrayList<MovieModel>> testObserver = movieRepository.getMovieList().test();
        verify(remoteDataSource).getMovieList();
        testObserver.assertValue(UnitTestUtils.getSuccessfulMovieListModelMock());
    }

    @Test
    public void getMovieDetailsSuccessTest() {
        int movieId = 19404;
        when(remoteDataSource.getMovieDetails(movieId))
                .thenReturn(Observable.just(UnitTestUtils.getSuccessfulMovieDetailsModelMock()));

        TestObserver<MovieDetailsModel> testObserver = movieRepository.getMovieDetails(movieId).test();
        verify(remoteDataSource).getMovieDetails(movieId);
        testObserver.assertValue(UnitTestUtils.getSuccessfulMovieDetailsModelMock());
    }
}
