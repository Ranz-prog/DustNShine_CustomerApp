package com.example.dustnshine.response;

import com.example.dustnshine.models.BookingHistoryModel;
import com.example.dustnshine.models.NotificationModel;

import java.util.List;

public class NotificationResponse {
    private String message;
    private List<NotificationModel> data;

    public NotificationResponse(String message, List<NotificationModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NotificationModel> getData() {
        return data;
    }

    public void setData(List<NotificationModel> data) {
        this.data = data;
    }
}
