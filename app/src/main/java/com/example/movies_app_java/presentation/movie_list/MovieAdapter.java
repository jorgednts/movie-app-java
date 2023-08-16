package com.example.movies_app_java.presentation.movie_list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_app_java.R;
import com.example.movies_app_java.domain.model.movie.MovieModel;
import com.example.movies_app_java.presentation.common.HorizontalListAdapter;

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
        private final ImageView posterImageView;
        private final Button button;
        private final RecyclerView genreList;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            releaseDateTextView = itemView.findViewById(R.id.releaseDate);
            ratingTextView = itemView.findViewById(R.id.rating);
            posterImageView = itemView.findViewById(R.id.poster);
            button = itemView.findViewById(R.id.button);
            genreList = itemView.findViewById(R.id.genreList);
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

            genreList.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            HorizontalListAdapter adapter = new HorizontalListAdapter(movie.getGenres());
            genreList.setAdapter(adapter);
        }
    }
}
