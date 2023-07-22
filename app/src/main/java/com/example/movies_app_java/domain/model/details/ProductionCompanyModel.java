package com.example.movies_app_java.domain.model.details;

public class ProductionCompanyModel {
    private final Integer id;
    private final String logoUrl;
    private final String name;
    private final String originCountry;

    public ProductionCompanyModel(Integer id, String logoUrl, String name, String originCountry) {
        this.id = id;
        this.logoUrl = logoUrl;
        this.name = name;
        this.originCountry = originCountry;
    }
}
