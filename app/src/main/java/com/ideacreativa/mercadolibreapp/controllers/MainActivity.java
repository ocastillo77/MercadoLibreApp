package com.ideacreativa.mercadolibreapp.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ideacreativa.mercadolibreapp.R;
import com.ideacreativa.mercadolibreapp.adapters.ListProductsAdapter;
import com.ideacreativa.mercadolibreapp.helpers.DividerDecorator;
import com.ideacreativa.mercadolibreapp.models.Producto;
import com.ideacreativa.mercadolibreapp.services.ProductResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Producto[] mProducts;
    SearchView mSearchView;
    RecyclerView mRecyclerProducts;
    ListProductsAdapter mListProductsAdapter;
    LinearLayout mNoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerProducts = findViewById(R.id.list);
        mRecyclerProducts.setHasFixedSize(true);
        mRecyclerProducts.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerProducts.addItemDecoration(new DividerDecorator(mRecyclerProducts.getContext()));

        mNoResults = findViewById(R.id.emptyContainer);
        mSearchView = findViewById(R.id.search);
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        closeKeyboard();
        mNoResults.setVisibility(View.GONE);

        ProductResponse mProductResponse = new ProductResponse(MainActivity.this);
        mProductResponse.productSearch(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    JSONObject jsonObject = new JSONObject(body);
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("results"));
                    mProducts = new Gson().fromJson(jsonArray.toString(), Producto[].class);

                    mListProductsAdapter = new ListProductsAdapter(mProducts, MainActivity.this);
                    mRecyclerProducts.setAdapter(mListProductsAdapter);

                    if (mProducts.length == 0) {
                        mNoResults.setVisibility(View.VISIBLE);
                    }
                } catch (IOException | JSONException e) {
                    Log.d("DetailProductActivity", e.getMessage());
                    Toast.makeText(MainActivity.this, "No se encontraron productos!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("DetailProductActivity", t.getMessage());
                Toast.makeText(MainActivity.this, "No se encontraron productos!", Toast.LENGTH_SHORT).show();
            }
        }, s, 50, 0);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}