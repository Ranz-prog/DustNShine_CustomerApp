package com.example.dustnshine.models;

import java.util.List;

public class CompanyResponse {

    private String message;

    private List<RecommendationModel> data;

    public CompanyResponse(String message, List<RecommendationModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RecommendationModel> getData() {
        return data;
    }

    public void setData(List<RecommendationModel> data) {
        this.data = data;
    }
}
