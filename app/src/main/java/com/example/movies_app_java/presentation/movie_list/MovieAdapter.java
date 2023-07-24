package com.example.movies_app_java.presentation.movie_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_app_java.R;
import com.example.movies_app_java.domain.model.movie.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<MovieModel> movieList;
    private OnButtonClickListener onButtonClickListener;

    public MovieAdapter(List<MovieModel> movieList) {
        this.movieList = movieList;
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.onButtonClickListener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<MovieModel> newMovieList) {
        movieList.clear();
        movieList.addAll(newMovieList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieModel movie = movieList.get(position);
        holder.bind(movie);
        holder.button.setOnClickListener(v -> {
            int movieId = movie.getId();
            if (onButtonClickListener != null) {
                onButtonClickListener.onButtonClick(movieId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView releaseDateTextView;
        private final TextView ratingTextView;
        private final LinearLayout genreLinearLayout;
        private final ImageView posterImageView;
        private final Context context;

        private final Button button;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            releaseDateTextView = itemView.findViewById(R.id.releaseDate);
            ratingTextView = itemView.findViewById(R.id.rating);
            genreLinearLayout = itemView.findViewById(R.id.genreLinearLayout);
            posterImageView = itemView.findViewById(R.id.poster);
            button = itemView.findViewById(R.id.button);
            context = itemView.getContext();
        }

        public void bind(MovieModel movie) {
            titleTextView.setText(movie.getTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
            ratingTextView.setText(String.format(String.valueOf(movie.getVoteAverage())));

            Glide.with(itemView.getContext())
                    .load(movie.getImageUrl())
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(posterImageView);

            genreLinearLayout.removeAllViews();

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int marginEnd = context.getResources().getDimensionPixelSize(R.dimen.genre_margin_end);

            for (String genre : movie.getGenres()) {
                TextView genreTextView = new TextView(itemView.getContext());
                genreTextView.setText(genre);
                genreTextView.setTextColor(Color.WHITE);
                genreTextView.setTextSize(12);
                genreTextView.setBackgroundResource(R.drawable.chip_background_border);
                genreTextView.setTypeface(null, Typeface.BOLD);
                layoutParams.setMarginEnd(marginEnd);
                genreTextView.setLayoutParams(layoutParams);
                genreLinearLayout.addView(genreTextView);
            }
        }
    }
}
