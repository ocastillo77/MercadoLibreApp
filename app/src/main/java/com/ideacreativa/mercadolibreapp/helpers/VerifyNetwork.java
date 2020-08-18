package com.ideacreativa.mercadolibreapp.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.ideacreativa.mercadolibreapp.controllers.MainActivity;

public class VerifyNetwork {
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            Log.d("VerifyNetwork", e.getMessage());
            Toast.makeText(context, "No tiene conexión a internet!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }
}
