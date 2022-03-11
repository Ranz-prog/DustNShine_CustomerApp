package com.example.dustnshine.response;

import com.example.dustnshine.models.RecommendedCompaniesModel;

import java.util.List;

public class RecommendedCompaniesResponse {
    private String message;
    private List<RecommendedCompaniesModel> data;

    public RecommendedCompaniesResponse(String message, List<RecommendedCompaniesModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RecommendedCompaniesModel> getData() {
        return data;
    }

    public void setData(List<RecommendedCompaniesModel> data) {
        this.data = data;
    }
}
