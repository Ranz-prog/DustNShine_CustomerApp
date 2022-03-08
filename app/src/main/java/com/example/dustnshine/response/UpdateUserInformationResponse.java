package com.example.dustnshine.response;

import com.example.dustnshine.models.UserManagementModel;

import java.util.List;

public class UpdateUserInformationResponse {
    private List<UserManagementModel> data;
    private String message;

    public UpdateUserInformationResponse(List<UserManagementModel> data, String message) {
        this.data = data;
        this.message = message;
    }

    public List<UserManagementModel> getData() {
        return data;
    }

    public void setData(List<UserManagementModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
