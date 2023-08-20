package com.example.movies_app_java.presentation.movie_details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_app_java.R;
import com.example.movies_app_java.data.api.Api;
import com.example.movies_app_java.data.api.MovieDataService;
import com.example.movies_app_java.data.remote.data_source.MovieRemoteDataSourceImpl;
import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.data.repository.MovieRepositoryImpl;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.model.details.ProductionCompanyModel;
import com.example.movies_app_java.domain.repository.MovieRepository;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCase;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCaseImpl;
import com.example.movies_app_java.presentation.common.ErrorFragment;
import com.example.movies_app_java.presentation.common.HorizontalListAdapter;
import com.example.movies_app_java.presentation.common.StringUtils;
import com.example.movies_app_java.presentation.movie_list.HorizontalListBorderlessAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Retrofit;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    MoviesRemoteDataSource moviesRemoteDataSource;
    MovieRepository movieRepository;
    GetMovieDetailsUseCase getMovieDetailsUseCase;
    MovieDetailsViewModel viewModel;
    ProgressBar progressBar;
    TextView originalTitleTag;
    TextView originalTitle;
    TextView originalLanguage;
    TextView releaseDate;
    TextView duration;
    ConstraintLayout successLayout;
    ImageView poster;
    RecyclerView genreList;
    TextView overview;
    TextView status;
    TextView rating;
    RecyclerView productionCompanies;
    TextView revenue;
    RecyclerView availableIn;
    View errorView;
    private ErrorFragment errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);

        setViews();

        instantiateDependencies();

        setErrorFragment(movieId);
        setLoadingObserver();
        setErrorMessageObserver();
        setMovieDetailsDataObserver();

        viewModel.getMovieDetails(movieId);
    }

    private void setViews() {
        setContentView(R.layout.activity_movie_details);
        progressBar = findViewById(R.id.progressBar);
        errorView = findViewById(R.id.errorView);
        errorView.setVisibility(View.GONE);

        originalLanguage = findViewById(R.id.originalLanguage);
        originalTitle = findViewById(R.id.originalTitle);
        originalTitleTag = findViewById(R.id.originalTitleTag);
        releaseDate = findViewById(R.id.releaseDate);
        duration = findViewById(R.id.duration);

        successLayout = findViewById(R.id.successLayout);
        poster = findViewById(R.id.poster);
        genreList = findViewById(R.id.genreList);
        overview = findViewById(R.id.overview);

        status = findViewById(R.id.status);
        rating = findViewById(R.id.rating);

        productionCompanies = findViewById(R.id.productionCompanies);
        revenue = findViewById(R.id.revenue);
        availableIn = findViewById(R.id.availableIn);
    }

    private void instantiateDependencies() {
        moviesRemoteDataSource = new MovieRemoteDataSourceImpl(getMovieDataService());
        movieRepository = new MovieRepositoryImpl(moviesRemoteDataSource);
        getMovieDetailsUseCase = new GetMovieDetailsUseCaseImpl(movieRepository);
        viewModel = new MovieDetailsViewModel(getMovieDetailsUseCase);
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
                errorView.setVisibility(View.GONE);
                successLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setErrorMessageObserver() {
        viewModel.getErrorMessage().observe(this, errorMessage ->
        {
            if (errorMessage.equals("")) {
                errorView.setVisibility(View.GONE);
            } else {
                errorFragment.showError(errorMessage);
                setTitle("");
                successLayout.setVisibility(View.GONE);
                errorView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setMovieDetailsDataObserver() {
        viewModel.getMovieDetailsLiveData().observe(this, this::configureView);
    }

    private void configureView(MovieDetailsModel movieDetails) {
        progressBar.setVisibility(View.GONE);
        successLayout.setVisibility(View.VISIBLE);
        setTitle(movieDetails.getTitle());

        originalTitle.setText(movieDetails.getOriginalTitle());
        String language = "(" + movieDetails.getOriginalLanguage().toUpperCase() + ")";
        originalLanguage.setText(language);


        releaseDate.setText(movieDetails.getReleaseDate());
        duration.setText(getString(R.string.movie_duration_format, movieDetails.getRuntime()));

        Glide.with(this)
                .load(movieDetails.getPosterUrl())
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(poster);
        genreList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HorizontalListAdapter adapter = new HorizontalListAdapter(movieDetails.getGenres());
        genreList.setAdapter(adapter);
        overview.setText(movieDetails.getOverview());

        rating.setText(getString(R.string.movie_vote_average_format, movieDetails.getVoteAverage()));
        status.setText(movieDetails.getStatus());

        ArrayList<String> productionCompaniesNames = movieDetails.getProductionCompanies()
                .stream().map(ProductionCompanyModel::getName)
                .collect(Collectors.toCollection(ArrayList::new));
        productionCompanies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HorizontalListAdapter productionCompaniesAdapter = new HorizontalListAdapter(productionCompaniesNames);
        productionCompanies.setAdapter(productionCompaniesAdapter);
        revenue.setText(getString(R.string.movie_budget_format, StringUtils.formatInt(movieDetails.getBudget())));
        availableIn.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HorizontalListBorderlessAdapter availableInAdapter = new HorizontalListBorderlessAdapter(movieDetails.getSpokenLanguages());
        availableIn.setAdapter(availableInAdapter);
    }

    private MovieDataService getMovieDataService() {
        Retrofit retrofit = Api.setupRetrofit();
        return retrofit.create(MovieDataService.class);
    }
}