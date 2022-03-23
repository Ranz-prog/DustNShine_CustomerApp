package com.example.dustnshine.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dustnshine.models.AddressModel;
import com.example.dustnshine.models.UserModel;

public class SharedPrefManager {

    private static final String SHARED_PREFERENCES_NAME = "user_shared_preferences";
    private static SharedPrefManager mInstance;
    private Context mContext;

    public SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPrefManager getInstance(Context mContext){
        if(mInstance == null){
            mInstance = new SharedPrefManager(mContext);
        }
        return  mInstance;
    }

    public void saveUser(UserModel userModel){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", userModel.getId());
        editor.putString("first_name", userModel.getFirst_name());
        editor.putString("last_name", userModel.getLast_name());
        editor.putString("mobile_number", userModel.getMobile_number());
        editor.putString("email", userModel.getEmail());
        editor.putString("email_verified_at", userModel.getEmail_verified_at());
        editor.putString("created_at", userModel.getCreated_at());
        editor.putString("updated_at", userModel.getUpdated_at());
        editor.apply();
    }

    public UserModel getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("mobile_number", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("email_verified_at", null),
                sharedPreferences.getString("created_at", null),
                sharedPreferences.getString("updated_at", null)
        );
    }

    public void savePassword(String password){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("password", password);

        editor.apply();
    }

    public String getPassword(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString("password", null);

    }

    public void saveUserToken(String token){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", token);
        editor.apply();
    }

    public String getUserToken(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", null);
    }


    public Boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
