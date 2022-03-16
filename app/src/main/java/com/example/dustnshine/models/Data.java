package com.example.dustnshine.models;

public class Data {

    private String token;
    private UserModel userModel;

    public Data(String token, UserModel userModel) {
        this.token = token;
        this.userModel = userModel;
    }

    public String getToken() {
        return token;
    }

    public UserModel getUser() {
        return userModel;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
