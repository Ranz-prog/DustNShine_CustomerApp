package com.example.dustnshine.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResponse {

    private String message;

    private List<ServicesModel> data;

    public ServiceResponse(String message, List<ServicesModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ServicesModel> getData() {
        return data;
    }

    public void setData(List<ServicesModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
