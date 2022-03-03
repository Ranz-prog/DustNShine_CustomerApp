package com.example.dustnshine.models;

public class Address {
    private int house_number;
    private String street, barangay, municipality, province, zipcode;

    public Address(int house_number, String street, String barangay, String municipality, String province, String zipcode) {
        this.house_number = house_number;
        this.street = street;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.zipcode = zipcode;
    }

    public int getHouse_number() {
        return house_number;
    }

    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
