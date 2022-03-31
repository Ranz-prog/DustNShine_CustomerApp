package com.example.dustnshine;

import com.example.dustnshine.response.ResetPasswordResponse;

public interface ResetPasswordCallback {
    void resetPasswordCallback(Integer statusCode, ResetPasswordResponse resetPasswordResponse);
}
