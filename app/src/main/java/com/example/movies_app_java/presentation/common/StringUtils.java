package com.example.movies_app_java.presentation.common;

import java.text.NumberFormat;
import java.util.Locale;

public class StringUtils {
    public static String formatInt(int number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        return numberFormat.format(number);
    }
}
