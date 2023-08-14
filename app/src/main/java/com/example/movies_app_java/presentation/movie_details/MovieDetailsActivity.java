package com.example.movies_app_java.presentation.movie_details;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movies_app_java.R;
import com.example.movies_app_java.data.api.Api;
import com.example.movies_app_java.data.api.MovieDataService;
import com.example.movies_app_java.data.remote.data_source.MovieRemoteDataSourceImpl;
import com.example.movies_app_java.data.remote.data_source.MoviesRemoteDataSource;
import com.example.movies_app_java.data.repository.MovieRepositoryImpl;
import com.example.movies_app_java.domain.model.details.MovieDetailsModel;
import com.example.movies_app_java.domain.repository.MovieRepository;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCase;
import com.example.movies_app_java.domain.use_case.GetMovieDetailsUseCaseImpl;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);
        progressBar = findViewById(R.id.progressBar);
        originalLanguage = findViewById(R.id.originalLanguage);
        originalTitle = findViewById(R.id.originalTitle);
        originalTitleTag = findViewById(R.id.originalTitleTag);
        releaseDate = findViewById(R.id.releaseDate);
        duration = findViewById(R.id.duration);


        int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);
        moviesRemoteDataSource = new MovieRemoteDataSourceImpl(getMovieDataService());
        movieRepository = new MovieRepositoryImpl(moviesRemoteDataSource);
        getMovieDetailsUseCase = new GetMovieDetailsUseCaseImpl(movieRepository);
        viewModel = new MovieDetailsViewModel(getMovieDetailsUseCase);

        setLoadingObserver();
        setMovieDetailsDataObserver();

        viewModel.getMovieDetails(movieId);
    }

    private void setLoadingObserver() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                setTitle("Loading...");
            }
        });
    }

    private void setMovieDetailsDataObserver() {
        viewModel.getMovieDetailsLiveData().observe(this, movieDetailsObservable -> movieDetailsObservable
                .subscribe(
                        movieDetails -> {
                            setTitle(movieDetails.getTitle());
                            configureView(movieDetails);
                        },
                        throwable -> Toast.makeText(this, "Error fetching movie list.", Toast.LENGTH_SHORT).show()
                ));
    }

    private void configureView(MovieDetailsModel movieDetails) {
        progressBar.setVisibility(View.GONE);
        originalTitle.setText(movieDetails.getOriginalTitle());
        originalTitle.setVisibility(View.VISIBLE);
        String language = "(" + movieDetails.getOriginalLanguage().toUpperCase() + ")";
        originalLanguage.setText(language);
        originalLanguage.setVisibility(View.VISIBLE);
        originalTitleTag.setVisibility(View.VISIBLE);

        releaseDate.setText(movieDetails.getReleaseDate());
        releaseDate.setVisibility(View.VISIBLE);
        duration.setText(String.valueOf(movieDetails.getRuntime()));
        duration.setVisibility(View.VISIBLE);
    }

    private MovieDataService getMovieDataService() {
        Retrofit retrofit = Api.setupRetrofit();
        return retrofit.create(MovieDataService.class);
    }
}