package com.example.dustnshine.response;

import com.example.dustnshine.models.Errors;

public class SignUpResponse {
    private String message;
//    private Errors errors;

    public SignUpResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
