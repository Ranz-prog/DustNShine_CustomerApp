package com.example.dustnshine.response;

import com.example.dustnshine.models.RecommendationModel;

import java.util.List;

public class CompanyResponse {

    private List<RecommendationModel> data;
    private String message;

    public CompanyResponse(List<RecommendationModel> data, String message) {
        this.data = data;
        this.message = message;
    }

    public List<RecommendationModel> getData() {
        return data;
    }

    public void setData(List<RecommendationModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
