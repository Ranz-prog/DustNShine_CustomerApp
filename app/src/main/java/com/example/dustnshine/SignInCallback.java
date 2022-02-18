package com.example.dustnshine;

import com.example.dustnshine.response.SignInResponse;

public interface SignInCallback {
    void signInCallback(Integer statusCode, SignInResponse signInResponse);
}
