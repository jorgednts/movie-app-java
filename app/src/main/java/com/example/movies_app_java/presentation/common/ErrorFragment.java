package com.example.movies_app_java.presentation.common;

import static com.example.movies_app_java.R.color.primaryColor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.movies_app_java.R;

public class ErrorFragment extends Fragment {
    private TextView errorTextView;
    private final boolean isMainActivity;

    public ErrorFragment(boolean isMainActivity) {
        this.isMainActivity = isMainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_error, container, false);
        errorTextView = view.findViewById(R.id.errorMessage);
        if(isMainActivity) {
            int primary = getResources().getColor(primaryColor);
            errorTextView.setTextColor(primary);
        }

        return view;
    }

    public void showError(String errorMessage) {
        if (getView() != null) {
            errorTextView = getView().findViewById(R.id.errorMessage);
            errorTextView.setText(errorMessage);
        }
    }
}