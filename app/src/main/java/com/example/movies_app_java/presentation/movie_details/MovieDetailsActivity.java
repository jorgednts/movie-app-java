package com.example.movies_app_java.presentation.movie_details;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.movies_app_java.R;
import com.example.movies_app_java.databinding.ActivityMovieDetailsBinding;
import com.example.movies_app_java.di.ApplicationComponent;
import com.example.movies_app_java.di.DaggerApplicationComponent;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.details.ProductionCompanyModel;
import com.example.movies_app_java.presentation.common.ErrorFragment;
import com.example.movies_app_java.presentation.common.HorizontalListAdapter;
import com.example.movies_app_java.presentation.common.StringUtils;
import com.example.movies_app_java.presentation.movie_list.HorizontalListBorderlessAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
    @Inject
    MovieDetailsViewModel viewModel;
    private ErrorFragment errorFragment;
    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent component = DaggerApplicationComponent.create();
        component.injectInMovieDetailsActivity(this);

        int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);

        setupViews();

        setErrorFragment(movieId);
        setLoadingObserver();
        setErrorMessageObserver();
        setMovieDetailsDataObserver();

        viewModel.getMovieDetails(movieId);
    }

    private void setupViews() {
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.errorView.setVisibility(View.GONE);
    }

    private void setErrorFragment(int movieId) {
        errorFragment = new ErrorFragment(false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.errorView, errorFragment)
                .commit();
        errorFragment.setOnRetryButtonClickListener(() -> viewModel.getMovieDetails(movieId));
    }

    private void setLoadingObserver() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                setTitle("Loading...");
                binding.errorView.setVisibility(View.GONE);
                binding.successLayout.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setErrorMessageObserver() {
        viewModel.getErrorMessage().observe(this, errorMessage ->
        {
            if (errorMessage.equals("")) {
                binding.errorView.setVisibility(View.GONE);
            } else {
                errorFragment.showError(errorMessage);
                setTitle("");
                binding.successLayout.setVisibility(View.GONE);
                binding.errorView.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setMovieDetailsDataObserver() {
        viewModel.getMovieDetailsLiveData().observe(this, this::configureView);
    }

    private void configureView(MovieDetailsModel movieDetails) {
        binding.progressBar.setVisibility(View.GONE);
        binding.successLayout.setVisibility(View.VISIBLE);
        setTitle(movieDetails.getTitle());

        binding.originalTitle.setText(movieDetails.getOriginalTitle());
        String language = "(" + movieDetails.getOriginalLanguage().toUpperCase() + ")";
        binding.originalLanguage.setText(language);


        binding.releaseDate.setText(movieDetails.getReleaseDate());
        binding.duration.setText(getString(R.string.movie_duration_format, movieDetails.getRuntime()));

        Glide.with(this)
                .load(movieDetails.getPosterUrl())
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(binding.poster);
        binding.genreList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HorizontalListAdapter adapter = new HorizontalListAdapter(movieDetails.getGenres());
        binding.genreList.setAdapter(adapter);
        binding.overview.setText(movieDetails.getOverview());

        binding.rating.setText(getString(R.string.movie_vote_average_format, movieDetails.getVoteAverage()));
        binding.status.setText(movieDetails.getStatus());

        ArrayList<String> productionCompaniesNames = movieDetails.getProductionCompanies()
                .stream().map(ProductionCompanyModel::getName)
                .collect(Collectors.toCollection(ArrayList::new));
        binding.productionCompanies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HorizontalListAdapter productionCompaniesAdapter = new HorizontalListAdapter(productionCompaniesNames);
        binding.productionCompanies.setAdapter(productionCompaniesAdapter);
        binding.revenue.setText(getString(R.string.movie_budget_format, StringUtils.formatInt(movieDetails.getBudget())));
        binding.availableIn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HorizontalListBorderlessAdapter availableInAdapter = new HorizontalListBorderlessAdapter(movieDetails.getSpokenLanguages());
        binding.availableIn.setAdapter(availableInAdapter);
    }
}