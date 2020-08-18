package com.ideacreativa.mercadolibreapp.services;

import androidx.appcompat.app.AppCompatActivity;

import com.ideacreativa.mercadolibreapp.helpers.VerifyNetwork;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitConnect {
    public Retrofit retrofit;
    private AppCompatActivity mActivity;

    public RetrofitConnect(AppCompatActivity activity) {
        mActivity = activity;
        Interceptor interceptor = new Interceptor();
        retrofit = new Retrofit.Builder().client(new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build()).addConverterFactory(ScalarsConverterFactory
                .create().create()).baseUrl("https://api.mercadolibre.com/").build();
    }

    protected class Interceptor implements okhttp3.Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!VerifyNetwork.isNetworkAvailable(mActivity)) {
                throw new IOException();
            }
            Request request = chain.request();
            return chain.proceed(request);
        }
    }
}

