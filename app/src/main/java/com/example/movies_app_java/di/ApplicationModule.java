package com.example.movies_app_java.di;

import com.example.movies_app_java.data.api.Api;
import com.example.movies_app_java.data.api.MovieDataService;
import com.example.movies_app_java.data.remote.data_source.MovieRemoteDataSourceImpl;
import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.data.repository.MovieRepositoryImpl;
import com.example.movies_app_java.domain.repository.MovieRepository;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCase;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCaseImpl;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCase;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCaseImpl;
import com.example.movies_app_java.presentation.movie_details.MovieDetailsViewModel;
import com.example.movies_app_java.presentation.movie_list.MovieListViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    public MovieDataService getMovieDataService() {
        return Api.setupRetrofit().create(MovieDataService.class);
    }

    @Provides
    public MoviesRemoteDataSource getMovieRemoteDataSource(MovieDataService movieDataService) {
        return new MovieRemoteDataSourceImpl(movieDataService);
    }

    @Provides
    public MovieRepository getMovieRepository(MoviesRemoteDataSource movieRemoteDataSource) {
        return new MovieRepositoryImpl(movieRemoteDataSource);
    }

    @Provides
    public GetMovieListUseCase getMovieListUseCase(MovieRepository movieRepository) {
        return new GetMovieListUseCaseImpl(movieRepository);
    }

    @Provides
    public GetMovieDetailsUseCase getMovieDetailsUseCase(MovieRepository movieRepository) {
        return new GetMovieDetailsUseCaseImpl(movieRepository);
    }

    @Provides
    public MovieListViewModel getMovieListViewModel(GetMovieListUseCase getMovieListUseCase) {
        return new MovieListViewModel(getMovieListUseCase);
    }

    @Provides
    public MovieDetailsViewModel getMovieDetailsViewModel(GetMovieDetailsUseCase getMovieDetailsUseCase) {
        return new MovieDetailsViewModel(getMovieDetailsUseCase);
    }
}
