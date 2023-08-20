package com.example.movies_app_java.presentation.movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app_java.R;
import com.example.movies_app_java.di.ApplicationComponent;
import com.example.movies_app_java.di.DaggerApplicationComponent;
import com.example.movies_app_java.presentation.common.ErrorFragment;
import com.example.movies_app_java.presentation.movie_details.MovieDetailsActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class MovieListActivity extends AppCompatActivity {

    @Inject
    MovieListViewModel viewModel;

    RecyclerView recyclerView;
    MovieAdapter adapter;
    ProgressBar progressBar;
    View errorView;
    private ErrorFragment errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent component = DaggerApplicationComponent.create();
        component.injectInMovieListActivity(this);

        setViews();

        setMovieAdapter();
        setErrorFragment();
        configureRecyclerView();

        setLoadingObserver();
        setErrorMessageObserver();
        setMovieListObserver();

        viewModel.getMovieList();
    }

    private void setViews() {
        setContentView(R.layout.activity_movie_list);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.movieListRecyclerView);
        errorView = findViewById(R.id.errorView);
    }

    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setMovieListObserver() {
        viewModel.getMovieListLiveData().observe(this, movieListData -> {
                    adapter.updateData(movieListData);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
        );
    }

    private void setErrorMessageObserver() {
        viewModel.getErrorMessageLiveData().observe(this, errorMessage -> {
            if (errorMessage.equals("")) {
                errorView.setVisibility(View.GONE);
            } else {
                errorView.setVisibility(View.VISIBLE);
                errorFragment.showError(errorMessage);
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setLoadingObserver() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                errorView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setMovieAdapter() {
        adapter = new MovieAdapter(new ArrayList<>());
        adapter.setOnButtonClickListener(movieId -> {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId);
            startActivity(intent);
        });
    }

    private void setErrorFragment() {
        errorFragment = new ErrorFragment(true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.errorView, errorFragment)
                .commit();
        errorFragment.setOnRetryButtonClickListener(() ->
                viewModel.getMovieList());
    }
}