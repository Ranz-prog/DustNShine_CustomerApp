package com.example.dustnshine.models;

public class RecommendationModel {

    private int companyImg;
    private  String companyName,companyLocation,companyRating;

    public RecommendationModel(int companyImg, String companyName, String companyLocation, String companyRating) {
        this.companyImg = companyImg;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.companyRating = companyRating;
    }

    public int getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(int companyImg) {
        this.companyImg = companyImg;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(String companyRating) {
        this.companyRating = companyRating;
    }
}
