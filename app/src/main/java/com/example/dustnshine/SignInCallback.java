package com.example.dustnshine;

import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.models.SignUpResponse;

public interface SignInCallback {
    void signInCallback(Integer statusCode, LoginResponse loginResponse);
}
