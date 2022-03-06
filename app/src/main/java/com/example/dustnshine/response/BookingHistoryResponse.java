package com.example.dustnshine.response;

import com.example.dustnshine.models.BookingHistoryModel;

import java.util.List;

public class BookingHistoryResponse {
    private String message;
    private List<BookingHistoryModel> data;

    public BookingHistoryResponse(String message, List<BookingHistoryModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingHistoryModel> getData() {
        return data;
    }

    public void setData(List<BookingHistoryModel> data) {
        this.data = data;
    }
}
