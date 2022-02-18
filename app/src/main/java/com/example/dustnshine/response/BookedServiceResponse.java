package com.example.dustnshine.response;

import com.example.dustnshine.models.BookingServiceData;

import java.util.List;

public class BookedServiceResponse {
    private String message;
    private List<BookingServiceData> data;

    public BookedServiceResponse(String message, List<BookingServiceData> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingServiceData> getData() {
        return data;
    }

    public void setData(List<BookingServiceData> data) {
        this.data = data;
    }
}
