package com.example.movies_app_java.data.remote.model.spoken_languages;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguagesResponse {
    @SerializedName("name")
    private final String name;

    public SpokenLanguagesResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
