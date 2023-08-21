package com.example.movies_app_java.presentation.movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app_java.R;
import com.example.movies_app_java.databinding.ActivityMovieListBinding;
import com.example.movies_app_java.di.ApplicationComponent;
import com.example.movies_app_java.di.DaggerApplicationComponent;
import com.example.movies_app_java.presentation.common.ErrorFragment;
import com.example.movies_app_java.presentation.movie_details.MovieDetailsActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class MovieListActivity extends AppCompatActivity {

    @Inject
    MovieListViewModel viewModel;
    private MovieAdapter adapter;
    private ErrorFragment errorFragment;
    private ActivityMovieListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponent component = DaggerApplicationComponent.create();
        component.injectInMovieListActivity(this);

        setupView();
        setupObservers();

        viewModel.getMovieList();
    }
    private void setupView() {
        binding = ActivityMovieListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupErrorFragment();
    }

    private void setupObservers() {
        setupMovieListObserver();
        setupErrorMessageObserver();
        setupLoadingObserver();
    }

    private void setupMovieListObserver() {
        viewModel.getMovieListLiveData().observe(this, movieListData -> {
                    setupMovieAdapter();
                    configureRecyclerView();
                    adapter.updateData(movieListData);
            binding.progressBar.setVisibility(View.GONE);
                }
        );
    }

    private void setupErrorMessageObserver() {
        viewModel.getErrorMessageLiveData().observe(this, errorMessage -> {
            if (errorMessage.equals("")) {
                binding.errorView.setVisibility(View.GONE);
            } else {
                binding.errorView.setVisibility(View.VISIBLE);
                errorFragment.showError(errorMessage);
                binding. progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setupLoadingObserver() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.errorView.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void configureRecyclerView() {
        binding.movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.movieListRecyclerView.setAdapter(adapter);
        binding.movieListRecyclerView.setHasFixedSize(true);
    }

    private void setupMovieAdapter() {
        adapter = new MovieAdapter(new ArrayList<>());
        adapter.setOnButtonClickListener(movieId -> {
            Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId);
            startActivity(intent);
        });
    }

    private void setupErrorFragment() {
        errorFragment = new ErrorFragment(true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.errorView, errorFragment)
                .commit();
        errorFragment.setOnRetryButtonClickListener(() ->
                viewModel.getMovieList());
    }
}