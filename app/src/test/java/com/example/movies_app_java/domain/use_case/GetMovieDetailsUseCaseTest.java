package com.example.movies_app_java.domain.use_case;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.movies_app_java.UnitTestUtils;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.repository.MovieRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class GetMovieDetailsUseCaseTest {
    @Mock
    private MovieRepository movieRepository;
    private GetMovieDetailsUseCaseImpl useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = new GetMovieDetailsUseCaseImpl(movieRepository);
    }

    @Test
    public void getMovieListSuccessTest() {
        int movieId = 19404;
        when(movieRepository.getMovieDetails(movieId))
                .thenReturn(Observable.just(UnitTestUtils.getSuccessfulMovieDetailsModelMock()));

        TestObserver<MovieDetailsModel> testObserver = useCase.call(movieId).test();
        verify(movieRepository).getMovieDetails(movieId);
        testObserver.assertValue(UnitTestUtils.getSuccessfulMovieDetailsModelMock());
    }
}
