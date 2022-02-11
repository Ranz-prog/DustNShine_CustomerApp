package com.example.dustnshine.models;

public class Data {

    private String token;
    private User user;

    public Data(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
