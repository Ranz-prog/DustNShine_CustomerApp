package com.example.dustnshine.response;

import com.example.dustnshine.models.BookingServiceData;

import java.util.List;

public class BookingServiceResponse {
    private String message;
    private BookingServiceData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookingServiceData getData() {
        return data;
    }

    public void setData(BookingServiceData data) {
        this.data = data;
    }
}
