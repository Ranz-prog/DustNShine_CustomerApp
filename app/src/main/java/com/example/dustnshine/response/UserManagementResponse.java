package com.example.dustnshine.response;

import com.example.dustnshine.models.UserManagementModel;

import java.util.List;

public class UserManagementResponse {

    private List<UserManagementModel> data;
    private String message;

    public UserManagementResponse(List<UserManagementModel> data, String message) {
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
