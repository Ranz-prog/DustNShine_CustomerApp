package com.example.dustnshine.models;

public class PivotModel {
    private int booking_id, service_id, company_id;

    public PivotModel(int booking_id, int service_id, int company_id) {
        this.booking_id = booking_id;
        this.service_id = service_id;
        this.company_id = company_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
