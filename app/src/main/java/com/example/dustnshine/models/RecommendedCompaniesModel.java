package com.example.dustnshine.models;

import com.example.dustnshine.models.CompanyModel;

public class RecommendedCompaniesModel {
    private int id, company_id, is_active, parent_id, lft, rgt, depth;
    private double rating;
    private String created_at, updated_at;
    private CompanyModel company;

    public RecommendedCompaniesModel(int id, int company_id, int is_active, int parent_id, int lft, int rgt, int depth, double rating, String created_at, String updated_at, CompanyModel company) {
        this.id = id;
        this.company_id = company_id;
        this.is_active = is_active;
        this.parent_id = parent_id;
        this.lft = lft;
        this.rgt = rgt;
        this.depth = depth;
        this.rating = rating;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }
}
