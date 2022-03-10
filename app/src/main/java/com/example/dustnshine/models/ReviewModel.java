package com.example.dustnshine.models;

public class ReviewModel {
    private int id, customer_id, booking_id;
    private String comment, created_at, updated_at;
    private double rating;

    public ReviewModel(int id, int customer_id, int booking_id, String comment, String created_at, String updated_at, double rating) {
        this.id = id;
        this.customer_id = customer_id;
        this.booking_id = booking_id;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
