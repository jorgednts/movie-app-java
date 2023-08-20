package com.example.movies_app_java.di;

import com.example.movies_app_java.presentation.movie_details.MovieDetailsActivity;
import com.example.movies_app_java.presentation.movie_list.MovieListActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void injectInMovieListActivity(MovieListActivity movieListActivity);

    void injectInMovieDetailsActivity(MovieDetailsActivity movieDetailsActivity);
}
