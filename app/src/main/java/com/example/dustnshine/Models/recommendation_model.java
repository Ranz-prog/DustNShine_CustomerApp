package com.example.dustnshine.Models;

public class recommendation_model {

    private int companyImg;
    private  String companyName;

    public recommendation_model(int companyImg, String companyName) {
        this.companyImg = companyImg;
        this.companyName = companyName;
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
}
