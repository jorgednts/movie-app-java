package com.example.movies_app_java.extensions;

import com.example.movies_app_java.constants.NullResponseConstants;

public class StringExtensions {
    public static String convertIfIsNullOrBlank(String value) {
        return value != null && !value.isEmpty() ? value : NullResponseConstants.nullStringResponse;
    }
}
