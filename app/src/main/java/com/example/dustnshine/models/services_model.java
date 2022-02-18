package com.example.dustnshine.models;

public class services_model {

    private  String serviceTitle,servicePrice,serviceDes1, serviceDes2;

    public services_model(String serviceTitle, String servicePrice, String serviceDes1, String serviceDes2) {
        this.serviceTitle = serviceTitle;
        this.servicePrice = servicePrice;
        this.serviceDes1 = serviceDes1;
        this.serviceDes2 = serviceDes2;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDes1() {
        return serviceDes1;
    }

    public void setServiceDes1(String serviceDes1) {
        this.serviceDes1 = serviceDes1;
    }

    public String getServiceDes2() {
        return serviceDes2;
    }

    public void setServiceDes2(String serviceDes2) {
        this.serviceDes2 = serviceDes2;
    }
}
