package com.example.dustnshine.response;

import com.example.dustnshine.models.CompanyAndServicesModel;


import java.util.List;

public class CompanyAndServiceResponse {

    private CompanyAndServicesModel data;
    private String message;

    public CompanyAndServiceResponse(CompanyAndServicesModel data, String message) {
        this.data = data;
        this.message = message;
    }

    public CompanyAndServicesModel getData() {
        return data;
    }

    public void setData(CompanyAndServicesModel data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
