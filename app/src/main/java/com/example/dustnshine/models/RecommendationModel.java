package com.example.dustnshine.models;


public class RecommendationModel {

    private int id;
    private String name, email, mobile_number, tel_number, address, created_at, updated_at, company_image;

    public RecommendationModel(int id, String name, String email, String mobile_number, String tel_number, String address, String created_at, String updated_at, String company_image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.tel_number = tel_number;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.company_image = company_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCompany_image() {
        return company_image;
    }

    public void setCompany_image(String company_image) {
        this.company_image = company_image;
    }
}
