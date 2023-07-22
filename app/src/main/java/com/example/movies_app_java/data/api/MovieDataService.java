package com.example.movies_app_java.data.api;

import com.example.movies_app_java.data.remote.model.details.MovieDetailsResponse;
import com.example.movies_app_java.data.remote.model.movie.MovieResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDataService {
    @GET("movies")
    Observable<ArrayList<MovieResponse>> getMovieList();

    @GET("movies/{id}")
    Observable<MovieDetailsResponse> getMovieDetails(
            @Path("id") int id
    );
}
