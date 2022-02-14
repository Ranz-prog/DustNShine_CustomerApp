package com.example.dustnshine.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dustnshine.models.SignInModel;
import com.example.dustnshine.ui.signin.SignInViewModel;

public class SignInViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SignInModel signInModel;
    private Context context;

    public SignInViewModelFactory(SignInModel signInModel, Context context) {
        this.signInModel = signInModel;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignInViewModel(signInModel, context);
    }
}
