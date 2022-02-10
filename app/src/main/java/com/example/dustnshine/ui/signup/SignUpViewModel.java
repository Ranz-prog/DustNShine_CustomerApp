package com.example.dustnshine.ui.signup;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.SignUpModel;

public class SignUpViewModel extends ViewModel {
    public MutableLiveData<String> firstName = new MutableLiveData<>();
    public MutableLiveData<String> lastName = new MutableLiveData<>();
    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> password_confirmation = new MutableLiveData<>();

    private SignUpModel signUpModel;
    private Context context;
}
