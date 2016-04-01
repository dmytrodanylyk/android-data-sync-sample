package com.todo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

public class NetworkUtils {

    public static boolean isNetworkConnected(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null;
    }
}
