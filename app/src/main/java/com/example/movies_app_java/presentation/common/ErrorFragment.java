package com.example.movies_app_java.presentation.common;

import static com.example.movies_app_java.R.color.lightBlue;
import static com.example.movies_app_java.R.color.primaryColor;
import static com.example.movies_app_java.R.color.secondaryColor;
import static com.example.movies_app_java.R.color.textWhite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.movies_app_java.R;

public class ErrorFragment extends Fragment {
    private TextView errorTextView;
    private Button retryButton;
    private final boolean isMainActivity;
    private OnRetryButtonClickListener onRetryButtonClickListener;

    public ErrorFragment(boolean isMainActivity) {
        this.isMainActivity = isMainActivity;
    }

    public void setOnRetryButtonClickListener(OnRetryButtonClickListener listener) {
        this.onRetryButtonClickListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_error, container, false);
        retryButton = view.findViewById(R.id.retryButton);
        errorTextView = view.findViewById(R.id.errorMessage);

        retryButton.setOnClickListener(v -> onRetryButtonClickListener.onButtonClick());
        int textColor = getResources().getColor(isMainActivity ? primaryColor : textWhite);
        errorTextView.setTextColor(textColor);

        return view;
    }

    public void showError(String errorMessage) {
        if (getView() != null) {
            errorTextView = getView().findViewById(R.id.errorMessage);
            errorTextView.setText(errorMessage);
        }
    }
}