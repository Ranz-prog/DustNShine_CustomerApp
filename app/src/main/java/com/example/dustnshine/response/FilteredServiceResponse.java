package com.example.dustnshine.response;

import com.example.dustnshine.models.PivotModel;
import com.example.dustnshine.models.RecommendationModel;

import java.util.List;

public class FilteredServiceResponse {
    private String message;
    private List<RecommendationModel> data;
    private PivotModel pivot;

    public FilteredServiceResponse(String message, List<RecommendationModel> data, PivotModel pivot) {
        this.message = message;
        this.data = data;
        this.pivot = pivot;
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

    public PivotModel getPivot() {
        return pivot;
    }

    public void setPivot(PivotModel pivot) {
        this.pivot = pivot;
    }
}
