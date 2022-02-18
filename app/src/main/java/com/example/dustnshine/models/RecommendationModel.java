package com.example.dustnshine.models;

<<<<<<< HEAD
import com.google.gson.annotations.SerializedName;

public class RecommendationModel {

    private int id;
    private String name, email, mobile_number, tel_number, address, created_at, updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
=======
import com.example.dustnshine.R;

public class RecommendationModel {

    private int companyImg;
    private  String name,address;

    public RecommendationModel(String name, String address) {
        this.name = name;
        this.address = address;
>>>>>>> branch_jericho
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
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

=======
>>>>>>> branch_jericho
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

<<<<<<< HEAD
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
=======
    @Override
    public String toString() {
        return "RecommendationModel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
>>>>>>> branch_jericho
    }
}
