package com.example.dustnshine.models;

import java.util.List;

public class BookingHistoryModel {
    private int id, user_id, company_id, status, total;
    private String sched_datetime, start_datetime, end_datetime, address, created_at, updated_at, note, reviews;
    private double latitude, longitude;
    private List<ServiceDetailsModel> services;

    public BookingHistoryModel(int id, int user_id, int company_id, int status, int total, String sched_datetime, String start_datetime, String end_datetime, String address, String created_at, String updated_at, String note, String reviews, double latitude, double longitude, List<ServiceDetailsModel> services) {
        this.id = id;
        this.user_id = user_id;
        this.company_id = company_id;
        this.status = status;
        this.total = total;
        this.sched_datetime = sched_datetime;
        this.start_datetime = start_datetime;
        this.end_datetime = end_datetime;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.note = note;
        this.reviews = reviews;
        this.latitude = latitude;
        this.longitude = longitude;
        this.services = services;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSched_datetime() {
        return sched_datetime;
    }

    public void setSched_datetime(String sched_datetime) {
        this.sched_datetime = sched_datetime;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<ServiceDetailsModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceDetailsModel> services) {
        this.services = services;
    }
}
