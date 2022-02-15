package com.example.dustnshine.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResponse {

    private String message;

    private List<ServicesModel> services;

    public ServiceResponse(String message, List<ServicesModel> services) {
        this.message = message;
        this.services = services;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ServicesModel> getServices() {
        return services;
    }

    public void setServices(List<ServicesModel> services) {
        this.services = services;
    }
}
