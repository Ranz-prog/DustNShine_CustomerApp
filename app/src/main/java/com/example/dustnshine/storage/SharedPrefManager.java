package com.example.dustnshine.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dustnshine.models.AddressModel;
import com.example.dustnshine.models.User;

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

    public void saveUser(User user){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("first_name", user.getFirst_name());
        editor.putString("last_name", user.getLast_name());
        editor.putString("mobile_number", user.getMobile_number());
        editor.putString("email", user.getEmail());

        editor.apply();
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("mobile_number", null),
                sharedPreferences.getString("email", null)
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

    public void saveUserAddress(AddressModel addressModel){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", addressModel.getId());
        editor.putInt("user_id", addressModel.getUser_id());
        editor.putInt("house_number", addressModel.getHouse_number());
        editor.putLong("latitude", Double.doubleToRawLongBits(addressModel.getLatitude()));
        editor.putLong("longitude", Double.doubleToRawLongBits(addressModel.getLongitude()));
        editor.putString("street", addressModel.getStreet());
        editor.putString("barangay", addressModel.getBarangay());
        editor.putString("municipality", addressModel.getMunicipality());
        editor.putString("province", addressModel.getProvince());
        editor.putString("zipcode", addressModel.getZipcode());

        editor.apply();
    }

    public AddressModel getUserAddress(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return new AddressModel(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getInt("user_id", -1),
                sharedPreferences.getInt("house_number", -1),
                sharedPreferences.getLong("latitude", -1),
                sharedPreferences.getLong("longitude", -1),
                sharedPreferences.getString("street", null),
                sharedPreferences.getString("barangay", null),
                sharedPreferences.getString("municipality", null),
                sharedPreferences.getString("province", null),
                sharedPreferences.getString("zipcode", null)
        );
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
