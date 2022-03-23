package com.example.dustnshine.models;

public class UserPivotModel {
    private int role_id, model_id;
    private String model_type;

    public UserPivotModel(int role_id, int model_id, String model_type) {
        this.role_id = role_id;
        this.model_id = model_id;
        this.model_type = model_type;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }
}
