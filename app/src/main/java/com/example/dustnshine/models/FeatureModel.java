package com.example.dustnshine.models;

public class FeatureModel {

    private int serviceImg;
    private  String serviceName,serviceDetails;

    public FeatureModel(int serviceImg, String serviceName, String serviceDetails) {
        this.serviceImg = serviceImg;
        this.serviceName = serviceName;
        this.serviceDetails = serviceDetails;
    }

    public int getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(int serviceImg) {
        this.serviceImg = serviceImg;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }
}