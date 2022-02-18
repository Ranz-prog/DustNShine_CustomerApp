package com.example.dustnshine.api;

import android.content.Context;

import com.example.dustnshine.storage.SharedPrefManager;
import com.example.dustnshine.ui.signin.ActivitySignIn;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://lpn.boomtech.co/api/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private Context context;

    OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
            .addInterceptor(
                    new Interceptor() {
                        @Override
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .build();
                            return chain.proceed(request);
                        }
                    }).build();

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(defaultHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if(mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
