package com.example.dustnshine;

import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.models.SignUpResponse;

public interface SignUpCallback {
    void signUpCallback(Integer statusCode, SignUpResponse signUpResponse);
}
