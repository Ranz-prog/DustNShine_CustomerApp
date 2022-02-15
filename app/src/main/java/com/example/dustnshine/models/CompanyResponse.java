package com.example.dustnshine.models;

import java.util.List;

public class CompanyResponse {

    private List<RecommendationModel> recommendationModelList;
    private String message;

    public List<RecommendationModel> getRecommendationModelList() {
        return recommendationModelList;
    }

    public void setRecommendationModelList(List<RecommendationModel> recommendationModelList) {
        this.recommendationModelList = recommendationModelList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
