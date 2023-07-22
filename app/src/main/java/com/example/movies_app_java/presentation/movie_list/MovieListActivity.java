package com.example.movies_app_java.presentation.movie_list;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;

import com.example.movies_app_java.R;
import com.example.movies_app_java.data.api.Api;
import com.example.movies_app_java.data.api.MovieDataService;
import com.example.movies_app_java.data.remote.data_source.MovieRemoteDataSourceImpl;
import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.data.repository.MovieRepositoryImpl;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.domain.repository.MovieRepository;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCase;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCaseImpl;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MovieListActivity extends AppCompatActivity {

    MoviesRemoteDataSource moviesRemoteDataSource = new MovieRemoteDataSourceImpl(getMovieDataService());
    MovieRepository movieRepository = new MovieRepositoryImpl(moviesRemoteDataSource);
    GetMovieListUseCase getMovieListUseCase = new GetMovieListUseCaseImpl(movieRepository);
    MovieListViewModel viewModel = new MovieListViewModel(getMovieListUseCase);

    ArrayList<MovieModel> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieList = new ArrayList<>();
    }

    private MovieDataService getMovieDataService() {
        Retrofit retrofit = Api.setupRetrofit();
        return retrofit.create(MovieDataService.class);
    }
}