package com.example.dustnshine.models;

public class SignUpResponse {
    private boolean error;
    private String message;

    public SignUpResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
