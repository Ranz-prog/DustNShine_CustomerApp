package com.example.dustnshine.models;

import com.example.dustnshine.R;

public class RecommendationModel {

    private int companyImg;
    private  String name,address;

    public RecommendationModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RecommendationModel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
