package com.example.movies_app_java.presentation.movie_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.movies_app_java.R;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);
    }
}