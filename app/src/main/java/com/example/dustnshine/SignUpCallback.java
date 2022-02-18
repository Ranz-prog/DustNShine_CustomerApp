package com.example.dustnshine;

import com.example.dustnshine.response.SignUpResponse;

public interface SignUpCallback {
    void signUpCallback(Integer statusCode, SignUpResponse signUpResponse);
}
