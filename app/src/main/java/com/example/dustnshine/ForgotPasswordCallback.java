package com.example.dustnshine;

import com.example.dustnshine.response.ForgotPasswordResponse;

public interface ForgotPasswordCallback {
    void forgotPasswordCallback(Integer statusCode, ForgotPasswordResponse forgotPasswordResponse);
}
