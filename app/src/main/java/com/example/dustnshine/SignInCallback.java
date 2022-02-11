package com.example.dustnshine;

import com.example.dustnshine.models.LoginResponse;

public interface SignInCallback {
    void signInCallback(Integer statusCode, LoginResponse loginResponse);
}
