package com.example.dustnshine.Models;

public class feature_model {

    private int serviceImg;
    private  String serviceName,serviceDetails;

    public feature_model(int serviceImg, String serviceName, String serviceDetails) {
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
