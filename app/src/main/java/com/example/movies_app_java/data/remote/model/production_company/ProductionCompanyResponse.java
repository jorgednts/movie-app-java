package com.example.movies_app_java.data.remote.model.production_company;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanyResponse {
    @SerializedName("id")
    private final Integer id;
    @SerializedName("logo_url")
    private final String logoUrl;
    @SerializedName("name")
    private final String name;
    @SerializedName("origin_country")
    private final String originCountry;

    public Integer getId() {
        return id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public ProductionCompanyResponse(Integer id, String logoUrl, String name, String originCountry) {
        this.id = id;
        this.logoUrl = logoUrl;
        this.name = name;
        this.originCountry = originCountry;
    }
}
