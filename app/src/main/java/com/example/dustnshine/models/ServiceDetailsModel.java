package com.example.dustnshine.models;

public class ServiceDetailsModel {
    private int id;
    private String name, description, created_at, updated_at;
    private PivotModel pivot;

    public ServiceDetailsModel(int id, String name, String description, String created_at, String updated_at, PivotModel pivot) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.pivot = pivot;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public PivotModel getPivot() {
        return pivot;
    }

    public void setPivot(PivotModel pivot) {
        this.pivot = pivot;
    }
}