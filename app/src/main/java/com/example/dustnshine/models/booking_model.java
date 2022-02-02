package com.example.dustnshine.models;

public class booking_model {

    private int customerImg;
    private  String customerName, customerLocation,customerContact;

    public booking_model(int customerImg, String customerName, String customerLocation, String customerContact) {
        this.customerImg = customerImg;
        this.customerName = customerName;
        this.customerLocation = customerLocation;
        this.customerContact = customerContact;
    }

    public int getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(int customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }
}
