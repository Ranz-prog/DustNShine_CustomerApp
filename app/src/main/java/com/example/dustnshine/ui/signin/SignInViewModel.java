package com.example.dustnshine.ui.signin;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dustnshine.models.LoginResponse;
import com.example.dustnshine.models.SignInModel;
import com.example.dustnshine.repository.UserAPIRepo;

public class SignInViewModel extends ViewModel {

    private UserAPIRepo userAPIRepo = new UserAPIRepo();
    private MutableLiveData<LoginResponse> mutableLiveData;
    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private SignInModel signInModel;
    private Context context;

    public SignInViewModel(SignInModel signInModel, Context context) {
        this.signInModel = signInModel;
        this.context = context;
    }

    public void onLoginClick(View view){
        signInModel.setEmail(emailAddress.getValue());
        signInModel.setPassword(password.getValue());

        if (!signInModel.isEmailValid()){
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show();
        } else if (!signInModel.isPasswordValid()){
            Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show();
        } else {
            mutableLiveData.postValue(userAPIRepo.userSignIn(emailAddress.getValue(), password.getValue()).getValue());
        }
    }

    public LiveData<LoginResponse> getResponse() {

        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }
}
