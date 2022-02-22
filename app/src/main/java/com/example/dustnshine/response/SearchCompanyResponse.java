package com.example.dustnshine.response;

import com.example.dustnshine.models.RecommendationModel;
import com.example.dustnshine.models.SearchCompanyModel;

import java.util.List;

public class SearchCompanyResponse {
    private String message;
    private List<RecommendationModel> data;

    public SearchCompanyResponse(String message, List<RecommendationModel> data) {
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
