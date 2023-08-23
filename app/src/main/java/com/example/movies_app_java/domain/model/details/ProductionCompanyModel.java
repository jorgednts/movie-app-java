package com.example.movies_app_java.domain.model.details;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionCompanyModel that = (ProductionCompanyModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(logoUrl, that.logoUrl) &&
                Objects.equals(name, that.name) &&
                Objects.equals(originCountry, that.originCountry);
    }
}
