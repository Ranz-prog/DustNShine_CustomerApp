package com.example.dustnshine.models;

public class User {
    private int id;
    private String first_name, last_name, mobile_number, email;

    public User(int id, String first_name, String last_name, String mobile_number, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_number = mobile_number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getEmail() {
        return email;
    }
}
