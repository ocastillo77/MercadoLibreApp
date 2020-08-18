package com.ideacreativa.mercadolibreapp.services;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class ProductResponse extends RetrofitConnect {
    private MLProduct routes;

    public ProductResponse(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        routes = retrofit.create(MLProduct.class);
    }

    public void productSearch(Callback<ResponseBody> callBack, String query, int limit, int offset) {
        Call<ResponseBody> call = routes.productList(query, limit, offset);
        call.enqueue(callBack);
    }

    public void productDetail(Callback<ResponseBody> callBack, String itemId) {
        Call<ResponseBody> call = routes.productDetail(itemId);
        call.enqueue(callBack);
    }
}
