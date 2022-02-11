package com.example.dustnshine.models;

import android.text.TextUtils;
import android.util.Patterns;

public class SignInModel {
    private String email, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
