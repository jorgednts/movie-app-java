package com.example.movies_app_java.data.remote_data_source;

import static org.mockito.Mockito.when;

import com.example.movies_app_java.UnitTestUtils;
import com.example.movies_app_java.data.api.MovieDataService;
import com.example.movies_app_java.data.remote.data_source.MovieRemoteDataSourceImpl;
import com.example.movies_app_java.domain.exception.CustomNetworkException;
import com.example.movies_app_java.domain.exception.GenericErrorException;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class MoviesRemoteDataSourceTest {

    @Mock
    private MovieDataService movieDataService;
    private MovieRemoteDataSourceImpl movieRemoteDataSource;
    private final int movieId = 19404;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieRemoteDataSource = new MovieRemoteDataSourceImpl(movieDataService);
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
        RxAndroidPlugins.
                setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void getMovieListSuccessTest() {
        when(movieDataService.getMovieList())
                .thenReturn(Observable.just(UnitTestUtils.getSuccessfulMovieListResponseMock()));

        TestObserver<ArrayList<MovieModel>> testObserver = movieRemoteDataSource.getMovieList().test();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertValue(UnitTestUtils.getSuccessfulMovieListModelMock());
    }

    @Test
    public void getMovieListNetworkErrorTest() {
        when(movieDataService.getMovieList())
                .thenReturn(Observable.error(new UnknownHostException()));

        movieRemoteDataSource.getMovieList()
                .test()
                .assertError(CustomNetworkException.class);
    }

    @Test
    public void getMovieListGenericErrorTest() {
        when(movieDataService.getMovieList())
                .thenReturn(Observable.error(new Exception()));

        movieRemoteDataSource.getMovieList()
                .test()
                .assertError(GenericErrorException.class);
    }

    @Test
    public void getMovieDetailsSuccessTest() {
        when(movieDataService.getMovieDetails(movieId))
                .thenReturn(Observable.just(UnitTestUtils.getSuccessfulMovieDetailsResponseMock()));

        TestObserver<MovieDetailsModel> testObserver =
                movieRemoteDataSource.getMovieDetails(movieId).test();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertResult(UnitTestUtils.getSuccessfulMovieDetailsModelMock());
    }

    @Test
    public void getMovieDetailsNetworkErrorTest() {
        when(movieDataService.getMovieDetails(movieId))
                .thenReturn(Observable.error(new UnknownHostException()));

        movieRemoteDataSource.getMovieDetails(movieId)
                .test()
                .assertError(CustomNetworkException.class);
    }

    @Test
    public void getMovieDetailsGenericErrorTest() {
        when(movieDataService.getMovieDetails(movieId))
                .thenReturn(Observable.error(new Exception()));

        movieRemoteDataSource.getMovieDetails(movieId)
                .test()
                .assertError(GenericErrorException.class);
    }
}
