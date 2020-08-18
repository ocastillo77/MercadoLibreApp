package com.ideacreativa.mercadolibreapp.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ideacreativa.mercadolibreapp.R;
import com.ideacreativa.mercadolibreapp.adapters.PageAdapter;
import com.ideacreativa.mercadolibreapp.adapters.ListProductsAdapter;
import com.ideacreativa.mercadolibreapp.models.ProductoDetalle;
import com.ideacreativa.mercadolibreapp.services.ProductResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {

    public ProductoDetalle mProductoDetalle;
    public ViewPager mViewPager;
    private TextView mTitle, mPrice, mSoldQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String itemId = intent.getStringExtra(ListProductsAdapter.ITEM_ID);

        mViewPager = findViewById(R.id.viewpager);
        mTitle = findViewById(R.id.title);
        mPrice = findViewById(R.id.price);
        mSoldQuantity = findViewById(R.id.sold_quantity);

        ProductResponse mProductResponse = new ProductResponse(DetailProductActivity.this);
        mProductResponse.productDetail(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    JSONObject jsonObject = new JSONObject(body);
                    mProductoDetalle = new Gson().fromJson(jsonObject.toString(), ProductoDetalle.class);
                    mViewPager.setAdapter(new PageAdapter(DetailProductActivity.this, mProductoDetalle.pictures));

                    mTitle.setText(mProductoDetalle.title);
                    mPrice.setText("$ " + mProductoDetalle.price);

                    if (!mProductoDetalle.sold_quantity.equals("0")) {
                        mSoldQuantity.setText(mProductoDetalle.sold_quantity + " vendidos");
                    }

                } catch (IOException | JSONException e) {
                    Log.d("DetailProductActivity", e.getMessage());
                    Toast.makeText(DetailProductActivity.this, "No se encontró el producto!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("DetailProductActivity", t.getMessage());
                Toast.makeText(DetailProductActivity.this, "No se encontró el producto!", Toast.LENGTH_SHORT).show();
            }
        }, itemId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}