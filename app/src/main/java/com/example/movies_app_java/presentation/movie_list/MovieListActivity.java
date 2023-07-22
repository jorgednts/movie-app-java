package com.example.movies_app_java.presentation.movie_list;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    MoviesRemoteDataSource moviesRemoteDataSource;
    MovieRepository movieRepository;
    GetMovieListUseCase getMovieListUseCase;
    MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        moviesRemoteDataSource = new MovieRemoteDataSourceImpl(getMovieDataService());
        movieRepository = new MovieRepositoryImpl(moviesRemoteDataSource);
        getMovieListUseCase = new GetMovieListUseCaseImpl(movieRepository);
        viewModel = new MovieListViewModel(getMovieListUseCase);

        RecyclerView recyclerView = findViewById(R.id.movieListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter adapter = new MovieAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel.getMovieListLiveData().observe(this, movieList -> {
            ArrayList<MovieModel> movies = movieList.blockingFirst();
            adapter.updateData(movies);
        });
        viewModel.getErrorMessageLiveData().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getMovieList();
    }

    private MovieDataService getMovieDataService() {
        Retrofit retrofit = Api.setupRetrofit();
        return retrofit.create(MovieDataService.class);
    }
}