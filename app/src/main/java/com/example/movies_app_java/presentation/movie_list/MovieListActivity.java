package com.example.movies_app_java.presentation.movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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
import com.example.movies_app_java.domain.repository.MovieRepository;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCase;
import com.example.movies_app_java.domain.use_case.GetMovieListUseCaseImpl;
import com.example.movies_app_java.presentation.movie_details.MovieDetailsActivity;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MovieListActivity extends AppCompatActivity {

    MoviesRemoteDataSource moviesRemoteDataSource;
    MovieRepository movieRepository;
    GetMovieListUseCase getMovieListUseCase;
    MovieListViewModel viewModel;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_list);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.movieListRecyclerView);

        moviesRemoteDataSource = new MovieRemoteDataSourceImpl(getMovieDataService());
        movieRepository = new MovieRepositoryImpl(moviesRemoteDataSource);
        getMovieListUseCase = new GetMovieListUseCaseImpl(movieRepository);
        viewModel = new MovieListViewModel(getMovieListUseCase);
        adapter = new MovieAdapter(new ArrayList<>());
        adapter.setOnButtonClickListener(movieId -> {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId);
            startActivity(intent);
        });

        configureRecyclerView();

        setErrorMessageObserver();
        setMovieListObserver();

        viewModel.getMovieList();
    }

    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setMovieListObserver() {
        viewModel.getMovieListLiveData().observe(this, movieListObservable -> movieListObservable
                .subscribe(
                        movieListData -> {
                            adapter.updateData(movieListData);
                            progressBar.setVisibility(View.GONE);
                        },
                        throwable -> Toast.makeText(this, "Error fetching movie list.", Toast.LENGTH_SHORT).show()
                )
        );
    }

    private void setErrorMessageObserver() {
        viewModel.getErrorMessageLiveData().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLoadingObserver() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private MovieDataService getMovieDataService() {
        Retrofit retrofit = Api.setupRetrofit();
        return retrofit.create(MovieDataService.class);
    }
}