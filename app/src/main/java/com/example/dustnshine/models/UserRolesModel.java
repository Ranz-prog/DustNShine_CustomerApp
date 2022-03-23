package com.example.dustnshine.models;

public class UserRolesModel {
    private int id;
    private String name, guard_name, created_at, updated_at;
    private UserPivotModel pivot;

    public UserRolesModel(int id, String name, String guard_name, String created_at, String updated_at, UserPivotModel pivot) {
        this.id = id;
        this.name = name;
        this.guard_name = guard_name;
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

    public String getGuard_name() {
        return guard_name;
    }

    public void setGuard_name(String guard_name) {
        this.guard_name = guard_name;
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

    public UserPivotModel getPivot() {
        return pivot;
    }

    public void setPivot(UserPivotModel pivot) {
        this.pivot = pivot;
    }
}
