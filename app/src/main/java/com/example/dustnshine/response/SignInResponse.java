package com.example.dustnshine.response;

import com.example.dustnshine.models.Data;

public class SignInResponse {
    private String message;
    private Data data;

    public SignInResponse(String message, Data data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Data data) {
        this.data = data;
    }
}