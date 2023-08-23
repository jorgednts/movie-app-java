package com.example.movies_app_java.domain.use_case;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.movies_app_java.UnitTestUtils;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class GetMovieListUseCaseTest {
    @Mock
    private MovieRepository movieRepository;
    private GetMovieListUseCaseImpl useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetMovieListUseCaseImpl(movieRepository);
    }

    @Test
    public void getMovieListSuccessTest() {
        when(movieRepository.getMovieList())
                .thenReturn(Observable.just(UnitTestUtils.getSuccessfulMovieListModelMock()));

        TestObserver<ArrayList<MovieModel>> testObserver = useCase.call().test();
        verify(movieRepository).getMovieList();
        testObserver.assertValue(UnitTestUtils.getSuccessfulMovieListModelMock());
    }
}
